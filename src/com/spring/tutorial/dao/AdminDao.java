package com.spring.tutorial.dao;

import java.util.List;

import com.spring.tutorial.pojo.Admin;

public interface AdminDao {
	
	public boolean save(Admin admin);
	public List<Admin> findAll();
	public Admin findById(int id);
	public List<Admin> findByNombre(String nombre);

}
