package com.webApplication.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
	private Integer id;
	private Date date;
	private String headline;
	private String content;
	private String link;
}
