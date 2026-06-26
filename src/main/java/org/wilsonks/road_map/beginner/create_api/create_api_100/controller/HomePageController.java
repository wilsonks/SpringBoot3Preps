package org.wilsonks.road_map.beginner.create_api.create_api_100.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class HomePageController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
