package com.example.commandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.service.CategoryService;

@Component
public class QuizCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	CategoryService categoryService;
	
    @Override
    public void run(String... args) throws Exception {   
        System.out.println("アプリケーションが起動しました。");
        System.out.println("コマンドラインランナーが終了しました");
    }
}
