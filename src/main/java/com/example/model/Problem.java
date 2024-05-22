package com.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "問題文を入力してください")
	private String question;
	
	@NotBlank(message = "選択肢を入力してください")
	private String correctChoice;
	
	@NotBlank(message = "選択肢を入力してください")
	private String dummyChoice1;
	
	@NotBlank(message = "選択肢を入力してください")	
	private String dummyChoice2;
	
	@NotBlank(message = "選択肢を入力してください")
	private String dummyChoice3;
		
	
	@NotBlank(message = "解説を入力してください")
	private String annotation;
	
	@ManyToOne
	private com.example.model.Category category;
	
	public Problem() {
	}
	
	public Problem(String question, String correctChoice, String dummyChoice1, String dummyChoice2, String dummyChoice3, int correctChoiceIndex, String annotation,
			Category category) {
		super();
		this.question = question;
		this.correctChoice = correctChoice;
		this.dummyChoice1 = dummyChoice1;
		this.dummyChoice2 = dummyChoice2;
		this.dummyChoice3 = dummyChoice3;
		this.annotation = annotation;
		this.category = category;
	}	
	
}
