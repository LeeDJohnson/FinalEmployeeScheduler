package org.launchcode.controllers;


import org.launchcode.models.TimeEntry;
import org.launchcode.models.data.EmployeeDao;
import org.launchcode.models.data.LoginDao;
import org.launchcode.models.data.TimeEntryDao;
import org.launchcode.models.forms.AddEntryItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("workHours")
public class WorkHourController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private TimeEntryDao timeEntryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }

        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title", "Work Schedules");

        return "workHours/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddWorkForm(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("title", "Time Entry");
        model.addAttribute("form", new AddEntryItemForm(employeeDao.findAll()));
        return "workHours/add-item";
    }

    @RequestMapping(value="view/{employeeId}", method = RequestMethod.GET)
    public String viewWorkHours(Model model, @PathVariable int employeeId) {

        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("title", employeeDao.findOne(employeeId).getName()+"'s Hours");
        Iterable<TimeEntry> timeEntries = timeEntryDao.findAll();

        List<TimeEntry> employeeTimeEntries = new ArrayList<>();
        timeEntries.forEach(timeEntry -> {
            if (timeEntry.getEmployee().getId() == employeeId) {
                employeeTimeEntries.add(timeEntry);
            }
        });

        model.addAttribute("timeEntry", employeeTimeEntries);
        return "workHours/view";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddEntryItemForm form, Errors errors) {

        if (errors.hasErrors()){
            model.addAttribute("form", form);
            System.out.println("errors: " + errors);
            return"workHours/add-item";
        }

        timeEntryDao.save(new TimeEntry(form.getClockIn(), form.getClockOut(),  employeeDao.findOne(form.getEmployeeId())));
        return "redirect:/workHours/view/" + form.getEmployeeId();
    }

}
