package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine_add_history")
public class Medicine_add_history {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "history_date")
	private LocalDate historyDate;

	private String text;

	// ★ 外部キーの設定   多側のエンティティの記述 users表の主キー(id)を参照するには、「users_id」で正解。
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // referencedColumnNameを省略した場合は主キーが参照される。自身のそのフィールドは外部キーとして指定する、という意味だけで参照してくれる。なのでuser_idフィールドにたいして、user_idを指定する。
	private Users users;

	public Medicine_add_history() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Medicine_add_history(Integer id, LocalDate historyDate, String text, Users users) {
		super();
		this.id = id;
		this.historyDate = historyDate;
		this.text = text;
		this.users = users;
	}

	public Medicine_add_history(LocalDate historyDate, String text, Users users) {
		super();
		this.historyDate = historyDate;
		this.text = text;
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(LocalDate historyDate) {
		this.historyDate = historyDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setAll(LocalDate historyDate, String text, Users users) {
		this.historyDate = historyDate;
		this.text = text;
		this.users = users;
	}

}
