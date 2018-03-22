package com.shiva.UploadFile.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiva.UploadFile.IService.IUploadService;
import com.shiva.UploadFile.model.User;

@Controller
public class UploadController {

	@Autowired
	IUploadService uploadSerivce;

	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	public @ResponseBody void saveUser()
	{
		User user= new User();

		user.setName("shiva");
		user.setMobileNumber("11111111");
		user.setImageName("image.png");
		user.setGender("male");
		user.setEmailId("shiva1607502.office@gmail.com");
		user.setDoucmentName("myFile.doc");

		uploadSerivce.saveUser(user);



	}


}
