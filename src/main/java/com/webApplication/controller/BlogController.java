package com.webApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Blog;
import com.webApplication.service.Images;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogController {
	private final Blog b;
	private final Images im;
	
	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}
	
	@GetMapping("/blog")
	public String goBlogPage(Model model) {
		b.setAuthorListModel(model);
		b.setBlogListModel(model);
		im.setPageImageListModel(model, "ブログサムネイル");
		return goAnyPage(model, "blog");
	}
	
	@PostMapping("/blog")
	public String goBlogPage(Model model, String author) {
		b.setAuthorListModel(model);
		b.setBlogListModel(model, author);
		return goAnyPage(model, "blog");
	}

}
