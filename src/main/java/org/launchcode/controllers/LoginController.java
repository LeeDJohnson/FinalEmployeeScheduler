package org.launchcode.controllers;


import org.launchcode.models.User;
import org.launchcode.models.data.EmployeeDao;
import org.launchcode.models.data.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value = "noAccess", method = RequestMethod.GET)
    public String noAccess(Model model) {

        return "login/noAccess";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLogin(Model model) {
        model.addAttribute("title", "Login Page");

        model.addAttribute(new User());
        return "redirect:login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLogin2(Model model) {
        model.addAttribute("title", "Login Page");

        model.addAttribute(new User());
        return "login/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processAddPositionForm(@ModelAttribute @Valid User user,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login Page");
            return "redirect:login";
        }

        String userName = user.getUserName();
        String password = user.getPassword();

        if (loginDao.authenticateUser(user)) {
            model.addAttribute("employees", employeeDao.findAll());
            model.addAttribute("title", "My Employees");

            String redirectUrl = loginDao.isCurrentUserAdmin()
                    ? "employee"
                    : "date";

            return "redirect:"+redirectUrl;

        }

        return "login/wrongPassword";
    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model) {
        loginDao.setCurrentUser(null);
        return "redirect:login";
    }
}