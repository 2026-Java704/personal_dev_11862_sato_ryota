package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	// SELECT * FROM Customer WHERE email = ? AND password = ?
	//	List<Customer> findById(Integer id);

	//	String findByEmail(String email);

	//	boolean existsByEmail(String email);

	//	boolean existsByPassword(String password);

	// 名前とパスワードで一致検索
	boolean existsByNameAndPassword(String name, String password);
}