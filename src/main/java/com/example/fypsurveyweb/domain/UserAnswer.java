package com.example.fypsurveyweb.domain;

import java.util.List;

/*
@author Qinyuan Zhang
@date 01/04/2019
*/
public class UserAnswer {
   private String name = "unknown";
   private String email = "unknown";
   private String q1;
   private String q2;
   private String q3;
   private String q4;
   private String q5;
   private String q6;
   private String q7;
   private String q8;
   private String q9;
   private String q10;
   private List<String> imageNames;

   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

   public String getQ1() {
      return q1;
   }

   public String getQ2() {
      return q2;
   }

   public String getQ3() {
      return q3;
   }

   public String getQ4() {
      return q4;
   }

   public String getQ5() {
      return q5;
   }

   public String getQ6() {
      return q6;
   }

   public String getQ7() {
      return q7;
   }

   public String getQ8() {
      return q8;
   }

   public String getQ9() {
      return q9;
   }

   public String getQ10() {
      return q10;
   }

   public List<String> getImageNames() {
      return imageNames;
   }

   public String[] wrapperOfAnswers() {
      return new String[] {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setQ1(String q1) {
      this.q1 = q1;
   }

   public void setQ2(String q2) {
      this.q2 = q2;
   }

   public void setQ3(String q3) {
      this.q3 = q3;
   }

   public void setQ4(String q4) {
      this.q4 = q4;
   }

   public void setQ5(String q5) {
      this.q5 = q5;
   }

   public void setQ6(String q6) {
      this.q6 = q6;
   }

   public void setQ7(String q7) {
      this.q7 = q7;
   }

   public void setQ8(String q8) {
      this.q8 = q8;
   }

   public void setQ9(String q9) {
      this.q9 = q9;
   }

   public void setQ10(String q10) {
      this.q10 = q10;
   }

   public void setImageNames(List<String> imageNames) {
      this.imageNames = imageNames;
   }
}
