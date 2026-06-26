package org.wilsonks.road_map.beginner.create_api.create_api_100.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardPageController {
    // Captures all routes except direct asset files (files containing a ".")
    @GetMapping(value = "/{path:[^\\.]*}")
    public String Redirect() {
        return "forward:/index.html";
    }
}
