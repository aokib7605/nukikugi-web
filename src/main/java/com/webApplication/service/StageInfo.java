package com.webApplication.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.entity.Stage;
import com.webApplication.repository.StageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StageInfo {
	private final StageRepository sr;
	
	public void setStageInfoModel(Model model, String value) {
		Stage stage = sr.getStage(value);
		model.addAttribute("stage", stage);
	}
}
