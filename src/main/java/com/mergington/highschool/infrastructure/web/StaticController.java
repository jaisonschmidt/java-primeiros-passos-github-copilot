package com.mergington.highschool.infrastructure.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for serving static files
 */
@Controller
public class StaticController {
    
    /**
     * Redirect root to static index.html
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/static/index.html";
    }
}