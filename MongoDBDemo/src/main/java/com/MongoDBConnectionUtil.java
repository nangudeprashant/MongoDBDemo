package com;

import com.mongodb.client.MongoDatabase;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

/*
 *Establishing connections to database
For making the connection, you have to mention the database name. MongoDB creates a database by 
default if no name is mentioned.

Firstly, import the required libraries for establishing the connection.
Here, “MongoClient” is used to create the client for the database.
“MongoCredential” is used for creating the credentials.(We are not using this part as of now.)
And finally, to access the database “MongoDatabase” is used.
Username will be: “GFGUser” and the database name will be “mongoDb“.
The function “.toCharArray()” is used to convert the password into a character array.
The function “.getDatabase()” is used for getting the database.
The following code establishes a connection to MongoDB 
*/

public class MongoDBConnectionUtil {
	public static MongoDatabase establishConnection() {

		MongoDatabase databaseMongoDB = null;
		try {
			MongoClient db = new MongoClient("localhost", 27017);

			 /* Please note that we are not using following part and logging into the database without 
			  * credentials.
			 */ 
			 
			 /*
			 * MongoCredential credential; credential = MongoCredential .createCredential(
			 * "GFGUser", "mongoDb", "password".toCharArray()); System.out.println(
			 * "Successfully Connected" + " to the database");
			 * System.out.println("Credentials are: " + credential);
			 */

			databaseMongoDB = db.getDatabase("mongoDb");

		} catch (Exception e) {
			System.out.println("Connection establishment failed");
			System.out.println(e);
		}
		return databaseMongoDB;
	}

}
