package com.webApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Images {
	private Long id;
	private String fileName;
	private String fileType;
	private String page;
	private byte[] binaryData;
}
