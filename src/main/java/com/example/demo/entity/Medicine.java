package com.example.demo.entity;

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

	// 残薬数
	@Column(name = "remaining_medicine_number")
	private Integer remainingMedicineNumber;

	// ★ 外部キーの設定   多側のエンティティの記述 users表の主キー(id)を参照するには、「users_id」で正解。
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // referencedColumnNameを省略した場合は主キーが参照される。自身のそのフィールドは外部キーとして指定する、という意味だけで参照してくれる。なのでuser_idフィールドにたいして、user_idを指定する。
	private Users users;

	public Medicine() {
	}

	public Medicine(Integer id, String name, String text, Integer count, boolean check, Integer remainingMedicineNumber,
			Users users) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.count = count;
		this.check = check;
		this.remainingMedicineNumber = remainingMedicineNumber;
		this.users = users;
	}

	public Medicine(Integer id, String name, String text, Integer count, Integer remainingMedicineNumber, Users users) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.count = count;
		this.remainingMedicineNumber = remainingMedicineNumber;
		this.users = users;
	}

	public Medicine(String name, String text, Integer count, boolean check, Integer remainingMedicineNumber,
			Users users) {
		super();
		this.name = name;
		this.text = text;
		this.count = count;
		this.check = check;
		this.remainingMedicineNumber = remainingMedicineNumber;
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

	public Integer getRemainingMedicineNumber() {
		return remainingMedicineNumber;
	}

	public void setRemainingMedicineNumber(Integer remainingMedicineNumber) {
		this.remainingMedicineNumber = remainingMedicineNumber;
	}

	// 残薬を更新するメソッド
	public void UpdateCounter() {
		this.remainingMedicineNumber = this.remainingMedicineNumber - this.count;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
