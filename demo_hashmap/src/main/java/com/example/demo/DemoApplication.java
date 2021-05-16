package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

public class DemoApplication {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		System.out.println("userDao = " + userDao.getAll());

		//create new user entity
		User user = new User();
		user.setId(1L);
		user.setUserName("usename1");
		user.setAddress("address1");

		User user2 = new User();
		user2.setId(2L);
		user2.setUserName("usename2");
		user2.setAddress("address2");

		userDao.save(user);
		userDao.save(user2);

		//fetch user from db
		System.out.println("fetch all = " + userDao.getAll());

		//fetch single user
		final User user1 = userDao.findById(1L);
		System.out.println("user1 = " + user1);

		System.out.println("user2 = " + userDao.findById(2L));
	}
}
