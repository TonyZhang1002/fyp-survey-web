package com.example.fypsurveyweb.service;

import com.example.fypsurveyweb.dao.mongoDB;
import com.example.fypsurveyweb.domain.UserAnswer;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.*;

/*
@author Qinyuan Zhang
@date 01/04/2019
*/
@Service
public class mainService {

   // Use the singleton instance
   private mongoDB mDB = mongoDB.getInstance();

   // A method to init the DB
   public void initDB() {
      mDB.initDB();
   }

   // A method to add results to the DB
   public void addResults (UserAnswer userAnswer) {

      int picIndex = 1;
      for (String pic : userAnswer.getImageNames()) {
         // If we are talking about the first kind of questions
         if (picIndex <=5) {
            if (userAnswer.wrapperOfAnswers()[picIndex - 1].equals("q" + picIndex +"Real")) {
               mDB.updateOneInDB(pic, "Real", 1);
            } else {
               mDB.updateOneInDB(pic, "Fake", 1);
            }
            mDB.updateOneInDB(pic, "1Involve", 1);
         }
         // If we are talking about the second kind of questions
         else {
            addRates(userAnswer.wrapperOfAnswers()[picIndex - 1],pic);
         }
         picIndex++;
      }
   }

   // A method to add rates to the DB
   public void addRates (String result, String pic) {
      switch (result) {
         case "One":
            mDB.updateOneInDB(pic, "Rate", 1);
            break;
         case "Two":
            mDB.updateOneInDB(pic, "Rate", 2);
            break;
         case "Three":
            mDB.updateOneInDB(pic, "Rate", 3);
            break;
         case "Four":
            mDB.updateOneInDB(pic, "Rate", 4);
            break;
      }
      mDB.updateOneInDB(pic, "2Involve", 1);
   }

   // A method to get results from the DB
   public Map<String, Map<String, Integer>> getResults() {

      Map<String, Map<String, Integer>> results = new TreeMap<>();
      Map<String, Integer> Ori = new TreeMap<>();
      Map<String, Integer> Ord = new TreeMap<>();
      Map<String, Integer> Gan = new TreeMap<>();
      Map<String, Integer> Res = new TreeMap<>();

      MongoCursor<Document> mongoCursor = mDB.getMongoCursor();
      while(mongoCursor.hasNext()){
         Document currentCursor = mongoCursor.next();
         switch (((String) currentCursor.get("pic")).split("-")[1]) {
            case "0":
               getInfoFromDB(currentCursor, Ori);
               break;
            case "1":
               getInfoFromDB(currentCursor, Ord);
               break;
            case "2":
               getInfoFromDB(currentCursor, Gan);
               break;
            case "3":
               getInfoFromDB(currentCursor, Res);
               break;
         }
         results.put("Ori", Ori);
         results.put("Ord", Ord);
         results.put("Gan", Gan);
         results.put("Res", Res);
      }
      return results;
   }

   private void getInfoFromDB (Document currentCursor, Map<String, Integer> map) {
      map.put("Real", map.containsKey("Real") ? map.get("Real") +
              (Integer) currentCursor.get("Real") :  (Integer) currentCursor.get("Real"));
      map.put("Fake", map.containsKey("Fake") ? map.get("Fake") +
              (Integer) currentCursor.get("Fake") :  (Integer) currentCursor.get("Fake"));
      map.put("Rate", map.containsKey("Rate") ? map.get("Rate") +
              (Integer) currentCursor.get("Rate") :  (Integer) currentCursor.get("Rate"));
      map.put("1Involve", map.containsKey("1Involve") ? map.get("1Involve") +
              (Integer) currentCursor.get("1Involve") :  (Integer) currentCursor.get("1Involve"));
      map.put("2Involve", map.containsKey("2Involve") ? map.get("2Involve") +
              (Integer) currentCursor.get("2Involve") :  (Integer) currentCursor.get("2Involve"));
   }

}
