package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonalDev11862SatoRyotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDev11862SatoRyotaApplication.class, args);
	}

}
// Windowsタスクスケジューラでの定期実行設定(Windows標準機能。アプリの記録のための日時処理を行うため)
// 「タスクスケジューラ」で検索。
// 右側の操作メニューから「基本タスク」の作成
// 名前とトリガーの設定。実行したい時間を選択。起動実行23:45   停止実行0:05 
// アクションの設定。「プログラムの開始」を選択して、作成した起動と停止のバッチファイルを指定。
// 「完了」を押す。

// 追加できる機能
// ・各種メール通知機能
// ・ユーザーアカウント管理機能
// ・履歴を見やすくする実装。検索機能など。
// ・運用上の機能
// ・