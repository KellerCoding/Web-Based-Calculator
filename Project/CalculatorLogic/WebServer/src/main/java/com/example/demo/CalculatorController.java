package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CalculatorController {
    @GetMapping("/")
    public String index() {
        return "<h1>Greetings from Kahmin!</h1>";
    }
/*
    @GetMapping("/")
    public String inputForm(Model model) {
        model.addAttribute("input", new Input());
        return "input";
    }

    /*@PostMapping("/")
    public String inputSubmit(@ModelAttribute Input input, Model model) {
        model.addAttribute("input", input);
        return "result";
    }*/
}
