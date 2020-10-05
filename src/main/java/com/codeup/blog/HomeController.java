package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<h1>This is the landing page!</h1>";
    }
}
