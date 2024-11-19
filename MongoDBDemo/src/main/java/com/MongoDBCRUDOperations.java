package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDBCRUDOperations {
	public static MongoDatabase databaseMongoDB = null;
	MongoCollection<Document> collection = null;

	public void createCollection(String collectionName) {
		try {
			// getting database
			databaseMongoDB = MongoDBConnectionUtil.establishConnection();
			// Create the collection
			databaseMongoDB.createCollection(collectionName);
			System.out.println("Collection created Successfully");
		} catch (Exception e) {
			System.out.println("Collection creation failed");
			System.out.println(e);
		}
	}

	public void getCollection(String collectionName) {

		try {

			// Retrieve the collection
			collection = databaseMongoDB.getCollection(collectionName);

			System.out.println("Collection retrieved Successfully");
		} catch (Exception e) {
			System.out.println("Collection retrieval failed");
			System.out.println(e);
		}
	}

	public void insertADocIntoDb() {
		try {
			// Creating the document
			// to be inserted
			Document document = new Document("title", "MongoDB").append("about", "Open-Source database");

			// Insert the document
			collection.insertOne(document);

			System.out.println("Single document is inserted successfully");
		} catch (Exception e) {
			System.out.println("Document insertion failed");
			System.out.println(e);
		}
	}

	// Function to insert multiple
	// documents in to the MongoDB
	public void insertManyDocsIntoDb() {
		try {
			// Creating the document
			// to be inserted
			Document document = new Document("title", "Apache Casendra").append("about", "Open-Source database");
			Document document1 = new Document("title", "Reddis").append("about", "Open-source database");

			// Adding the documents into a list
			List<Document> dblist = new ArrayList<Document>();
			dblist.add(document);
			dblist.add(document1);

			// Insert the list of documents into DB
			collection.insertMany(dblist);

			System.out.println("Multiple documents are inserted successfully");
		} catch (Exception e) {
			System.out.println("Documents insertion failed");
			e.printStackTrace();
		}

	}

	public void updateDocuments() {

		try {
			MongoCollection<Document> collection = databaseMongoDB.getCollection("Databases");

			collection.updateOne(Filters.eq("title", "MongoDB"), Updates.set("about", "Database"));

			System.out.println("Successfully updated" + " the document");
		} catch (Exception e) {
			System.out.println("Updation failed");
			System.out.println(e);
		}
	}

	public void displayCollections() {

		try {
			System.out.println("Displaying the list" + " of all collections");

			MongoCollection<Document> collection = databaseMongoDB.getCollection("Databases");

			for (String allColl : databaseMongoDB.listCollectionNames()) {
				System.out.println(allColl);
			}
		} catch (Exception e) {
			System.out.println("Collections display failed");
			System.out.println(e);
		}
	}

	public void displayCollectionContents() {
		// Creating a collection object
		MongoCollection<Document> collection = databaseMongoDB.getCollection("Databases");
		// Retrieving the documents
		FindIterable<Document> iterDoc = collection.find();
		Iterator it = iterDoc.iterator();
		System.out.println("Displaying contents of the collection.....");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void deleteDocument() {

		try {
			MongoCollection<Document> collection = databaseMongoDB.getCollection("Databases");

			collection.deleteOne(Filters.eq("title", "Reddis")); // To delete single document.
			// collection.deleteMany(Filters.eq("title", "Reddis")); //To delete multiple
			// documents.
			System.out.println("Successfully deleted" + " the document");
		} catch (Exception e) {
			System.out.println("Deletion failed");
			System.out.println(e);
		}
	}

	public void dropACollection(String collectionName) {

		try {
			MongoCollection<Document> collection = databaseMongoDB.getCollection(collectionName);

			// Drop the above collection
			collection.drop();

			System.out.println("Successfully dropped" + " collection");
		} catch (Exception e) {
			System.out.println("Drop failed");
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		MongoDBCRUDOperations obj = new MongoDBCRUDOperations();
		obj.createCollection("Databases");
		obj.getCollection("Databases");
		obj.insertADocIntoDb();
		obj.insertManyDocsIntoDb();
		obj.updateDocuments();
		obj.displayCollections();
		obj.displayCollectionContents();
		obj.deleteDocument();
		obj.displayCollectionContents();
		obj.dropACollection("Databases");
		obj.displayCollectionContents();
	}
}
