package com.webApplication.functions;

import org.springframework.ui.Model;

public class SetCopyrightText {
	public static void setCopyrightText(Model model) {
		model.addAttribute("copyright", "Copyright @2024 Nukinikui Kugi All Rights Reserved.");
	}
}
