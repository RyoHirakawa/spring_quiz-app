package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.CommonInfo;
import com.example.service.CommonService;

@ControllerAdvice
public class CommonController {
	@Autowired
	private CommonService commonService;
	
	@ModelAttribute("commonInfo")
	public CommonInfo addCommonInfoToModel() {
		CommonInfo commonInfo = new CommonInfo();
		commonInfo.setCategories(commonService.getCategories());
		return commonInfo;
	}
}
