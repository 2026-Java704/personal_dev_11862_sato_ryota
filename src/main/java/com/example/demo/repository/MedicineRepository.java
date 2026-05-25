package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Medicine;

// 外部キー条件select式は、JPQLで表結合したSELECT結果で条件検索をすれば、できる。@Queryアノテーション

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

	// JPQLで外部キー（dept_id）を条件に指定する例
	//	@Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
	//	List<Employee> findEmployeesByDeptId(@Param("deptId") Long deptId);

	// 呼び出すだけで昇順（id順）に取得されるメソッド
	List<Medicine> findByOrderByIdAsc();

	// MedicineのUser_id外部キーで条件検索一括取得
	List<Medicine> findByUsers_Id(Integer user_id);
}
