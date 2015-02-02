package edu.columbia.mysqltomongodbconv.domain;

import java.util.Date;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {

	@JsonIgnore
	private int id;

	@Id
	private String objectId;

	private Name name;
	private String email;

	@JsonIgnore
	private int addressId;
	
	private String addressObjectId;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getAddressObjectId() {
		return addressObjectId;
	}

	public void setAddressObjectId(String addressObjectId) {
		this.addressObjectId = addressObjectId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
