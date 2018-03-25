package com.shiva.UploadFile.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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


	
	public List<User> getAllUsers() {
       TypedQuery<User> query = entityManager.createQuery("from User",User.class);
		
		List<User> userList =query.getResultList();
		
		return userList;
	}

}
