package com.ctol.bench.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	@Id
	private ObjectId _id;

	@NotNull(message = "Email is compulsory")
	@Indexed(unique = true)
	@Email(message = "Email doesn't match required pattern")
	private String email;

	private String username;

	@NotNull(message = "Password is compulsory")
	private String password;

	@Indexed(unique = true)
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User() {
	}

	public User(ObjectId _id, String username, String password) {
		this._id = _id;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getObjectId() {
		return this._id.toHexString();
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setObjectId(ObjectId _id) {
		this._id = _id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
