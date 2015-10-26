package com.spring.tutorial;

import java.sql.Timestamp;
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
		
		Admin admin = new Admin();
		admin.setCargo("gerente");
		admin.setNombre("Martin");
		admin.setFechaCreacion(ts);
		
		
		
		try {
			admindao.save(admin);
//			List<Admin> admins = admindao.findAll();
//			for (Admin admin2 : admins) {
//				System.out.println(admin2);
//			}
			System.out.println(admindao.findById(1));
			System.out.println(admindao.findById(2));
			System.out.println(admindao.findByNombre("j").toString());
			
			
		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
		}
		catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		
		
		((ClassPathXmlApplicationContext) applicationContext).close();
		
	}

}
