package com.example.fypsurveyweb.service;

import com.example.fypsurveyweb.dao.MongoDB;
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
public class MainService {
   
   // Define variable constants
   private static final String InvolveInFirstKindQuestions = "1Involve";
   private static final String InvolveInSecondKindQuestions = "2Involve";
   private static final String RealMarker = "Real";
   private static final String FakeMarker = "Fake";
   private static final String RateMarker = "Rate";
   
   
   // Use the singleton instance
   private MongoDB mDB = MongoDB.getInstance();

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
            if (userAnswer.wrapperOfAnswers()[picIndex - 1].equals("q" + picIndex +RealMarker)) {
               mDB.updateOneInDB(pic, RealMarker, 1);
            } else {
               mDB.updateOneInDB(pic, FakeMarker, 1);
            }
            mDB.updateOneInDB(pic, InvolveInFirstKindQuestions, 1);
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
            mDB.updateOneInDB(pic, RateMarker, 1);
            break;
         case "Two":
            mDB.updateOneInDB(pic, RateMarker, 2);
            break;
         case "Three":
            mDB.updateOneInDB(pic, RateMarker, 3);
            break;
         case "Four":
            mDB.updateOneInDB(pic, RateMarker, 4);
            break;
         default:
            break;
      }
      mDB.updateOneInDB(pic, InvolveInSecondKindQuestions, 1);
   }

   // A method to get results from the DB
   public Map<String, Map<String, Integer>> getResults() {

      Map<String, Map<String, Integer>> results = new TreeMap<>();
      Map<String, Integer> oriMap = new TreeMap<>();
      Map<String, Integer> ordMap = new TreeMap<>();
      Map<String, Integer> ganMap = new TreeMap<>();
      Map<String, Integer> resMap = new TreeMap<>();

      MongoCursor<Document> mongoCursor = mDB.getMongoCursor();
      while(mongoCursor.hasNext()){
         Document currentCursor = mongoCursor.next();
         switch (((String) currentCursor.get("pic")).split("-")[1]) {
            case "0":
               getInfoFromDB(currentCursor, oriMap);
               break;
            case "1":
               getInfoFromDB(currentCursor, ordMap);
               break;
            case "2":
               getInfoFromDB(currentCursor, ganMap);
               break;
            case "3":
               getInfoFromDB(currentCursor, resMap);
               break;
            default:
               break;
         }
         results.put("Ori", oriMap);
         results.put("Ord", ordMap);
         results.put("Gan", ganMap);
         results.put("Res", resMap);
      }
      return results;
   }

   private void getInfoFromDB (Document currentCursor, Map<String, Integer> map) {
      map.put(RealMarker, map.containsKey(RealMarker) ? map.get(RealMarker) +
              (Integer) currentCursor.get(RealMarker) :  (Integer) currentCursor.get(RealMarker));
      map.put(FakeMarker, map.containsKey(FakeMarker) ? map.get(FakeMarker) +
              (Integer) currentCursor.get(FakeMarker) :  (Integer) currentCursor.get(FakeMarker));
      map.put(RateMarker, map.containsKey(RateMarker) ? map.get(RateMarker) +
              (Integer) currentCursor.get(RateMarker) :  (Integer) currentCursor.get(RateMarker));
      map.put(InvolveInFirstKindQuestions, map.containsKey(InvolveInFirstKindQuestions) ? map.get(InvolveInFirstKindQuestions) +
              (Integer) currentCursor.get(InvolveInFirstKindQuestions) :  (Integer) currentCursor.get(InvolveInFirstKindQuestions));
      map.put(InvolveInSecondKindQuestions, map.containsKey(InvolveInSecondKindQuestions) ? map.get(InvolveInSecondKindQuestions) +
              (Integer) currentCursor.get(InvolveInSecondKindQuestions) :  (Integer) currentCursor.get(InvolveInSecondKindQuestions));
   }

}
