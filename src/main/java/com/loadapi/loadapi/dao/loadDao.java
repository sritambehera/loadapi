package com.loadapi.loadapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loadapi.loadapi.entities.payLoad;

public interface loadDao extends JpaRepository<payLoad, Long>{
	
}
