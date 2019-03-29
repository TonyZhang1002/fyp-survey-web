package com.example.fypsurveyweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
@author Qinyuan Zhang
@date 06/02/2019
*/
@Controller
public class mainController {
   @GetMapping("/123")
   public String formPage(Model model) {
      model.addAttribute("imgName", "2-gan");
      return "index";
   }
}
