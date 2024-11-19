package com;
import com.mongodb.MongoClient;  
import com.mongodb.client.MongoCollection;  
import com.mongodb.client.MongoDatabase;  
import org.bson.Document;
public class MongoDBDemo {
public static void main(String[] args) {
	try{  
		//---------- Connecting DataBase -------------------------//  
		System.out.println("Connecting DataBase.........");
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
		//---------- Creating DataBase ---------------------------//  
		MongoDatabase db = mongoClient.getDatabase("javatpoint");  
		//---------- Creating Collection -------------------------//  
		System.out.println("Creating Collection.......");
		MongoCollection<Document> table = db.getCollection("employee");  
		//---------- Creating Document ---------------------------//
		System.out.println("Creating Document.......");
		Document doc = new Document("name", "Peter John");  
		doc.append("id",12);  
		//----------- Inserting Data ------------------------------//  
		System.out.println("Inserting Data......");
		table.insertOne(doc);  
		System.out.println("Data inserted succesfully......");
		}catch(Exception e){  
		System.out.println(e);  
		}  
}
}
