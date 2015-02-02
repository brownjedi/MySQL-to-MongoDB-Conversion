package edu.columbia.mysqltomongodbconv.mybatis.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import edu.columbia.mysqltomongodbconv.domain.Address;
import edu.columbia.mysqltomongodbconv.domain.Customer;
import edu.columbia.mysqltomongodbconv.mybatis.mapper.SakilaDBMapper;
import edu.columbia.mysqltomongodbconv.mybatis.util.MyBatisConnectionFactory;

public class SakilaDBService {

	public Customer getCustomerById(int id) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        Customer customer;
        try {
            SakilaDBMapper sakilaDBMapper = sqlSession.getMapper(SakilaDBMapper.class);
            customer = sakilaDBMapper.getCustomerById(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return customer;
	}

	public List<Customer> getAllCustomers() {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<Customer> customerList;
        try {
        	SakilaDBMapper sakilaDBMapper = sqlSession.getMapper(SakilaDBMapper.class);
            customerList = sakilaDBMapper.getAllCustomers();
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return customerList;
	}

	public List<Customer> getCustomerByAddressId(int addressId) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<Customer> customerList;
        try {
            SakilaDBMapper sakilaDBMapper = sqlSession.getMapper(SakilaDBMapper.class);
            customerList = sakilaDBMapper.getCustomerByAddressId(addressId);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return customerList;		
	}

	public Address getAddressById(int id) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        Address address;
        try {
            SakilaDBMapper sakilaDBMapper = sqlSession.getMapper(SakilaDBMapper.class);
            address = sakilaDBMapper.getAddressById(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return address;
	}

	public List<Address> getAllAddress() {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<Address> addressList;
        try {
        	SakilaDBMapper sakilaDBMapper = sqlSession.getMapper(SakilaDBMapper.class);
        	addressList = sakilaDBMapper.getAllAddress();
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return addressList;
	}

}
