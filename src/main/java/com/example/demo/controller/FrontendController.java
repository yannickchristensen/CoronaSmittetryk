package com.example.demo.controller;

import com.example.demo.Service.KommuneService;
import com.example.demo.Service.SognService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @Autowired
    SognService sognService;

    @Autowired
    KommuneService kommuneService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sogne", sognService.findAll());
        return "index";
    }

}
