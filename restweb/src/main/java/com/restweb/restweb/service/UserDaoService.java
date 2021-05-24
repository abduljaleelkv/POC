package com.restweb.restweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Component;

import com.restweb.restweb.bean.User;

@Component
public class UserDaoService {
	private int userCount =4;
	private static List<User> users = new ArrayList<>();
	
	

	public List<User> findAll(){
		
		return users;
	}
	public User save(User user) {
		
		if(user.getId()==0) {
			
			user.setId(++userCount);
		}
		
		users.add(user);
		return user;
	}
	public User findOne(int id) {
		
		for(User user:users){
			
			if (user.getId()==id) {
				
				return user;
			}
		}
		return null;
		
	}
	
public User DeleteById(int id) {
	
	
	Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()){
			User user =iterator.next();
			if (user.getId()==id) {
				
				iterator.remove();
				return user;
			}
		}
		return null;
	
     }

}
