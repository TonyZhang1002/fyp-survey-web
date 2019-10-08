package com.example.fypsurveyweb.controller;

import com.example.fypsurveyweb.FypSurveyWebApplication;
import com.example.fypsurveyweb.domain.UserAnswer;
import com.example.fypsurveyweb.service.MainService;
import com.example.fypsurveyweb.service.RandomGenerateImageNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
@author Qinyuan Zhang
@date 06/02/2019
*/
@Controller
public class MainController {

   @Autowired
   private MainService ms;
   @Autowired
   private RandomGenerateImageNames rgin;

   @GetMapping(value = "/surveyForm")
   public String formPage(Model model) {

      // init database in the beginning
      if (FypSurveyWebApplication.initFlag) {
         ms.initDB();
         FypSurveyWebApplication.initFlag = false;
      }

      // init 10 random image names
      rgin.initRandomNames();

      // Add info into the index web page
      model.addAttribute("imageNamesFrontend", rgin.getImageNamesFrontend());
      model.addAttribute("RealText", "It is Real.");
      model.addAttribute("FakeText", "It is Colourised by AI.");
      model.addAttribute("HintStarText", "Your Rate?");
      model.addAttribute("OneStarText", "*");
      model.addAttribute("TwoStarText", "* *");
      model.addAttribute("ThreeStarText", "* * *");
      model.addAttribute("FourStarText", "* * * *");

      // Return the index web page to the browser
      return "index";
   }

   @PostMapping(value = "/submitForm")
   public String getInfo(@ModelAttribute UserAnswer userAnswer) {
      userAnswer.setImageNames(rgin.getImageNamesBackend());
      ms.addResults(userAnswer);
      // Clear the pic names
      rgin.clearImageNames();
      return "success";
   }

   @GetMapping(value = "/check")
   public String check(Model model) {
      Map<String, Map<String, Integer>> results = ms.getResults();
      model.addAttribute("resultsMap", results);
      return "check";
   }
}
