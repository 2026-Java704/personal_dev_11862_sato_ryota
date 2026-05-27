package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	// SELECT * FROM Customer WHERE email = ? AND password = ?
	//	List<Customer> findById(Integer id);

	//	String findByEmail(String email);

	boolean existsByName(String name);

	//	boolean existsByPassword(String password);

	boolean existsByNameAndPassword(String name, String password);

	// 
	Users findByNameAndPassword(String name, String password);

	//	Users findByName(String name);

	// 呼び出すだけで昇順（id順）に取得されるメソッド
	List<Users> findByOrderByIdAsc();
}