package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Medicine;
import com.example.demo.entity.Users;
import com.example.demo.model.Account;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class medicalController {
	private final HttpSession session;
	private final Account account;
	private final UserRepository userRepository;
	private final MedicineRepository medicineRepository;

	public medicalController(HttpSession session, Account account, UserRepository userRepository,
			MedicineRepository medicineRepository) {
		this.session = session;
		this.account = account;
		this.userRepository = userRepository;
		this.medicineRepository = medicineRepository;

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
		return "createUser";
	}

	@GetMapping("/insertMedicineView") // GETリクエスト
	public String insertMedicine() {
		// ユーザーが薬を登録する画面へ遷移
		return "insertMedicineView";
	}

	//ホーム画面の表示 get
	@GetMapping("/medicalView") // GETリクエスト
	public String medicalView(Model model) {
		Users user = userRepository.findById(account.getUserId()).get();
		List<Medicine> medicineList = new ArrayList<>();
		medicineList = medicineRepository.findAll();

		//データ件数分繰り返す
		int i = 0; // カウンタ
		List<Medicine> medicineList2 = new ArrayList<>();
		for (Medicine m : medicineList) {
			// ユーザIDと同じ薬データのみ保存する
			if (m.getUsers().getId() == user.getId()) {
				medicineList2.add(m);
			}
			i++;
		}
		// ユーザの服用薬一覧をタイムリーフに渡す
		model.addAttribute("medicineList", medicineList2);
		return "medicalView";
	}

	// 更新画面へ遷移する
	@GetMapping("/updateView") // GETリクエスト
	public String updateView(
			@RequestParam Integer update,
			Model model) {

		// db検索して、更新する薬の情報をhtmlに渡して、値保持とコントローラに渡して更新できるようにする
		Medicine medicine = medicineRepository.findById(update).get();
		model.addAttribute("medicine", medicine);
		return "updateView";
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

		// ユーザー登録の処理
		Users insertUser = new Users(name, password);
		insertUser = userRepository.save(insertUser);
		// sessionに登録。
		account.setUserId(insertUser.getId()); //SQL取得用にIdを取得
		account.setUserName(insertUser.getName());
		model.addAttribute("insertUser", insertUser);

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
			return "medicalLogin";
		}
		// 空文字チェックしか、してない
		//ユーザー登録処理と、セッションに値を保持して画面へ表示させるようにする。セッションはできれば2時間程度にする

		// ログインできた場合の処理を記述する
		if (userRepository.existsByNameAndPassword(name, password)) {
			// ログインできたユーザー情報をセッションに保持
			Users user = userRepository.findByNameAndPassword(name, password);
			account.setUserId(user.getId()); //SQL取得用にIdを取得
			account.setUserName(user.getName());
			// 服薬一覧を表示するためのユーザーの使用する薬一覧を取得
			// 全件取得して、iで条件分岐して削除処理をすれば、該当のものを順番にリスト代入できて、ほしい情報が取得できる
			List<Medicine> medicineList = new ArrayList<>();
			medicineList = medicineRepository.findAll();

			//データ件数分繰り返す
			int i = 0; // カウンタ
			List<Medicine> medicineList2 = new ArrayList<>();
			for (Medicine m : medicineList) {
				// ユーザIDと同じ薬データのみ保存する
				if (m.getUsers().getId() == user.getId()) {
					medicineList2.add(m);
				}
				i++;
			}
			// ユーザの服用薬一覧をタイムリーフに渡す
			model.addAttribute("medicineList", medicineList2);
			return "medicalView";
		}
		// ログインできてないときは初期画面にとりあえずもっていく
		return "medicalLogin";
	}

	// 薬登録フォーム
	@PostMapping("/insertMedicine") // POSTリクエスト
	public String insert(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") Integer count,
			@RequestParam(defaultValue = "") String text,
			Model model) {
		// 登録処理
		Users loginUser = userRepository.findById(account.getUserId()).get();
		//
		Medicine insertMedicine = new Medicine(name, text, count, false, false, false, false, new Date(1999, 10, 10),
				loginUser);
		insertMedicine = medicineRepository.save(insertMedicine);

		model.addAttribute("insertMedicine", insertMedicine);
		model.addAttribute("messege", "登録しました。");

		return "/insertMedicineView";
	}

	// deleteMedicine 薬の削除機能
	@PostMapping("/deleteMedicine") // POSTリクエスト
	public String deleteMedicine(
			@RequestParam(defaultValue = "") Integer id, // usersのid。外部キー。
			Model model) {
		// 削除
		medicineRepository.deleteById(id);
		model.addAttribute("deleteMessege", "削除しました。");
		// ホーム画面に戻る   get
		return "medicalView";
	}

	// updateMedicine 薬の更新機能 処理部分
	@PostMapping("/updateMedicine") // POSTリクエスト
	public String updateMedicine(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") Integer count, // usersのid。外部キー。
			@RequestParam(defaultValue = "") String text,
			Model model) {
		account.getUserId();
		// 更新処理

		//		medicineRepository.save();
		model.addAttribute("deleteMessege", "更新しました。");
		// ホーム画面に戻る   get
		return "medicalView";
	}

}
