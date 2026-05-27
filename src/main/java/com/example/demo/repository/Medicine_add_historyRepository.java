package com.example.demo.repository;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Medicine_add_history;

public interface Medicine_add_historyRepository extends JpaRepository<Medicine_add_history, Integer> {
	// MedicineのUser_id外部キーで条件検索一括取得
	List<Medicine_add_history> findByUsers_Id(Integer user_id);

	@Transactional
	void deleteByUsers_Id(Integer user_id);

	// order by desc
	List<Medicine_add_history> findByUsers_IdOrderByIdDesc(Integer user_id);

}
