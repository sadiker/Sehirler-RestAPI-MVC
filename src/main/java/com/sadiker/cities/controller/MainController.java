package com.sadiker.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.sadiker.cities.service.CityService;


@Controller
public class MainController {

    @Autowired
    CityService cityService;
    
    @GetMapping({"/","/home",""})
    public String home(){
        return "home";
    }

    @GetMapping("/citiesmvc")
    public String apiCity(ModelMap map){
        map.addAttribute("cities", cityService.findAll());
       return "cities"; 
    }
}
