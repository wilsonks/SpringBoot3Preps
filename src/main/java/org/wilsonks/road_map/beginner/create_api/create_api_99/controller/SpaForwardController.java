package org.wilsonks.road_map.beginner.create_api.create_api_99.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@Slf4j
public class SpaForwardController {
    @GetMapping(value = "/{path:(?!api)[^.]*}/**")
    public String forward(@PathVariable String path) {
        log.info("Serving Path {}", path);
        return "forward:/index.html";
    }

}
