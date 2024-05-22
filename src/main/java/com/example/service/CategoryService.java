package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.model.Problem;
import com.example.repository.CategoryRepository;
import com.example.repository.ProblemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProblemRepository problemRepository;

	public void save(Category category) {
		categoryRepository.save(category);
	}

	public java.util.List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	public Category getCategoryById(Long categoryId) {
		Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
		return categoryOptional.orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
	}
	
	public java.util.List<Problem> getProblemsByCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id : " + categoryId));
		return category.getProblems();
	}
	
	public int countProblems(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id : " + categoryId));
		return category.getProblems().size();
	}


	//n個のランダムな問題を返す
	public java.util.List<Problem> getRandomProblems(Long categoryId, int numberOfProblems) {		
		Category category = getCategoryById(categoryId);
		java.util.List<Problem> allProblems = problemRepository.findByCategory(category);
		Collections.shuffle(allProblems);
		// 問題が n 個未満の場合は全ての問題を返す
		if (allProblems.size() <= numberOfProblems) {			
			return allProblems;
		}

		// 問題をランダムに選択するためにリストをシャッフル
		Collections.shuffle(allProblems);

		// ランダムに選択した n 個の問題を格納するリスト
		java.util.List<Problem> randomProblems = new ArrayList<>();

		// ランダムに選択した n 個の問題を取得
		for (int i = 0; i < numberOfProblems; i++) {
			randomProblems.add(allProblems.get(i));
		}

		return randomProblems;
	}
}