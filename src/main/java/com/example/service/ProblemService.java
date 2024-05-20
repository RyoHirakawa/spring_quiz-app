package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.model.Problem;
import com.example.repository.ProblemRepository;

@Service
public class ProblemService {
	@Autowired
	private ProblemRepository problemRepository;
	
	public java.util.List<Problem> getAllProblems() {
		return problemRepository.findAll();
	}
	
	public Problem getProblemById(Long id) {
		return problemRepository.findById(id).orElse(null);
	}
	
	public Problem saveProblem(Problem problem) {
		return problemRepository.save(problem);
	}
	
	public void deleteProblem(Long id) {
		problemRepository.deleteById(id);
	}

	public java.util.List<Problem> getProblemsByCategory(Category category) {
		java.util.List<Problem> problems = problemRepository.findByCategory(category);
		return problems;
	}
}
