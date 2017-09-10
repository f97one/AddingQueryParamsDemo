package net.formula97.webapps.addingqueryparamsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLogin(@RequestParam(name = "p", required = false) String param) {

        return "login";
    }
}
