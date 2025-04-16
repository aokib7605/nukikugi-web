package com.webApplication.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Contact {
	private final JavaMailSender mailSender;
	
	public void sendMail(String name, String title, String contact, String content) {
		//build.gradleに下記の記述が必要
		/*
		plugins {
			    id 'org.springframework.boot' version '3.2.4'
			    id 'io.spring.dependency-management' version '1.1.4'
			    id 'java'
		}
		group = 'com.example'
		version = '0.0.1-SNAPSHOT'
		sourceCompatibility = '17'  // Javaバージョンに応じて調整
		
		repositories {
		    mavenCentral()
		}
		dependencies {
		    implementation 'org.springframework.boot:spring-boot-starter-mail'
		    implementation 'org.springframework.boot:spring-boot-starter'
		}
		*/
		//application.propertiesに下記の記述が必要
		/*
		spring.mail.host=smtp.gmail.com
		spring.mail.port=587
		spring.mail.username=nukikugi@gmail.com
		spring.mail.password=kizm hdxv lpyr fbqx
		spring.mail.properties.mail.smtp.auth=true
		spring.mail.properties.mail.smtp.starttls.enable=true
		*/
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("nukikugi@gmail.com");
		message.setFrom("nukikugi@gmail.com");
		message.setSubject("ホームページよりお問い合わせがありました");
		message.setText(
				"件名：" + title + "\n" +
				"お名前：" + name + "\n" +
				"連絡先：" + contact + "\n" +
				"内容：" + content + "\n"
				);

		// メール送信を実施する。
		mailSender.send(message);
	}
}
