package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;

@Service
public class CommonService {
	@Autowired
	CategoryService categoryService;
	public java.util.List<Category> getCategories() {
		java.util.List<Category> categories = categoryService.getAll();
		return categories;
	}
}
