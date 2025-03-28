package com.webApplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.webApplication.entity.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	private final JdbcTemplate tmp;
	private String sql = "";

	public ArrayList<Member> getMemberList(){
		sql = "select * from member";

		try {
			List<Map<String, Object>> list = tmp.queryForList(sql);
			return setMemberList(list);	
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Member getMember(int id){
		sql = "select * from member where id = ?";
		try {
			Map<String, Object> data = tmp.queryForMap(sql, id);
			return setMember(data);	
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Member getMember(String value){
		sql = "select * from member where value = ?";
		try {
			Map<String, Object> data = tmp.queryForMap(sql, value);
			return setMember(data);	
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private ArrayList<Member> setMemberList(List<Map<String, Object>> list){
		ArrayList<Member> memberList = new ArrayList<Member>();
		for(Map<String, Object> data : list) {
			memberList.add(setMember(data));
		}
		return memberList;
	}

	private Member setMember(Map<String, Object> data) {
		int id = (int)data.get("id");
		Member m = new Member((Integer)id, (String)data.get("name"), (String)data.get("value"), (String)data.get("position"), (Integer)data.get("height"), (String)data.get("hobby"), (String)data.get("content"));
		return m;
	}
	
	public void insertMember(String name, String value, String position, Integer height, String hobby, String content) {
		sql = "insert into member (name, value, position, height, hobby, content) values (?, ?, ?, ?, ?, ?)";

		try {
			tmp.update(sql, name, value, position, height, hobby, content);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateMember(int id, String name, String value, String position, Integer height, String hobby, String content) {
		sql = "update member set name = ?, value = ?, position = ?, height = ?, hobby = ?, content = ? where id = ?";

		try {
			tmp.update(sql, name, value, position, height, hobby, content, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteMember(int id) {
		sql = "delete from member where id = ?";
		
		try {
			tmp.update(sql, id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
