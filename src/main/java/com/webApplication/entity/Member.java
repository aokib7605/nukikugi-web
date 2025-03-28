package com.webApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private Integer id;
	private String name;
	private String value;
	private String position;
	private Integer height;
	private String hobby;
	private String content;
}
