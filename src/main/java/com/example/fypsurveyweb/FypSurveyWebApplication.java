package com.example.fypsurveyweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FypSurveyWebApplication {

   public static boolean initFlag = true;

   public static void main(String[] args) {
      SpringApplication.run(FypSurveyWebApplication.class, args);
   }

}
