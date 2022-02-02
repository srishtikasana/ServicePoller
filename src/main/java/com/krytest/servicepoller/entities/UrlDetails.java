package com.krytest.servicepoller.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlDetails {

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;

	String url;
	
	String urlName;
	
	@Column (name="user_id")
	String userId;
	
	Timestamp createdDate;
	
	Timestamp updatedDate;
	
	String Status;
	
	Timestamp LastStatusCheck;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp timestamp) {
		this.createdDate = timestamp;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Timestamp getLastStatusCheck() {
		return LastStatusCheck;
	}

	public void setLastStatusCheck(Timestamp lastStatusCheck) {
		LastStatusCheck = lastStatusCheck;
	}
	
	
	
}
