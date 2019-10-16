package org.launchcode.controllers;

import org.launchcode.models.Employee;
import org.launchcode.models.Date;
import org.launchcode.models.data.EmployeeDao;
import org.launchcode.models.data.DateDao;
import org.launchcode.models.data.LoginDao;
import org.launchcode.models.forms.AddDateItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("date")
public class DateController {
    @Autowired
    private DateDao dateDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }

        model.addAttribute("dates", dateDao.findAll());
        model.addAttribute("title", "Work Schedules");

        return "date/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("title", "Date of Work Schedule");
        model.addAttribute(new Date());
        model.addAttribute("positions", dateDao.findAll());
        return "date/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDateForm(@ModelAttribute @Valid Date newDate,
                                     Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Schedule");
            return "redirect:date/add";
        }
        dateDao.save(newDate);
        return "redirect:view/" + newDate.getId();
    }

    @RequestMapping(value="view/{dateId}", method = RequestMethod.GET)
    public String viewDate(Model model, @PathVariable int dateId) {

        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        Date date = dateDao.findOne(dateId);
        model.addAttribute("title", "Work Schedule");
        model.addAttribute("date", date);
        model.addAttribute("dateId", date.getId());
        return "date/view";
    }

    @RequestMapping(value="add-item/{dateId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int dateId) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        Date date = dateDao.findOne(dateId);
        AddDateItemForm form = new AddDateItemForm(date, employeeDao.findAll());
        model.addAttribute("form", form);
        model.addAttribute("title", "Schedule Employees to work: " + date.getName());
        return "date/add-item";
    }

    @RequestMapping(value="add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddDateItemForm form, Errors errors) {

        if (errors.hasErrors()){
            model.addAttribute("form", form);
            return"redirect:date/add-item";
        }

        Employee employee = employeeDao.findOne(form.getEmployeeId());
        Date date = dateDao.findOne(form.getDateId());
        date.addItem(employee);
        dateDao.save(date);
        return "redirect:/date/view/" + date.getId();
    }

}
