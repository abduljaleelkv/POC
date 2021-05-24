package com.restweb.restweb.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restweb.restweb.Exception.UserNotFoundException;
import com.restweb.restweb.bean.Post;
import com.restweb.restweb.bean.User;
import com.restweb.restweb.repository.PostRepository;
import com.restweb.restweb.repository.UserRepository;
import com.restweb.restweb.service.UserDaoService;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		
		return userRepository.findAll();
	}
	
	//retrieve a user
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		
		Optional<User> user= userRepository.findById(id);
		
		if (!user.isPresent()) 
			
			throw new UserNotFoundException("id-"+id);
		  
		    return user.get();
	}
	
	//save user
	@PostMapping("/users")
	public User  saveUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
		

	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		
		userRepository.deleteById(id);
		
		
	}
	//Get User posts
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveUsersPost(@PathVariable int id ){
		
		Optional<User> userOptional= userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return userOptional.get().getPosts();
	}
   
	//create a post to user
		@PostMapping("/users/{id}/posts")
		public Post  createPost(@PathVariable int id , @RequestBody Post post) {
			
			Optional<User> userOptional= userRepository.findById(id);
			
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("id-"+id);
			}
			
			User user= userOptional.get();
			
			post.setUser(user);
			return postRepository.save(post);
			

		}
}
