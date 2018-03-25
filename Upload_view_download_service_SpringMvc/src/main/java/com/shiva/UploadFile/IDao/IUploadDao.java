package com.shiva.UploadFile.IDao;

import java.util.List;

import com.shiva.UploadFile.model.User;

public interface IUploadDao {

	public void saveUser(User user);
	public List<User> getAllUsers();

}
