package com.example.demo.controller;

/*
 * p.31
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // コントローラを表すアノテーション
public class HelloController {

	@GetMapping("/") // GETリクエスト
	public String index() {
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

	//	@GetMapping({ "/", "/test" }) // GETリクエストを受け取るURLを指定 ("/") // URLパターンの追記({ "/", "/test" })
	//	public String index() {
	//		// hello.htmlを出力する
	//		return "hello";
	//	}
	//
	//	// 「http://localhost:8080/hello?msg=AAA」のURLパターンを処理する
	//	@GetMapping("/hello")
	//	public String hello(@RequestParam String msg, Model model) {
	//		// msgの引数は必須。未入力のパラメータを処理できるようにする。P62～
	//		// import org.springframework.ui.Model
	//		// 画面に情報を渡す
	//		model.addAttribute("memo", msg);
	//		// hello.htmlの出力
	//		return "hello";
	//	}
	//
	//	@PostMapping("/hello")
	//	public String helloByPost(@RequestParam String msg, Model model) {
	//		// import org.springframework.ui.Model
	//		// 画面に情報を渡す
	//		model.addAttribute("memo", msg);
	//		// helloPost.htmlの出力
	//		return "helloPost";
	//	}

}
