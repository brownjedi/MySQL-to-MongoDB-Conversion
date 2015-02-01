package edu.columbia.mysqltomongodbconv.mybatis.mapper;

import java.util.List;

import edu.columbia.mysqltomongodbconv.mybatis.domain.Address;
import edu.columbia.mysqltomongodbconv.mybatis.domain.Customer;

public interface SakilaDBMapper {

	public Customer getCustomerById(int id);

	public List<Customer> getAllCustomers();

	public List<Customer> getCustomerByAddressId(int addressId);

	public Address getAddressById(int id);

	public List<Address> getAllAddress();

}
