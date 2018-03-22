package com.shiva.UploadFile.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shiva.UploadFile.IDao.IUploadDao;
import com.shiva.UploadFile.model.User;


@Repository
public class UploadDaoImpl implements IUploadDao {

	@PersistenceContext
	private EntityManager entityManager;


	@Transactional
	public void saveUser(User user) {


		entityManager.persist(user);

	}

}
