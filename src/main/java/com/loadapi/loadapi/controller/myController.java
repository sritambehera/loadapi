package com.loadapi.loadapi.controller;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ReflectionUtils;

import com.loadapi.loadapi.entities.payLoad;
import com.loadapi.loadapi.services.loadService;

@RestController
public class myController {
	
	@Autowired
	private loadService loadservice;
	
	//for adding new payload
	@PostMapping("/load")
	public String addLoad(@RequestBody payLoad load) {
		return this.loadservice.addLoad(load);
	}
	
	//to get a payload by its loadId
	@GetMapping("/load/{loadId}")
	public payLoad getLoad(@PathVariable String loadId) {
		return this.loadservice.getLoad(Long.parseLong(loadId));
	}
	
	// to get payloads with a shipperId
	@GetMapping("/load")
	public List<payLoad> getLoads(@RequestParam(name = "shipperId") String id){
		return this.loadservice.getLoads(id);
//		return id;
	}
	// to update a payload with loadId
	@PutMapping("/load/{loadId}")
	public void updateLoad(@PathVariable String loadId,@RequestBody Map<Object,Object> fields ) {
		payLoad load= this.loadservice.getLoad(Long.parseLong(loadId));
		fields.forEach((k,v) ->{
			Field field=ReflectionUtils.findField(payLoad.class,(String) k);
			field.setAccessible(true);
			try {
				ReflectionUtils.setField(field,load,v);
			} catch(Exception e) {
				String tmp=v.toString();
				SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(tmp);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				Field tmp=ReflectionUtils.findField(payLoad.class, (Date)v);
				load.setDate(date);
			}
			
		});
		this.loadservice.updateLoad(load);
	}
	
	// to delete a load given by loadId
	@DeleteMapping("/load/{loadId}")
	public void deleteLoad(@PathVariable String loadId) {
		this.loadservice.deleteLoad(Long.parseLong(loadId));
	}
}
