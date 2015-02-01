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

import edu.columbia.mysqltomongodbconv.mongojack.domain.MongoJackAddress;
import edu.columbia.mysqltomongodbconv.mongojack.domain.MongoJackCustomer;
import edu.columbia.mysqltomongodbconv.mybatis.domain.Address;
import edu.columbia.mysqltomongodbconv.mybatis.domain.Customer;
import edu.columbia.mysqltomongodbconv.mybatis.service.SakilaDBService;

public class App {

	private static final Logger LOGGER = Logger.getLogger("App");

	public static void main(String[] args) {

		try {
			MongoClient client = new MongoClient("localhost", 27017);

			DB db = client.getDB("sakila");			
			db.dropDatabase();			

			DBCollection customerCollection = db.getCollection("customer");
			DBCollection addressCollection = db.getCollection("address");

			JacksonDBCollection<MongoJackCustomer, String> customerJacksonCollection = JacksonDBCollection
					.wrap(customerCollection, MongoJackCustomer.class,
							String.class);
			JacksonDBCollection<MongoJackAddress, String> addressJacksonCollection = JacksonDBCollection
					.wrap(addressCollection, MongoJackAddress.class,
							String.class);

			SakilaDBService sakilaDBService = new SakilaDBService();

			List<Address> addressList = sakilaDBService.getAllAddress();

			for (Address address : addressList) {

				MongoJackAddress mongoJackAddress = new MongoJackAddress();
				mongoJackAddress.setAddress(address.getAddress());
				mongoJackAddress.setAddress2(address.getAddress2());
				mongoJackAddress.setDistrict(address.getDistrict());
				mongoJackAddress.setCity(address.getCity());
				mongoJackAddress.setCountry(address.getCountry());
				mongoJackAddress.setPostalCode(address.getPostalCode());
				mongoJackAddress.setLastUpdate(address.getLastUpdate());

				WriteResult<MongoJackAddress, String> result = addressJacksonCollection
						.insert(mongoJackAddress);

				List<Customer> customerList = sakilaDBService.getCustomerByAddressId(address.getId());

				for (Customer customer : customerList) {
					MongoJackCustomer mongoJackCustomer = new MongoJackCustomer();
					mongoJackCustomer.setName(customer.getName());
					mongoJackCustomer.setEmail(customer.getEmail());
					mongoJackCustomer.setAddressObjectId(result.getSavedId());
					mongoJackCustomer.setActive(customer.isActive());
					mongoJackCustomer.setCreateDate(customer.getCreateDate());
					mongoJackCustomer.setLastUpdate(customer.getLastUpdate());
					customerJacksonCollection.insert(mongoJackCustomer);
				}				
			}

			client.close();
		} catch (UnknownHostException e) {
			LOGGER.log(Level.SEVERE,
					"Unable to Create MongoClient, Unknown Host Exception", e);
		} catch (MongoTimeoutException e) {
			LOGGER.log(Level.SEVERE,
					"Unable to Create MongoClient, Timeout Exception", e);
		}
	}

}
