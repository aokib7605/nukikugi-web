package com.webApplication.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	private Integer id;
	private Date date;
	private String title;
	private String content;
	private String thumbnail;
	private String link;
	private String author;
}
