package com.krytest.servicepoller.services;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.krytest.servicepoller.entities.UrlDetails;
import com.krytest.servicepoller.models.AddUrlRequest;
import com.krytest.servicepoller.models.DeleteUrlRequest;
import com.krytest.servicepoller.models.EditUrlRequest;
import com.krytest.servicepoller.repositories.UrlDetailsRepo;


@Service
public class UrlService {

	@Autowired
	UrlDetailsRepo urlDetailsRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	//list URLs for Dashboard
	public UrlDetails[] listUrl(String listUserId) {
		UrlDetails[] urlDetailsArray=urlDetailsRepo.findByUserId(listUserId);
		return urlDetailsArray ;
	}
	
	//Get data for edit and Delete
	public UrlDetails getUrlDetails(long urlId) {
		Optional<UrlDetails> urlDetailOptional= urlDetailsRepo.findById(urlId);
		if(urlDetailOptional.isPresent()) {
			UrlDetails urlDetail=urlDetailOptional.get();
			return urlDetail;
		}
		return new UrlDetails();
		
	}
	
	//save new url data
	public void addUrl(AddUrlRequest newUrl) {
		UrlDetails urlDetail=new UrlDetails();
		urlDetail.setUrl(newUrl.getUrl());
		urlDetail.setUrlName(newUrl.getUrlName());
		urlDetail.setUserId(newUrl.getUserId());
		urlDetail.setCreatedDate(new java.sql.Timestamp(new Date().getTime()));
		urlDetail.setUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
		this.urlDetailsRepo.save(urlDetail);
	}
	
	//save edited url
	public void editUrl(EditUrlRequest editUrl) {
		Optional<UrlDetails> urlDetailsOptional = urlDetailsRepo.findById(editUrl.getId());
		if(urlDetailsOptional.isPresent()) {
			UrlDetails urlDetails = urlDetailsOptional.get();
			urlDetails.setUrl(editUrl.getUrl());
			urlDetails.setUrlName(editUrl.getUrlName());
			urlDetails.setUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
			this.urlDetailsRepo.save(urlDetails);
		}
		
	}
	
	//delete url
	public void deleteUrl(DeleteUrlRequest deleteUrl) {
		UrlDetails urlDetail=new UrlDetails();
		urlDetail.setId(deleteUrl.getId());
		this.urlDetailsRepo.delete(urlDetail);;
	}
	
	//check url connection status and save to database
	public boolean statusCheck(Long id) {
		try {
		Optional<UrlDetails> urlDetails = this.urlDetailsRepo.findById(id);
		if(urlDetails.isPresent()) {
			UrlDetails urlDetail = urlDetails.get();
			ResponseEntity<String> result = restTemplate.getForEntity(URI.create(urlDetail.getUrl()), String.class);
			if(result.getStatusCodeValue() == HttpStatus.OK.value()) {
				urlDetail.setStatus("OK");
				urlDetail.setLastStatusCheck(new java.sql.Timestamp(new Date().getTime()));
				this.urlDetailsRepo.save(urlDetail);
				return true;
			}else {
				urlDetail.setStatus("FAIL");
				urlDetail.setLastStatusCheck(new java.sql.Timestamp(new Date().getTime()));
				this.urlDetailsRepo.save(urlDetail);
				return false;
			}
		}
	}catch(DataAccessException  e) {
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
		}
		return false;
	}


}
