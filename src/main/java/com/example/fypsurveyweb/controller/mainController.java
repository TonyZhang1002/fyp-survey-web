package com.example.fypsurveyweb.controller;

import com.example.fypsurveyweb.FypSurveyWebApplication;
import com.example.fypsurveyweb.domain.UserAnswer;
import com.example.fypsurveyweb.service.mainService;
import com.example.fypsurveyweb.service.randomGenerateImageNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

   public static List<String> picNum = new ArrayList<>();
   public static List<Integer> preventDup = new ArrayList<>();
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

      for (int i = 1; i <= 2; i++) {
         for (int j = 1; j <= 5; j++) {
            model.addAttribute("imgName" + i + "" + j, rgin.getPicNumFrontend()[i-1][j-1]);
         }
      }

      return "index";
   }

   @RequestMapping(value = "/submitForm", method = RequestMethod.POST)
   public String getInfo(@ModelAttribute UserAnswer userAnswer) {
      userAnswer.setPicNum(picNum);
      ms.addResult(userAnswer);
      System.out.println(picNum);
      picNum.clear();
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
