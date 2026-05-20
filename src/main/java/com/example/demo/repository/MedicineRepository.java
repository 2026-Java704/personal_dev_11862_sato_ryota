package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Medicine;

// 外部キー条件select式は、JPQLで表結合したSELECT結果で条件検索をすれば、できる。@Queryアノテーション

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

}
