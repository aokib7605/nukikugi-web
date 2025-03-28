package com.webApplication.service;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Blog {
	private final BlogRepository br;
	
	public void setBlogListModel(Model model) {
		model.addAttribute("blogList", br.getBlogList());
	}
	
	public void setBlogListModel(Model model, String author) {
		model.addAttribute("blogList", br.getBlogListByAuthor(author));
	}
	
	public void setAuthorListModel(Model model) {
		model.addAttribute("authorList", br.getAuthorList());
	}
	
	public void insertBlog(Date date, String title, String link, String thumbnail, String author, String content) {
		br.insertBlog(date+"", title, content, link, thumbnail, author);
	}
	
	public void updateBlog(int id,Date date, String title, String link, String thumbnail, String author, String content) {
		br.updateBlog(date+"", title, content, link, thumbnail, author, id);
	}
	
	public void deleteBlog(int id) {
		br.deleteBlog(id);
	}
}
