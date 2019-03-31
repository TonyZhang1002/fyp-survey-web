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
   @GetMapping("")
   public String formPage(Model model) {
      int randomImg = (int) (Math.random() * 17);
      int randomTag = (int) (Math.random() * 4);
      switch (randomTag) {
         case 0:  model.addAttribute("imgName", randomImg + "-ord.jpg");
         case 1:  model.addAttribute("imgName", randomImg + "-ori.jpg");
         case 2:  model.addAttribute("imgName", randomImg + "-gan.png");
         case 3:  model.addAttribute("imgName", randomImg + "-res.png");
         default:
      }
      return "index";
   }
}
