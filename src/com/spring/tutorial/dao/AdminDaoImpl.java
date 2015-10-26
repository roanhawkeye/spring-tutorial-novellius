package com.spring.tutorial.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.tutorial.pojo.Admin;
import com.spring.tutorial.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Admin admin) {
		// TODO Auto-generated method stub
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);
		
		return jdbcTemplate.update("insert into Admin (nombre, cargo, fechaCreacion) values(:nombre, :cargo, :fechaCreacion)", paramMap) == 1;
	}

	@Override
	public List<Admin> findAll() {
		return jdbcTemplate.query("Select * from Admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				admin.setIdAd(rs.getInt("idAd"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setNombre(rs.getString("nombre"));
				return admin;
			}
		});
	}

	@Override
	public Admin findById(int id) {
//		return (Admin) jdbcTemplate.query("Select * from Admin where idAd=:idAd",
//				new MapSqlParameterSource("idAd", id), new AdminRowMapper());
		
		return jdbcTemplate.queryForObject("Select * from Admin where idAd=:idAd",
				new MapSqlParameterSource("idAd", id), new AdminRowMapper());
	}

	@Override
	public List<Admin> findByNombre(String nombre) {
		return jdbcTemplate.query("Select * from Admin where nombre like :nombre", 
				new MapSqlParameterSource("nombre", "%" + nombre + "%"), new AdminRowMapper());
	}
	
	

}
