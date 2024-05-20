package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Category;
import com.example.model.Problem;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.ProblemService;


@Controller
public class QuizController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProblemService problemService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/createCategory")
	public String createCategory(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.getAll());
		return "createCategory";
	}
	
	@PostMapping("/saveCategory")
	public String postMethodName(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/createCategory";
	}
	
	@GetMapping("/category/{id}/createProblem")
	public String createProblem(@PathVariable("id") Long categoryId, Model model) {
		System.out.println(categoryId);
		categoryService.sayHello();
//		System.out.println(category);
//		System.out.println(category.toString());
		
//		System.out.println("Hello createProblem");
		Category category = categoryService.getCategoryById(categoryId);
		Problem newProblem = new Problem();
		newProblem.setCategory(category);
		model.addAttribute("problem", newProblem);		
		return "createProblem";
	}
	@PostMapping("/saveProblem")
	public String saveProblem(@ModelAttribute("problem") Problem problem) {		
		problemService.saveProblem(problem);
		return "redirect:/createCategory";
	}
	
	@GetMapping("/category/{id}/problems")
	public String Problems(@PathVariable("id") Long categoryId ,Model model) {
		System.out.println("/category/" + categoryId + "/problems");
		Category category = categoryService.getCategoryById(categoryId);
		java.util.List<Problem> problems = problemService.getProblemsByCategory(category);
		
		model.addAttribute("categoryName", category.getName());
		model.addAttribute("problems", problems);
		return "showProblems";
	}
	
	@GetMapping("/category/{id}/test")
	public String test(@PathVariable("id") Long categoryId, Model model) {
		Category category = categoryService.getCategoryById(categoryId);
		model.addAttribute("category", category);
		System.out.println(categoryService.getRandomProblems(categoryId, 2));
		return "test";
	}
}
