package net.formula97.webapps.addingqueryparamsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    @RequestMapping("/menu")
    public String showMenu(@RequestParam(name = "p", required = false) String param, Model model) {
        String urlParam = "(not specified)";
        if (param != null && param.length() > 0) {
            urlParam = param;
        }
        model.addAttribute("urlparam", urlParam);
        
        return "menu";
    }
}
