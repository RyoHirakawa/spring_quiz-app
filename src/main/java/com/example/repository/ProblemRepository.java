package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Category;
import com.example.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
	java.util.List<Problem> findByCategory(Category category);
}
