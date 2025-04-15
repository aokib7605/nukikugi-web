package com.webApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Images;
import com.webApplication.service.Index;
import com.webApplication.service.Member;
import com.webApplication.service.News;
import com.webApplication.service.Next;
import com.webApplication.service.Special;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final Index i;
	private final Member m;
	private final News n;
	private final Next nx;
	private final Special s;
	private final Images im;
	
	@GetMapping("/")
	public String welcom(Model model) {
		System.out.println("OPEN : /index");
		GetMenuList.setMenuListModel(model, "/index");
		SetCopyrightText.setCopyrightText(model);
		i.setNewsListModel(model);
		nx.setNextStage(model);
		im.setPageImageModel(model, nx.getNextStageValue() + ".png", "フライヤー表面");
		return "index";
	}
	
	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}
	
	@GetMapping("/index")
	public String goIndexPage(Model model) {
		return welcom(model);
	}
	
	@GetMapping("/about")
	public String goAboutPage(Model model) {
		return goAnyPage(model, "about");
	}
	
	@GetMapping("/member")
	public String goMemberPage(Model model) {
		m.setMemberListModel(model);
		im.setPageImageListModel(model, "メンバー");
		return goAnyPage(model, "member/member");
	}
	
	@GetMapping("/news")
	public String goNewsPage(Model model) {
		n.setNewsListModel(model);
		return goAnyPage(model, "news");
	}
	
	@GetMapping("/next")
	public String goNextPage(Model model) {
		nx.setNextStage(model);
		im.setPageImageModel(model, nx.getNextStageValue() + ".png", "フライヤー表面");
		return goAnyPage(model, "next");
	}
	
	@GetMapping("/special")
	public String goSpecialPage(Model model) {
		s.setStageListModel(model);
		s.setMovieListModel(model);
		im.setPageImageListModel(model, "作品サムネイル");
		return goAnyPage(model, "specials/special");
	}
	
	@GetMapping("/contact")
	public String goContactPage(Model model) {
		return goAnyPage(model, "contact/contact");
	}
}
