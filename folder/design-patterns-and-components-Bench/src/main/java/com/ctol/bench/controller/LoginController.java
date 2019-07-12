package com.ctol.bench.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctol.bench.model.User;
import com.ctol.bench.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/users" })
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping(path = { "/{id}" })
	public User findOne(@PathVariable("id") ObjectId id) {
		return userService.findByObjectId(id);
	}

	@DeleteMapping(path = { "/{id}" })
	public User delete(@PathVariable("id") ObjectId id) {
		return userService.delete(id);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
}
