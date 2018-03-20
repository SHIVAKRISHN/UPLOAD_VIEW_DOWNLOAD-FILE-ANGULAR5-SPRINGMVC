package com.shiva.UploadFile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	int id;

	@Column(name="name")
	String name;

	@Column(name="emailId")
	String emailId;

	@Column(name="mobileNumber")
	String mobileNumber;

	@Column(name="gender")
	String gender;

	@Column(name="imageName")
	String imageName;

	@Column(name="doucmentName")
	String doucmentName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDoucmentName() {
		return doucmentName;
	}

	public void setDoucmentName(String doucmentName) {
		this.doucmentName = doucmentName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber
				+ ", gender=" + gender + ", imageName=" + imageName + ", doucmentName=" + doucmentName + "]";
	}

}
