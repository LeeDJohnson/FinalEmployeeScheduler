package org.launchcode.controllers;

import org.launchcode.models.Position;
import org.launchcode.models.Employee;
import org.launchcode.models.data.LoginDao;
import org.launchcode.models.data.PositionDao;
import org.launchcode.models.data.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private PositionDao postionDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        if (!loginDao.isCurrentUserAdmin()){
            return "redirect:noAccess";
        }

        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title", "My Employees");

        return "employee/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddEmployeeForm(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("title", "Add Employee");
        model.addAttribute(new Employee());
        model.addAttribute("positions", postionDao.findAll());
        return "employee/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEmployeeForm(@ModelAttribute  @Valid Employee newEmployee,
            Errors errors, @RequestParam int positionId, Model model) {

        Position position = postionDao.findOne(positionId);
        newEmployee.setPosition(position);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Employee");
            return "employee/add";
        }
        employeeDao.save(newEmployee);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveEmployeeForm(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title", "Remove Employee");
        return "employee/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveEmployeeForm(@RequestParam int[] employeeIds) {

        for (int employeeId : employeeIds) {
            employeeDao.delete(employeeId);
        }

        return "redirect:";
    }

}
