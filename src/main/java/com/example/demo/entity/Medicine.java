package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine")
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String text;

	private Integer count;

	// SQLでは、checkが予約語
	@Column(name = "medicine_check")
	private boolean check;

	@Column(name = "morning_check")
	private boolean morningCheck;

	@Column(name = "daytime_check")
	private boolean daytimeCheck;

	@Column(name = "night_check")
	private boolean nightCheck;

	@Column(name = "lastdate")
	private Date lastDate;

	// ★ 外部キーの設定   多側のエンティティの記述 users表の主キー(id)を参照するには、「users_id」で正解。
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // referencedColumnNameを省略した場合は主キーが参照される。自身のそのフィールドは外部キーとして指定する、という意味だけで参照してくれる。なのでuser_idフィールドにたいして、user_idを指定する。
	private Users users;

	public Medicine() {
	}

	public Medicine(Integer id, String name, String text, Integer count, boolean check, boolean morningCheck,
			boolean daytimeCheck, boolean nightCheck, Date lastDate, Users users) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.count = count;
		this.check = check;
		this.morningCheck = morningCheck;
		this.daytimeCheck = daytimeCheck;
		this.nightCheck = nightCheck;
		this.lastDate = lastDate;
		this.users = users;
	}

	public Medicine(Integer id, boolean check, Users users) {
		this.id = id;
		this.check = check;
		this.users = users;
	}

	public Medicine(String name, String text, Integer count, boolean check, boolean morningCheck,
			boolean daytimeCheck, boolean nightCheck, Date lastDate, Users users) {
		this.name = name;
		this.text = text;
		this.count = count;
		this.check = check;
		this.morningCheck = morningCheck;
		this.daytimeCheck = daytimeCheck;
		this.nightCheck = nightCheck;
		this.lastDate = lastDate;
		this.users = users;
	}

	public Medicine(String name, String text, Integer count, Date lastDate, Users users) {
		this.name = name;
		this.text = text;
		this.count = count;
		this.lastDate = lastDate;
		this.users = users;
	}

	public Medicine(Integer id, String name, String text, Integer count, Date lastDate, Users users) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.count = count;
		this.lastDate = lastDate;
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isMorningCheck() {
		return morningCheck;
	}

	public void setMorningCheck(boolean morningCheck) {
		this.morningCheck = morningCheck;
	}

	public boolean isDaytimeCheck() {
		return daytimeCheck;
	}

	public void setDaytimeCheck(boolean daytimeCheck) {
		this.daytimeCheck = daytimeCheck;
	}

	public boolean isNightCheck() {
		return nightCheck;
	}

	public void setNightCheck(boolean nightCheck) {
		this.nightCheck = nightCheck;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
