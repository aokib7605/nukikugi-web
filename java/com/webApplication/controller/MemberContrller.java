package com.webApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Images;
import com.webApplication.service.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberContrller {
	private final Member m;
	private final Images im;
	
	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}
	
	@GetMapping("/member/info")
	public String goMemberInfoPage(Model model, String nukikugi, int id, String fileName) {
		m.setMemberModel(model, id);
		m.setCareerListModel(model, id);
		im.setPageImageModel(model, fileName + ".png", "メンバー");
		return goAnyPage(model, "member/memberInfo");
	}
}
