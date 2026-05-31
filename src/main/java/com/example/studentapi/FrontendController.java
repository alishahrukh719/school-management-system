package com.example.studentapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FrontendController {

    @GetMapping("/app")
    public RedirectView app() {
        return new RedirectView("/school_management_system.html");
    }
}
