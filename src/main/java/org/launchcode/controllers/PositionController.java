package org.launchcode.controllers;

import org.launchcode.models.Position;
import org.launchcode.models.data.LoginDao;
import org.launchcode.models.data.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping("position")
public class PositionController {
    @Autowired
    private PositionDao positionDao;

    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        if (!loginDao.isCurrentUserAdmin()){
            return "redirect:noAccess";
        }
        model.addAttribute("positions", positionDao.findAll());
        model.addAttribute("title", "Positions");

        return "position/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPositionForm(Model model) {
        if (!loginDao.isLoggedIn()) {
            return "redirect:login";
        }
        model.addAttribute("title", "Add Position");
        model.addAttribute(new Position());

        return "position/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPositionForm(@ModelAttribute @Valid Position newPosition,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Position Type");
            return "position/add";
        }

        positionDao.save(newPosition);
        return "redirect:";
    }
}
