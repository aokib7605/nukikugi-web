package com.webApplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Images;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ImagesRepository {
	private final JdbcTemplate tmp;
	private String sql = "";
	
	public ArrayList<Images> getImageDatas(){
		sql = "SELECT * FROM images";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setImageDatas(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Images getImageData(Long id){
		sql = "SELECT * FROM images where id = ? limit 1";
		try {
			Map<String, Object> data = tmp.queryForMap(sql, id);
			return setImageData(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Images> getPageImageDatas(String page){
		sql = "SELECT * FROM images where page = ?";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql, page);
			return setImageDatas(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Images getPageImageData(String fileName, String page){
		sql = "SELECT * FROM images where fileName = ? && page = ? limit 1";
		try {
			Map<String, Object> data = tmp.queryForMap(sql, fileName, page);
			return setImageData(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Images getImageData(String fileName){
		sql = "SELECT * FROM images where fileName = ? limit 1";
		try {
			Map<String, Object> data = tmp.queryForMap(sql, fileName);
			return setImageData(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Images> setImageDatas(List<Map<String, Object>> list){
		ArrayList<Images> i = new ArrayList<Images>();
		for(Map<String, Object> data : list) {
			i.add(setImageData(data));
		}
		return i;
	}
	
	private Images setImageData(Map<String, Object> data) {
		Images i = new Images();
		i.setId((Long)data.get("id"));
		i.setFileName((String)data.get("fileName"));
		i.setFileType((String)data.get("fileType"));
		i.setPage((String)data.get("page"));
		i.setBinaryData((byte[])data.get("binaryData"));
		return i;
	}
	
	public void insertImageData(String fileName, String fileType, String page, byte[] binaryData) {
	    sql = "INSERT INTO images (fileName, fileType, page, binaryData) VALUES (?,?,?,?)";
	    try {
	    	tmp.update(sql, fileName, fileType, page, binaryData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateImageData(Long id, String fileName, String page) {
		sql = "update images set fileName = ?, page = ? where id = ?";
		try {
			tmp.update(sql, fileName, page, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteImageData(Long id) {
		sql = "delete from images where id = ?";
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
