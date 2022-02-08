package com.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Login;
import com.learning.repo.LoginRepo;
import com.learning.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepo loginRepo;

	@Override
	public String addLogin(Login login) {
		Login login2 = loginRepo.save(login);
		if(login2 != null) {
			return "success";
		}else {
			return "fail";
		}
	}
	

}
