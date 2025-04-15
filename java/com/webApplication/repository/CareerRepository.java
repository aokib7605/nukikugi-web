package com.webApplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Career;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CareerRepository {
	private final JdbcTemplate tmp;
	private String sql = "";
	
	public ArrayList<Career> getCareerList(int id, String type1, String type2){
		sql = "select * from " + id + type1 + "Career" + type2;
		
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setCareerList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	private ArrayList<Career> setCareerList(List<Map<String, Object>> list){
		ArrayList<Career> careerList = new ArrayList<Career>();
		for(Map<String, Object> data : list) {
			careerList.add(setCareer(data));
		}
		return careerList;
	}
	
	private Career setCareer(Map<String, Object> data) {
		Career c = new Career((Integer)data.get("id"), (String)data.get("title"), (String)data.get("position"));
		return c;
	}
	
	public void insertCareer(String tableName, String title, String position) {
		sql = "insert into " + tableName + " (title, position) values (?, ?)";

		try {
			tmp.update(sql, title, position);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateCareer(int id, String tableName, String title, String position) {
		sql = "update " + tableName + " set title = ?, position = ? where id = ?";

		try {
			tmp.update(sql, title, position, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteCareer(int id, String tableName) {
		sql = "delete from " + tableName + " where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void createCareer(String tableName) {
		sql = "create table " + tableName + " (id int auto_increment primary key, title varchar(256), position varchar(128))";
		
		try {
			tmp.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void dropCareer(String tableName) {
		sql = "drop table " + tableName;
		
		try {
			tmp.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
