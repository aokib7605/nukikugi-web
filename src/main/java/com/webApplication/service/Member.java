package com.webApplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.entity.Career;
import com.webApplication.repository.CareerRepository;
import com.webApplication.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Member {
	private final MemberRepository mr;
	private final CareerRepository cr;
	
	public ArrayList<com.webApplication.entity.Member> setMemberListModel(Model model){
		ArrayList<com.webApplication.entity.Member> memberList = mr.getMemberList();
		model.addAttribute("memberList", memberList);
		return memberList;
	}
	
	public com.webApplication.entity.Member setMemberModel(Model model, int id){
		com.webApplication.entity.Member member = mr.getMember(id);
		model.addAttribute("member", member);
		return member;
	}
	
	public void setCareerListModel(Model model, int id) {
		setCareerModel(model, id, "in", "St");
		setCareerModel(model, id, "in", "Mv");
		setCareerModel(model, id, "out", "St");
		setCareerModel(model, id, "out", "Mv");
	}
	
	private ArrayList<Career> setCareerModel(Model model, int id, String type1, String type2) {
		String modelName = "";
		ArrayList<Career> career = cr.getCareerList(id, type1, type2);
		modelName = type1 + "Career" + type2;
		model.addAttribute(modelName, career);
		return career;
	}
	
	public void setMemberIdModel(Model model, Integer memberId) {
		model.addAttribute("memberId", memberId);
	}
	
	public void insertCareer(Model model, Integer memberId, String type1, String type2, String title, String position) {
		String tableName = memberId + type1 + "Career" + type2;
		cr.insertCareer(tableName, title, position);
	}
	
	public void updateCareer(Model model, Integer memberId, Integer id, String type1, String type2, String title, String position) {
		String tableName = memberId + type1 + "Career" + type2;
		cr.updateCareer(id, tableName, title, position);
	}
	
	public void deleteCareer(Model model, Integer memberId, Integer id, String type1, String type2) {
		String tableName = memberId + type1 + "Career" + type2;
		cr.deleteCareer(id, tableName);
	}
	
	public void insertMember(Model model, String name, String value, String position, Integer height, String hobby, String content) {
		mr.insertMember(name, value, position, height, hobby, content);
		com.webApplication.entity.Member m = mr.getMember(value);
		createCareer(m.getId());
	}
	
	public void updateMember(Model model, int id, String name, String value, String position, Integer height, String hobby, String content) {
		mr.updateMember(id, name, value, position, height, hobby, content);
	}
	
	public void deleteMember(Model model, Integer id) {
		mr.deleteMember(id);
		dropCareer(id);
	}
	
	private void createCareer(Integer memberId) {		
		createCareer(memberId, "in", "Mv");
		createCareer(memberId, "in", "St");
		createCareer(memberId, "out", "Mv");
		createCareer(memberId, "out", "St");
	}
	
	private void createCareer(Integer memberId, String type1, String type2) {
		String tableName = memberId + type1 + "Career" + type2;
		cr.createCareer(tableName);
	}
	
	private void dropCareer(Integer memberId) {		
		dropCareer(memberId, "in", "Mv");
		dropCareer(memberId, "in", "St");
		dropCareer(memberId, "out", "Mv");
		dropCareer(memberId, "out", "St");
	}
	
	private void dropCareer(Integer memberId, String type1, String type2) {
		String tableName = memberId + type1 + "Career" + type2;
		cr.dropCareer(tableName);
	}
}
