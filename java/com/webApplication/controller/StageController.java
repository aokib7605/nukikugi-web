package com.webApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Images;
import com.webApplication.service.StageInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StageController {
	private final StageInfo si;
	private final Images im;
	
	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}
	
	@GetMapping("/stage")
	public String goStageInfoPage(Model model, String info) {
		si.setStageInfoModel(model, info);
		im.setPageImageModel(model, info + ".png", "フライヤー裏面");
		return goAnyPage(model, "specials/stageInfo");
	}
}
