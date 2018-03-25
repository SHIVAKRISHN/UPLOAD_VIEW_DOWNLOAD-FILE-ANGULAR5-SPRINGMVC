package com.shiva.UploadFile.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.UploadFile.IDao.IUploadDao;
import com.shiva.UploadFile.IService.IUploadService;
import com.shiva.UploadFile.model.User;

@Service
public class IUploadServiceImpl implements IUploadService {

	@Autowired
	IUploadDao uploadDao;

	public void saveUser(User user) {

		uploadDao.saveUser(user);

	}

	public List<User> getAllUsers() {
		
		return uploadDao.getAllUsers();
		
	}

}
