package com.webApplication.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.News;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NewsRepository {
	private final JdbcTemplate tmp;
	private String sql = "";
	
	public ArrayList<News> getNewsList(){
		sql = "select * from news order by date";
		
		List<Map<String, Object>> list = tmp.queryForList(sql);
		return setNewsList(list);
	}
	
	public News getNews(int id){
		sql = "select * from news where id = ? limit 1";
		
		Map<String, Object> data = tmp.queryForMap(sql);
		return setNews(data);
	}
	
	private ArrayList<News> setNewsList(List<Map<String, Object>> list) {
		ArrayList<News> newsList = new ArrayList<News>();
		for(Map<String, Object> data: list) {
			newsList.add(setNews(data));
		}
		return newsList;
	}
	
	private News setNews(Map<String, Object> data) {
		News n = new News((Integer)data.get("id"), (Date)data.get("date"), (String)data.get("headline"), (String)data.get("content"), (String)data.get("link"));
		return n;
	}
	
	public void insertNews(String date, String headline, String link) {
		sql = "insert news (date, headline, link) values (?, ?, ?)";
		
		try {
			tmp.update(sql, date, headline, link);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateNews(int id, String date, String headline, String link) {
		System.out.println(date);
		sql = "update news set date = ?, headline = ?, link = ? where id = ?";
		
		try {
			tmp.update(sql, date, headline, link, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteNews(int id) {
		sql = "delete from news where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
