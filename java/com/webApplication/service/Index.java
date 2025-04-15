package com.webApplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.entity.News;
import com.webApplication.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Index {
	private final NewsRepository nr;
	
	public void setNewsListModel(Model model) {
		ArrayList<News> newsList = nr.getNewsList();
		for(int i = 0; i <= newsList.size(); i++) {
			if(newsList.size() > 5) {
				newsList.remove(5);
			}
		}
		model.addAttribute("newsList", newsList);
	}

}
