package com.webApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {
	private Integer id;
	private String stage;
	private String title;
	private String value;
	private String date;
	private String place;
	private String mainStaff;
	private String cast;
	private String staff;
	private String story;
	private String ticket;
	private String reservation;
	private String support;
}
