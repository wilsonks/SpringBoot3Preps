package org.wilsonks.road_map.beginner.create_api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Maps web requests to specific methods.
 */
@Controller
public class TestController {

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("message", "Hello World!");
        return "home"; // Resolves to home.html or home.jsp
    }
}
