package com.shiva.UploadFile.IService;

import java.util.List;

import com.shiva.UploadFile.model.User;

public interface IUploadService {

	public void saveUser(User user);
	public List<User> getAllUsers();

}
