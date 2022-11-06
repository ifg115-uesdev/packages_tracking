package ues.edu.sv.packages_tracking.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/")
public class AuthController {

    @GetMapping(value="auth/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping(value="")
    public String getIndex() {
        return "index";
    }

}
