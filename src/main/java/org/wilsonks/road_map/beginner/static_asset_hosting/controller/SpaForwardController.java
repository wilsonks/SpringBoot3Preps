package org.wilsonks.road_map.beginner.static_asset_hosting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@Slf4j
public class SpaForwardController {
    //A custom URL path created with regular expressions
    @GetMapping(value = {
            "/{path:(?!api)[^.]*}/**"
    })
    public String forward(@PathVariable String path) {
        log.info("Serving..." + path);
        //Spring processes "forward:/" by creating a new internal request with /index.html
        return "forward:/index.html";
    }
}
