package com.example.javaitmo2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        String message = "Authorize for use API";

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/", "/registration" }, method = RequestMethod.GET)
    public String registration(Model model) {

        String message = "Registration for use App";

        model.addAttribute("message", message);

        return "registration";
    }
}
