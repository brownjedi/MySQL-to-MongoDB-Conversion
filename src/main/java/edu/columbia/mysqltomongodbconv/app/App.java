package edu.columbia.mysqltomongodbconv.app;

import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoTimeoutException;

import edu.columbia.mysqltomongodbconv.domain.Address;
import edu.columbia.mysqltomongodbconv.domain.Customer;
import edu.columbia.mysqltomongodbconv.mybatis.service.SakilaDBService;

public class App {

	private static final Logger LOGGER = Logger.getLogger("App");

	public static void main(String[] args) {

		try {
			// Create a new Mongoclient
			
			System.out.println("Creating a MongoDB Client");
			MongoClient client = new MongoClient("localhost", 27017);

			// Connect to the Database "sakila", if it doesn't exist, MongoDB will create one for us.
			
			DB db = client.getDB("sakila");
			System.out.println("Droping existing \"sakila\" database");

			// Since this project creates a database from scratch, we will first delete any existing "sakila" database			
			
			db.dropDatabase();

			System.out.println("Creating a new \"sakila\" database");

			// Accessing the collections (tables) "customer" and "address". As usual, if these are not already present, 
			// MongoDB will create one for us
			
			System.out.println("Accessing a \"customer\" and \"address\" collections");
			DBCollection customerCollection = db.getCollection("customer");
			DBCollection addressCollection = db.getCollection("address");

			// Create a JacksonDBCollection which wraps around the Customer and Address Classes
			
			JacksonDBCollection<Customer, String> customerJacksonCollection = JacksonDBCollection
					.wrap(customerCollection, Customer.class, String.class);
			JacksonDBCollection<Address, String> addressJacksonCollection = JacksonDBCollection
					.wrap(addressCollection, Address.class, String.class);

			// This is a service created using MyBatis framework, which is used to get the relational tables information from MySQL.
			
			SakilaDBService sakilaDBService = new SakilaDBService();

			// Getting all the Address information from the "address" table. Here, i have included the city and the 
			// country information in the address itself instead of creating a new class for them.
			
			List<Address> addressList = sakilaDBService.getAllAddress();

			// Since, the address table is referred as a foriegn key in the customer table, we will be doing a manual reference in the
			// MongoDB. First, we will be populating the address row, and then we will get all the customers who are referring the 
			// address row .Then add the address json's objectID(_id) to the customer json as a field so that we can refer it 
			// manually when required.

			System.out.println("Inserting Data...!!!");

			for (Address address : addressList) {

				// Write the result into the "address" collection and get the result.
				
				WriteResult<Address, String> result = addressJacksonCollection.insert(address);

				// Find all the customers which use this address as a foreign key
				List<Customer> customerList = sakilaDBService.getCustomerByAddressId(address.getId());

				// For every customer populate the AddressObjectId as result.getSavedId() to create a manual reference
				for (Customer customer : customerList) {
					customer.setAddressObjectId(result.getSavedId());

					// Save the document in the "customer" collection
					customerJacksonCollection.insert(customer);
				}
			}

			System.out.println("Data Conversion successful");

			// Close the MongoClient
			System.out.println("Closing the MongoDB Client");
			client.close();

		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE, "Unable to Create MongoClient, Unknown Host Exception", e);
		} catch (MongoTimeoutException e) {
			LOGGER.log(Level.SEVERE, "Unable to Create MongoClient, Timeout Exception", e);
		}
	}

}
