package com.example.fypsurveyweb.controller;

import com.example.fypsurveyweb.FypSurveyWebApplication;
import com.example.fypsurveyweb.domain.UserAnswer;
import com.example.fypsurveyweb.service.mainService;
import com.example.fypsurveyweb.service.randomGenerateImageNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
@author Qinyuan Zhang
@date 06/02/2019
*/
@Controller
public class mainController {

   @Autowired
   private mainService ms;
   @Autowired
   private randomGenerateImageNames rgin;

   @RequestMapping(value = "/surveyForm", method = RequestMethod.GET)
   public String formPage(Model model) {

      if (FypSurveyWebApplication.initFlag) {
         System.out.println("Init Database!");
         ms.initDB();
         FypSurveyWebApplication.initFlag = false;
      }

      rgin.initRandomNames();

      model.addAttribute("imageNamesFrontend", rgin.getImageNamesFrontend());
      model.addAttribute("RealText", "It is Real.");
      model.addAttribute("FakeText", "It is Colourised by AI.");
      model.addAttribute("HintStarText", "Your Rate?");
      model.addAttribute("OneStarText", "*");
      model.addAttribute("TwoStarText", "* *");
      model.addAttribute("ThreeStarText", "* * *");
      model.addAttribute("FourStarText", "* * * *");


      return "index";
   }

   @RequestMapping(value = "/submitForm", method = RequestMethod.POST)
   public String getInfo(@ModelAttribute UserAnswer userAnswer) {
      userAnswer.setPicNum(rgin.getImageNamesBackend());
      ms.addResult(userAnswer);
      System.out.println(rgin.getImageNamesBackend());
      // Clear the pic names
      rgin.clearImageNames();
      return "success";
   }

   @RequestMapping(value = "/check", method = RequestMethod.GET)
   public String check(Model model) {
      Map<String, Map<String, Integer>> results = ms.getResults();
      //System.out.println(results.size());
      model.addAttribute("resultsMap", results);
      // System.out.println(results.get("4-1"));
      return "check";
   }
}
