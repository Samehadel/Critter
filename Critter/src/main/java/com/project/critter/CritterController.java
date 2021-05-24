package com.project.critter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy controller class to verify installation success. Does not have any usage.
 */
@RestController
public class CritterController {

    @GetMapping("/test")
    public String test(){
        return "Critter Starter installed successfully";
    }
}
