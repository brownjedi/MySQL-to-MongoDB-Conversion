package edu.columbia.mysqltomongodbconv.mongojack.domain;

import java.util.Date;

import org.mongojack.Id;

import edu.columbia.mysqltomongodbconv.mybatis.domain.Name;

public class MongoJackCustomer {
	
	@Id
	private String objectId;
	
	private Name name;
	private String email;
	private String addressId;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;

	public String getId() {
		return objectId;
	}

	public void setId(String objectId) {
		this.objectId = objectId;
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

	public String getAddressObjectId() {
		return addressId;
	}

	public void setAddressObjectId(String addressId) {
		this.addressId = addressId;
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
