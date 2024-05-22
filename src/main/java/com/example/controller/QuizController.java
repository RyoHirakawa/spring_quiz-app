package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Category;
import com.example.model.Problem;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.ProblemService;

import jakarta.validation.Valid;


@Controller
public class QuizController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProblemService problemService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.getAll());
		return "index";
	}
	
	@GetMapping("/editCategory")
	public String editCategory(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.getAll());
		return "editCategory";
	}
	
	@PostMapping("/saveCategory")
	public String postMethodName(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/editCategory";
	}
	
	@GetMapping("/category/{id}/createProblem")
	public String createProblem(@PathVariable("id") Long categoryId, Model model) {
		System.out.println(categoryId);
		Category category = categoryService.getCategoryById(categoryId);
		Problem newProblem = new Problem();
		newProblem.setCategory(category);
		model.addAttribute("problem", newProblem);		
		return "createProblem";
	}
	
	@PostMapping("/saveProblem")
	public String saveProblem(@Valid @ModelAttribute("problem") Problem problem, BindingResult bindingResult) {
		System.out.println(" hasErrors : " + bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			return "redirect:/category/" + problem.getCategory().getId() + "/createProblem";
		}
		problemService.saveProblem(problem);
		return "redirect:/";
	}
	
	@PostMapping("/deleteProblem")
	public String deleteProblem(@RequestParam("problemId") Long problemId) {
		problemService.deleteProblem(problemId);
		return "redirect:/";
	}
	
	@PostMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return "redirect:editCategory";
	}
	
	@GetMapping("/category/{id}/problems")
	public String Problems(@PathVariable("id") Long categoryId ,Model model) {
		System.out.println("/category/" + categoryId + "/problems");
		Category category = categoryService.getCategoryById(categoryId);
		java.util.List<Problem> problems = problemService.getProblemsByCategory(category);
		
		model.addAttribute("category", category);
		model.addAttribute("problems", problems);
		return "showProblems";
	}
	
	@GetMapping("/category/{id}/test")
	public String test(@PathVariable("id") Long categoryId, Model model) {
		Category category = categoryService.getCategoryById(categoryId);		
		int numberOfProblems = Math.min(10, categoryService.countProblems(categoryId));
		java.util.List<Problem> problemList = categoryService.getRandomProblems(categoryId, numberOfProblems);
		List<List<String>> choicesForProblems = new ArrayList<List<String>>();
		for (Problem problem : problemList) {
			List<String> choices = new ArrayList<String>();
			choices.add(problem.getCorrectChoice());
			choices.add(problem.getDummyChoice1());
			choices.add(problem.getDummyChoice2());
			choices.add(problem.getDummyChoice3());
			Collections.shuffle(choices);
			choicesForProblems.add(choices);			
		}
		System.out.println(choicesForProblems.toString());
		model.addAttribute("category", category);
		model.addAttribute("problemList", problemList);
		model.addAttribute("choicesForProblems", choicesForProblems);
		return "test";
	}
}
