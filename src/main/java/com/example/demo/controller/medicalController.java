package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class medicalController {
	@GetMapping("/") // GETリクエスト
	public String index() {
		// ログイン画面を表示する
		return "medicalLogin";
	}

	@GetMapping("/t") // GETリクエスト
	public String str() {
		return "medicalView";
	}

	@GetMapping("/a") // GETリクエスト
	public String s() {
		return "medicalInsert";
	}
}
