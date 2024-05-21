package com.example.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "問題文を入力してください")
	private String question;
	
	@NotEmpty
	private java.util.List<String> choices;
	
	private int correctChoiceIndex;
	
	@NotBlank(message = "解説を入力してください")
	private String annotation;
	
	@ManyToOne
	private com.example.model.Category category;
	
	public Problem() {		
		this.choices = new ArrayList<>();
		for (int i=0; i<4; i++) {
			this.choices.add(new String());
		}
	}
	
	public Problem(String question, List<String> choices, int correctChoiceIndex, String annotation,
			Category category) {
		super();
		this.question = question;
		this.choices = choices;
		this.correctChoiceIndex = correctChoiceIndex;
		this.annotation = annotation;
		this.category = category;
	}	
	
}
