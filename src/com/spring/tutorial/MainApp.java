package com.spring.tutorial;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.spring.tutorial.dao.AdminDao;
import com.spring.tutorial.pojo.Admin;

public class MainApp {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		
		AdminDao admindao = (AdminDao) applicationContext.getBean("adminDao");
		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		
		try {
			List<Admin> admins = new ArrayList<Admin>();
			
			admins.add(new Admin(30, "pedro", "jefe de ingenieria", ts));
			admins.add(new Admin(31, "jorge", "subjefe de ingenieria", ts));
			admins.add(new Admin(32, "maria", "representante legal", ts));
			
			int[] vals = admindao.saveAll(admins);
			
			for (int i : vals) {
				System.out.println("filas afectadas para esta operacion: " + i);
			}
					
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
		}
		catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		
		
		((ClassPathXmlApplicationContext) applicationContext).close();
		
	}

}
