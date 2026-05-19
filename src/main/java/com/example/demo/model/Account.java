package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Account {

	private String userName; // ユーザー名
	private Integer userId; // ユーザID

	public Account() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Account(String userName, Integer userId) {
		super();
		this.userName = userName;
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
