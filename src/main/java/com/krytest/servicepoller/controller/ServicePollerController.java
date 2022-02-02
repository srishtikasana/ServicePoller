package com.krytest.servicepoller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.krytest.servicepoller.entities.UrlDetails;
import com.krytest.servicepoller.models.AddUrlRequest;
import com.krytest.servicepoller.models.DeleteUrlRequest;
import com.krytest.servicepoller.models.EditUrlRequest;
import com.krytest.servicepoller.services.UrlService;

@RestController
@RequestMapping("/api/servicepoller")
@CrossOrigin(origins = "*")
public class ServicePollerController {
	
	@Autowired
	UrlService urlService;
	
	//Get All urls with Details for Dashboard based on user id
	@PostMapping("/listurls")
	public ResponseEntity<String> listUrl(@RequestBody String userId)
	{
		
		UrlDetails[] urlList;
		urlList=this.urlService.listUrl(userId);
		JsonMapper mapper = new JsonMapper();
		try {
			
			String responseStr=mapper.writeValueAsString(urlList);
			return ResponseEntity.ok(responseStr);
		
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	//Get url with Details for populating edit or delete screens based on url id
	@PostMapping("/geturl")
	public ResponseEntity<?> getUrl(@RequestBody String urlIdStr)
	{
		
		long urlId=Long.parseLong(urlIdStr);
		UrlDetails urlDetail;
		urlDetail=this.urlService.getUrlDetails(urlId);
		JsonMapper mapper = new JsonMapper();
		try {
			
			String responseStr=mapper.writeValueAsString(urlDetail);
			return ResponseEntity.ok(responseStr);
		
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
		
	}
	
	//save new url from add screen
	@PostMapping("/addurl")
	public ResponseEntity<?> addUrl(@RequestBody AddUrlRequest addUrl)
	{
		this.urlService.addUrl(addUrl);
		return ResponseEntity.ok("");
	}
	
	//save changed url from edit screen
	@PostMapping("/editurl")
	public ResponseEntity<?> editUrl(@RequestBody EditUrlRequest editUrl)
	{
		this.urlService.editUrl(editUrl);
		return ResponseEntity.ok("");
	}
	
	//delete url from delete screen
	@PostMapping("/deleteurl")
	public ResponseEntity<?> deleteUrl(@RequestBody DeleteUrlRequest deleteUrl)
	{
		this.urlService.deleteUrl(deleteUrl);
		return ResponseEntity.ok("");
	}
	
	//check url connection status
	@GetMapping("/urls/{id}/status")
	public ResponseEntity<?> statusCheck(@PathVariable Long id){
		boolean result = this.urlService.statusCheck(id);
		return ResponseEntity.ok(result);
	}
	
}
