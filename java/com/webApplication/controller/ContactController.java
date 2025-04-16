package com.webApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Contact;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContactController {
	private final Contact c;
	
	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}
	
	@PostMapping("/sendMail")
	public String sendMail(Model model, String name, String title, String contact, String content) {
		c.sendMail(name, title, contact, content);
		model.addAttribute("message", "お問い合わせを承りました。<br>お返事まで少々お待ちください。");
		return goAnyPage(model, "contact/contact");
	}

}
