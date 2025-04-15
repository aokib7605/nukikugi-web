package com.webApplication.functions;

import java.util.ArrayList;

import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GetMenuList {
	
	public static void setMenuListModel(Model model, String mode) {
		model.addAttribute("menuList", getMenuList(mode));
	}
	
	public static void setGroupMenuListModel(Model model, String mode) {
		model.addAttribute("menuList", getGroupMenuList());
	}
	
	private static ArrayList<MenuList> getMenuList(String mode){
		ArrayList<MenuList> menuList = new ArrayList<MenuList>();
		int i = 0;
		if(mode.equals("/index")) {
			menuList.add(setMenuList(i, "Home", null));
		} else {
			menuList.add(setMenuList(i, "Home", "/index"));
		}
		i++;
		if(mode.equals("/about")) {
			menuList.add(setMenuList(i, "About", null));			
		} else {
			menuList.add(setMenuList(i, "About", "/about"));
		}
		i++;
		if(mode.equals("/member/member")) {
			menuList.add(setMenuList(i, "Member", null));			
		} else {
			menuList.add(setMenuList(i, "Member", "/member"));
		}
		i++;
		if(mode.equals("/news")) {
			menuList.add(setMenuList(i, "News", null));		
		} else {
			menuList.add(setMenuList(i, "News", "/news"));
		}
		i++;
		if(mode.equals("/next")) {
			menuList.add(setMenuList(i, "Next", null));		
		} else {
			menuList.add(setMenuList(i, "Next", "/next"));
		}
		i++;
		if(mode.equals("/specials/special")) {
			menuList.add(setMenuList(i, "作品展示", null));		
		} else {
			menuList.add(setMenuList(i, "作品展示", "/special"));
		}
		i++;
		if(mode.equals("/blog")) {
			menuList.add(setMenuList(i, "活動報告", null));		
		} else {
			menuList.add(setMenuList(i, "活動報告", "/blog"));
		}
		i++;
		if(mode.equals("/contact/contact")) {
			menuList.add(setMenuList(i, "お問い合わせ", null));		
		} else {
			menuList.add(setMenuList(i, "お問い合わせ", "/contact"));
		}
		return menuList;
	}
	
	private static ArrayList<MenuList> getGroupMenuList(){
		ArrayList<MenuList> menuList = new ArrayList<MenuList>();
		int i = 0;
		menuList.add(setMenuList(i, "団体メニュートップ", "/group/setInformations"));i++;
		menuList.add(setMenuList(i, "ニュース", "/group/news"));i++;
		menuList.add(setMenuList(i, "ブログ", "/group/blog"));i++;
		menuList.add(setMenuList(i, "映像作品", "/group/movie"));i++;
		menuList.add(setMenuList(i, "演劇作品", "/group/stage"));i++;
		menuList.add(setMenuList(i, "内部映像経歴", "/group/inMv"));i++;
		menuList.add(setMenuList(i, "内部舞台経歴", "/group/inSt"));i++;
		menuList.add(setMenuList(i, "外部映像経歴", "/group/outMv"));i++;
		menuList.add(setMenuList(i, "外部舞台経歴", "/group/outSt"));i++;
		menuList.add(setMenuList(i, "メンバー", "/group/member"));i++;
		menuList.add(setMenuList(i, "画像管理", "/group/images"));
		return menuList;
	}
	
	private static MenuList setMenuList(Integer id, String name, String value) {
		MenuList ml = new MenuList(id, name, value);
		return ml;
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class MenuList{
	private Integer id;
	private String name;
	private String value;
}