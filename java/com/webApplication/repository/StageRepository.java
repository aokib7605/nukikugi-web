package com.webApplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Stage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StageRepository {
	private final JdbcTemplate tmp;
	private String sql = "";

	public ArrayList<Stage> getStageList(){
		sql = "select * from stage";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setStageList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<Stage> getStageListByOffset(int offset){
		sql = "select * from stage limit 3 offset ?";
		try {
			List<Map<String, Object>> list = tmp.queryForList(sql, offset);
			return setStageList(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Stage getStage(int id) {
		sql = "select * from stage where id = ?";
		try {
			Map<String, Object> list = tmp.queryForMap(sql, id);
			return setStage(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Stage getStage(String value) {
		sql = "select * from stage where value = ? limit 1";
		try {
			Map<String, Object> list = tmp.queryForMap(sql, value);
			return setStage(list);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private ArrayList<Stage> setStageList(List<Map<String, Object>> list){
		ArrayList<Stage> stageList = new ArrayList<Stage>();
		for(Map<String, Object> data : list) {
			stageList.add(setStage(data));
		}
		return stageList;
	}

	private Stage setStage(Map<String, Object> data){
		Stage s = new Stage((Integer)data.get("id"), (String)data.get("stage"), (String)data.get("title"), (String)data.get("value"), (String)data.get("date"), (String)data.get("place"), (String)data.get("mainStaff"), (String)data.get("cast"), (String)data.get("staff"), (String)data.get("story"), (String)data.get("ticket"), (String)data.get("reservation"), (String)data.get("support"));
		return s;
	}
	
	public void insertStage(String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support) {
		sql = "insert into stage(stage, title,  value, date, place, mainStaff, cast, staff, story, ticket, reservation, support) values (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			tmp.update(sql, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateStage(int id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support) {
		sql = "update stage set stage = ?, title = ?, value = ?, date = ?, place = ?, mainStaff = ?, cast = ?, staff = ?, story = ?, ticket = ?, reservation = ?, support = ? where id = ?";

		try {
			tmp.update(sql, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteStage(int id) {
		sql = "delete from stage where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
