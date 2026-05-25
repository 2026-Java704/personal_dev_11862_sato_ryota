package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Medicine;
import com.example.demo.entity.Medicine_add_history;
import com.example.demo.entity.Users;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.Medicine_add_historyRepository;
import com.example.demo.repository.UserRepository;

@Component
public class DailyTask {
	private final UserRepository userRepository;
	private final MedicineRepository medicineRepository;
	private final Medicine_add_historyRepository medicine_add_historyRepository;

	public DailyTask(UserRepository userRepository, MedicineRepository medicineRepository,
			Medicine_add_historyRepository medicine_add_historyRepository) {
		super();
		this.userRepository = userRepository;
		this.medicineRepository = medicineRepository;
		this.medicine_add_historyRepository = medicine_add_historyRepository;
	}

	// cron式で「毎日0時0分0秒」を指定
	//	@Scheduled(cron = "0 0 * * * ?", zone = "Asia/Tokyo")
	@Scheduled(cron = "0 55 23 * * ?", zone = "Asia/Tokyo")
	public void executeAtMidnight() {
		// 24時に実行される
		//履歴を保存する処理を記述したい。
		List<Users> userList = new ArrayList<>();
		List<Medicine> medicineList = new ArrayList<>();

		userList = userRepository.findByOrderByIdAsc();
		String textStr = null;

		for (Users user : userList) {

			// ユーザーIDごとの薬情報をすべて取得してきて更新
			medicineList = medicineRepository.findByUsers_Id(user.getId());
			for (Medicine m : medicineList) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formattedDate = now.format(formatter); // 例: 2026年05月25日
				//				Date date = (Date) formatter.parse(formattedDate); // errorコード　Dateは非推奨で使えてない

				//服薬済なら   日時+服薬記録+薬名前を記録
				if (m.isCheck()) {
					textStr = formattedDate + "服薬済" + m.getName();
				} else { // してなければ
					textStr = formattedDate + "未服薬" + m.getName();
				}
				Medicine_add_history history = new Medicine_add_history();
				history.setAll(
						LocalDate.parse(formattedDate), // 今の日時 記録した日時　記録する時間を当日23:55にすれば当日分の記録として使える
						textStr, // text
						user // users_id
				);
				//薬履歴テーブルに挿入　日時別に取得するとかはもう考えない。
				medicine_add_historyRepository.save(history);
				//今の薬テーブルの状態をすべて、のんでいない状態に変更する
				m.setCheck(false);
				medicineRepository.save(m);
			}
			//記録後は、薬履歴テーブルを取得して表示するのみ
		}

	}
}