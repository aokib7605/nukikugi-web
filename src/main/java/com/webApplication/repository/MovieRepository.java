package com.webApplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Movie;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
	private final JdbcTemplate tmp;
	private String sql = "";

	public ArrayList<Movie> getMovieList(){
		sql = "select * from movie";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setMovieList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<Movie> getMovieListByOffset(int offset){
		sql = "select * from movie limit 3 offset ?";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql, offset);
			return setMovieList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Movie getMovie(int id) {
		sql = "select * from movie where id = ?";
		try {
			Map<String, Object> list = tmp.queryForMap(sql, id);
			return setMovie(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Movie getMovie(String value) {
		sql = "select * from movie where value = ? limit 1";
		try {
			Map<String, Object> list = tmp.queryForMap(sql, value);
			return setMovie(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private ArrayList<Movie> setMovieList(List<Map<String, Object>> list){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		for(Map<String, Object> data : list) {
			movieList.add(setMovie(data));
		}
		return movieList;
	}

	private Movie setMovie(Map<String, Object> data){
		Movie m = new Movie((Integer)data.get("id"), (String)data.get("stage"), (String)data.get("title"), (String)data.get("value"), (String)data.get("date"), (String)data.get("place"), (String)data.get("mainStaff"), (String)data.get("cast"), (String)data.get("staff"), (String)data.get("story"), (String)data.get("ticket"), (String)data.get("reservation"), (String)data.get("support"), (String)data.get("link"));
		return m;
	}

	public void insertMovie(String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support, String link) {
		sql = "insert into movie(stage, title,  value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			tmp.update(sql, stage, title,  value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateMovie(int id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support, String link) {
		sql = "update movie set stage = ?, title = ?, value = ?, date = ?, place = ?, mainStaff = ?, cast = ?, staff = ?, story = ?, ticket = ?, reservation = ?, support = ?, link = ? where id = ?";

		try {
			tmp.update(sql, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteMovie(int id) {
		sql = "delete from movie where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
