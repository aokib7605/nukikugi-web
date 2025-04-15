package com.webApplication.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.entity.Stage;
import com.webApplication.repository.StageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Next {
	private final StageRepository sr;

	public void setNextStage(Model model) {
		model.addAttribute("nextStage", sr.getStage(getLatestStageId()));
	}
	
	public String getNextStageValue() {
		Stage stage = sr.getStage(getLatestStageId());
		return stage.getValue();
	}

	private int getLatestStageId() {
		int latestStageId = sr.getStageList().size();
		return latestStageId;
	}
}
