package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class medicalController {
	private final HttpSession session;
	private final Account account;
	private final UserRepository userRepository;

	public medicalController(HttpSession session, Account account, UserRepository userRepository) {
		this.session = session;
		this.account = account;
		this.userRepository = userRepository;
	}

	@GetMapping("/") // GETリクエスト
	public String index() {
		// ログイン画面を表示する
		// セッション情報を全てクリアする
		session.invalidate();
		return "medicalLogin";
	}

	@GetMapping("/createUser") // GETリクエスト
	public String createUser() {
		// ユーザー新規登録画面を表示する
		// セッション情報を全てクリアする
		session.invalidate();
		return "createUser";
	}

	// 新規登録へ
	@PostMapping("/medicalView") // POSTリクエスト
	public String medicalView(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {
		// ユーザー登録が完了したので、ホーム画面に遷移する
		//
		// エラーチェックは空文字チェック、文字列一致チェック
		List<String> errorList = new ArrayList<>();
		if (name == null || name.length() == 0) {
			errorList.add("名前を入力してください");
		}
		if (password == null || password.length() == 0) {
			errorList.add("パスワードを入力してください");
		}
		if (errorList.size() > 0) {
			errorList.add("ユーザー登録が、できませんでした");
			model.addAttribute("errorList", errorList);
			return "/createUser";
		}
		// 空文字チェックしか、してません

		// errorをはかなかったときの登録処理を以下に記述

		return "medicalView";
	}

	// login画面のフォーム送信先
	@PostMapping("/loginCheck") // POSTリクエスト
	public String loginCheck(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String password,
			Model model) {
		// エラーチェックは空文字チェック、文字列一致チェック
		List<String> errorList = new ArrayList<>();
		if (name == null || name.length() == 0) {
			errorList.add("名前を入力してください");
		}
		if (password == null || password.length() == 0) {
			errorList.add("パスワードを入力してください");
		}
		if (errorList.size() > 0) {
			errorList.add("ログインできませんでした");
			model.addAttribute("errorList", errorList);
			// "/"が動作しない。なぜか
			return "medicalLogin";
		}
		// 空文字チェックしか、してない
		//ユーザー登録処理と、セッションに値を保持して画面へ表示させるようにする。セッションはできれば2時間程度にする

		// ログインできた場合の処理を記述する
		// userRepository.findByNameAndPassword(name, password)
		if (userRepository.existsByNameAndPassword(name, password)) {
			// ログインできたユーザー情報をセッションに保持
			//			account.setUserId(1);
			account.setUserName(name);

			return "medicalView";
		}
		// ログインできてないときは初期画面にとりあえずもっていく
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
