package com.webApplication.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Blog;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BlogRepository {
	private final JdbcTemplate tmp;
	private String sql = "";

	public ArrayList<Blog> getBlogList(){
		sql = "select * from blog order by date";

		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setBlogList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<Blog> getBlogListByAuthor(String author){
		sql = "select * from blog where author = ? order by date";

		try {
			List<Map<String, Object>> list = tmp.queryForList(sql, author);
			return setBlogList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<String> getAuthorList(){
		sql = "select author from blog group by author order by author";
		
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setAuthorList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private ArrayList<Blog> setBlogList(List<Map<String, Object>> list){
		ArrayList<Blog> blogList = new ArrayList<Blog>();
		for(Map<String, Object> data: list) {
			blogList.add(setBlog(data));
		}
		return blogList;
	}

	private Blog setBlog(Map<String, Object> data){
		Blog b = new Blog((Integer)data.get("id"), (Date)data.get("date"), (String)data.get("title"), (String)data.get("content"), (String)data.get("thumbnail"), (String)data.get("link"), (String)data.get("author"));
		return b;
	}
	
	private ArrayList<String> setAuthorList(List<Map<String, Object>> list){
		ArrayList<String> authorList = new ArrayList<String>();
		for(Map<String, Object> data: list) {
			authorList.add((String)data.get("author"));
		}
		return authorList;
	}
	
	public void insertBlog(String date, String title, String content, String link, String thumbnail, String author) {
		sql = "insert into blog(date, title, content, thumbnail, link, author) values (?, ?, ?, ?, ?, ?)";
		
		try {
			tmp.update(sql, date, title, content, thumbnail, link, author);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateBlog(String date, String title, String content, String link, String thumbnail, String author, int id) {
		sql = "update blog set date = ?, title = ?, content = ?, link = ?, thumbnail = ?, author = ? where id = ?";
		
		try {
			tmp.update(sql, date, title, content, link, thumbnail, author, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteBlog(int id) {
		sql = "delete from blog where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
