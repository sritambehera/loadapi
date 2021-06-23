package com.loadapi.loadapi.services;

import java.util.List;

import com.loadapi.loadapi.entities.payLoad;

public interface loadService {
	
	public String addLoad(payLoad load);
	public payLoad getLoad(long loadId);
	public List<payLoad> getLoads(String id);
	public void updateLoad(payLoad load);
	public void deleteLoad(long loadId);
}
