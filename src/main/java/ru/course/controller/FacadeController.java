package ru.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacadeController {

    @GetMapping("")
    public void test() {
        System.out.println();
    }

}
