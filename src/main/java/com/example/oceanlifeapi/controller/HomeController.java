package com.example.oceanlifeapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller - handles root URL requests.
 */
@Controller
public class HomeController {

    /**
     * Redirect root URL to animals list.
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/animals";
    }
}
