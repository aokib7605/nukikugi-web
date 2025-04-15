package com.webApplication.controller;

import java.sql.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webApplication.functions.GetMenuList;
import com.webApplication.functions.SetCopyrightText;
import com.webApplication.service.Blog;
import com.webApplication.service.Images;
import com.webApplication.service.Member;
import com.webApplication.service.News;
import com.webApplication.service.Special;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SetInformationsController {
	private final News n;
	private final Blog b;
	private final Special s;
	private final Member m;
	private final Images i;
	private final HttpSession session;

	private String goAnyPage(Model model, String name) {
		System.out.println("OPEN : " + name);
		GetMenuList.setGroupMenuListModel(model, name);
		SetCopyrightText.setCopyrightText(model);
		return name;
	}

	@GetMapping("/group/login")
	public String goLoginPage(Model model) {
		return goAnyPage(model, "tools/login");
	}

	@GetMapping("/logout")
	public String goLogoutPage(Model model) {
		session.invalidate();
		model.addAttribute("message", "ログアウトしました");
		return goLoginPage(model);
	}

	@GetMapping("/group/setInformations")
	public String goSetInformationPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		return goAnyPage(model, "tools/setInformations");
	}

	@PostMapping("/group/setInformations")
	public String loginSetInformationPage(Model model, String id, String password) {
		if(!id.equals("nukikugi")) {
			model.addAttribute("message", "IDが間違っています");
			return goLoginPage(model);
		}
		if(!password.equals("Nukikugi2016")) {
			model.addAttribute("message", "パスワードが間違っています");
			return goLoginPage(model);
		}
		session.setAttribute("login", true);
		return goAnyPage(model, "tools/setInformations");
	}

	@GetMapping("/group/news")
	public String goNewsPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		n.setNewsListModel(model);
		return goAnyPage(model, "tools/news");
	}

	@PostMapping("/group/news")
	public String updateNewsTable(Model model, String mode, Integer id, Date date, String headline, String link) {
		if(mode.equals("insert")) {
			n.insertNews(model, date, headline, link);
		}
		if(mode.equals("update")) {
			n.updateNews(model, id, date, headline, link);
		}
		if(mode.equals("delete")) {
			n.deleteNews(model, id);
		}
		return goNewsPage(model);
	}

	@GetMapping("/group/blog")
	public String goBlogPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		b.setAuthorListModel(model);
		b.setBlogListModel(model);
		return goAnyPage(model, "tools/blog");
	}

	@PostMapping("/group/blog")
	public String updateBlogTable(Model model, String mode, Integer id, Date date, String title, String link, String thumbnail, String author, String content) {
		if(mode.equals("insert")) {
			b.insertBlog(date, title, link, thumbnail, author, content);
		}
		if(mode.equals("update")) {
			b.updateBlog(id, date, title, link, thumbnail, author, content);
		}
		if(mode.equals("delete")) {
			b.deleteBlog(id);
		}
		return goBlogPage(model);
	}

	@GetMapping("/group/movie")
	public String goMoviePage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		s.setMovieListModel(model);
		return goAnyPage(model, "tools/movie");
	}

	@PostMapping("/group/movie")
	public String updateMovieTable(Model model, String mode, Integer id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support, String link) {
		if(mode.equals("info")) {
			s.setMovieModel(model, id);
		}
		if(mode.equals("insert")) {
			s.insertMovie(model, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link);
		}
		if(mode.equals("update")) {
			s.updateMovie(model, id, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link);
		}
		if(mode.equals("delete")) {
			s.deleteMovie(model, id);
		}
		return goMoviePage(model);
	}

	@GetMapping("/group/stage")
	public String goStagePage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		s.setStageListModel(model);
		return goAnyPage(model, "tools/stage");
	}

	@PostMapping("/group/stage")
	public String updateStageTable(Model model, String mode, Integer id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support) {
		if(mode.equals("info")) {
			s.setStageModel(model, id);
		}
		if(mode.equals("insert")) {
			s.insertStage(model, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support);
		}
		if(mode.equals("update")) {
			s.updateStage(model, id, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support);
		}
		if(mode.equals("delete")) {
			s.deleteStage(model, id);
		}
		return goStagePage(model);
	}

	@GetMapping("/group/inMv")
	public String goInMvPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		m.setMemberListModel(model);
		return goAnyPage(model, "tools/inMv");
	}

	@PostMapping("/group/inMv")
	public String updateInMvTable(Model model, String mode, Integer memberId, Integer id, String type1, String type2, String title, String position) {
		m.setMemberIdModel(model, memberId);
		if(mode.equals("info")) {
			m.setCareerListModel(model, memberId);
		}
		if(mode.equals("insert")) {
			m.insertCareer(model, memberId, type1, type2, title, position);
		}
		if(mode.equals("update")) {
			m.updateCareer(model, memberId, id, type1, type2, title, position);
		}
		if(mode.equals("delete")) {
			m.deleteCareer(model, memberId, id, type1, type2);
		}
		return goInMvPage(model);
	}

	@GetMapping("/group/inSt")
	public String goInStPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		m.setMemberListModel(model);
		return goAnyPage(model, "tools/inSt");
	}

	@PostMapping("/group/inSt")
	public String updateInStTable(Model model, String mode, Integer memberId, Integer id, String type1, String type2, String title, String position) {
		m.setMemberIdModel(model, memberId);
		if(mode.equals("info")) {
			m.setCareerListModel(model, memberId);
		}
		if(mode.equals("insert")) {
			m.insertCareer(model, memberId, type1, type2, title, position);
		}
		if(mode.equals("update")) {
			m.updateCareer(model, memberId, id, type1, type2, title, position);
		}
		if(mode.equals("delete")) {
			m.deleteCareer(model, memberId, id, type1, type2);
		}
		return goInStPage(model);
	}

	@GetMapping("/group/outMv")
	public String goOutMvPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		m.setMemberListModel(model);
		return goAnyPage(model, "tools/outMv");
	}

	@PostMapping("/group/outMv")
	public String updateOutMvTable(Model model, String mode, Integer memberId, Integer id, String type1, String type2, String title, String position) {
		m.setMemberIdModel(model, memberId);
		if(mode.equals("info")) {
			m.setCareerListModel(model, memberId);
		}
		if(mode.equals("insert")) {
			m.insertCareer(model, memberId, type1, type2, title, position);
		}
		if(mode.equals("update")) {
			m.updateCareer(model, memberId, id, type1, type2, title, position);
		}
		if(mode.equals("delete")) {
			m.deleteCareer(model, memberId, id, type1, type2);
		}
		return goOutMvPage(model);
	}

	@GetMapping("/group/outSt")
	public String goOutStPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		m.setMemberListModel(model);
		return goAnyPage(model, "tools/outSt");
	}

	@PostMapping("/group/outSt")
	public String updateOutStTable(Model model, String mode, Integer memberId, Integer id, String type1, String type2, String title, String position) {
		m.setMemberIdModel(model, memberId);
		if(mode.equals("info")) {
			m.setCareerListModel(model, memberId);
		}
		if(mode.equals("insert")) {
			m.insertCareer(model, memberId, type1, type2, title, position);
		}
		if(mode.equals("update")) {
			m.updateCareer(model, memberId, id, type1, type2, title, position);
		}
		if(mode.equals("delete")) {
			m.deleteCareer(model, memberId, id, type1, type2);
		}
		return goOutStPage(model);
	}

	@GetMapping("/group/member")
	public String goMemberPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		m.setMemberListModel(model);
		return goAnyPage(model, "tools/member");
	}

	@PostMapping("/group/member")
	public String updateMemberTable(Model model, String mode, Integer id, String name, String value, String position, Integer height, String hobby, String content) {
		if(mode.equals("info")) {
			m.setMemberModel(model, id);
		}
		if(mode.equals("insert")) {
			m.insertMember(model, name, value, position, height, hobby, content);
		}
		if(mode.equals("update")) {
			m.updateMember(model, id, name, value, position, height, hobby, content);
		}
		if(mode.equals("delete")) {
			m.deleteMember(model, id);
		}
		return goMemberPage(model);
	}

	@GetMapping("/group/images")
	public String goImagesPage(Model model) {
		if(session.getAttribute("login") == null){
			return goLogoutPage(model);
		}
		i.setImageListModel(model);
		return goAnyPage(model, "tools/images");
	}

	@GetMapping("/images/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		com.webApplication.entity.Images image = i.getImage(id);
		if (image != null) {
			return ResponseEntity.ok()
					.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, image.getFileType())
					.body(image.getBinaryData());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/images/member/{fileName}")
	public ResponseEntity<byte[]> getMemberImage(@PathVariable String fileName) {
		return getImage(fileName, "メンバー");
	}
	
	@GetMapping("/images/flyer1/{fileName}")
	public ResponseEntity<byte[]> getFlyer1Image(@PathVariable String fileName) {
		return getImage(fileName, "フライヤー表面");
	}
	
	@GetMapping("/images/flyer2/{fileName}")
	public ResponseEntity<byte[]> getFlyer2Image(@PathVariable String fileName) {
		return getImage(fileName, "フライヤー裏面");
	}
	
	@GetMapping("/images/workThumnail/{fileName}")
	public ResponseEntity<byte[]> getWorkThumnailImage(@PathVariable String fileName) {
		return getImage(fileName, "作品サムネイル");
	}
	
	@GetMapping("/images/blogThumnail/{fileName}")
	public ResponseEntity<byte[]> getBlogThumnailImage(@PathVariable String fileName) {
		return getImage(fileName, "ブログサムネイル");
	}
	
	private ResponseEntity<byte[]> getImage(String fileName, String page) {
		com.webApplication.entity.Images image = i.getImage(fileName, page);
		if (image != null) {
			return ResponseEntity.ok()
					.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, image.getFileType())
					.body(image.getBinaryData());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/group/images")
	public String updateImages(Model model, String mode, Long id, String fileName, String page) {
		if(mode.equals("update")) {
			i.updateImageData(id, fileName, page);
		}
		if(mode.equals("delete")) {
			i.deleteImageData(id);
		}
		return goImagesPage(model);
	}
	@PostMapping("/group/upload")
	public String uploadImages(Model model, String mode, Long id, @RequestParam("image") MultipartFile file, String fileName, String page) {
		if(mode.equals("upload")) {
			i.uploadImage(file, model, page);
		}
		return goImagesPage(model);
	}
}
