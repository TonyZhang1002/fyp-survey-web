package com.example.fypsurveyweb.dao;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

/*
@author Qinyuan Zhang
@date 01/04/2019
*/
public class MongoDB {

   // Create a singleton instance
   private static final MongoDB instance = new MongoDB();

   private MongoDB() {}

   public static MongoDB getInstance() {
      return instance;
   }

   MongoClient mongo = new MongoClient("db", 27017);

   // This method is used to keep the connection with the database
   public MongoCollection<Document> getTable () {
      MongoDatabase db = mongo.getDatabase("usersAnswers");
      return db.getCollection("user");
   }

   public void initDB () {
      MongoCollection<Document> table = this.getTable();
      List<Document> documents = new ArrayList<>();
      for (int i = 0; i < 101; i++) {
         for (int j = 0; j < 4; j++) {
            Document document = new Document("pic", i + "-" + j).
                    append("Real", 0).append("Fake", 0).append("Rate", 0).
                    append("1Involve", 0).append("2Involve", 0);
            documents.add(document);
         }
      }
      table.insertMany(documents);
   }

   public void updateOneInDB(String picBackend, String key, int value) {
      getTable().updateOne(Filters.eq("pic", picBackend), new Document("$inc",new Document(key,value)));
   }

   public MongoCursor<Document> getMongoCursor () {
      FindIterable<Document> findIterable = getTable().find();
      return findIterable.iterator();
   }

   public void closeConnection () {
      if (mongo != null) {
         mongo.close();
      }
   }
}
