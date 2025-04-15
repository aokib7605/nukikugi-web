package com.webApplication.service;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class News {
	private final NewsRepository nr;
	
	public void setNewsListModel(Model model) {
		model.addAttribute("newsList", nr.getNewsList());
	}
	
	public void insertNews(Model model, Date date, String headline, String link) {
		nr.insertNews(date+"", headline, link);
	}
	
	public void updateNews(Model model, int id, Date date, String headline, String link) {
		nr.updateNews(id, date+"", headline, link);
	}
	
	public void deleteNews(Model model, int id) {
		nr.deleteNews(id);
	}
}
