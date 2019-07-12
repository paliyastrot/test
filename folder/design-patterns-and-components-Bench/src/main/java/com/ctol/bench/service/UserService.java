package com.ctol.bench.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ctol.bench.exception.AuthenticationException;
import com.ctol.bench.model.User;


public interface UserService extends UserDetailsService {
	User create(User user) throws AuthenticationException;

	User delete(ObjectId id);

	List<User> findAll();

	User update(User user);

	User findByEmail(String email);

	User findByObjectId(ObjectId id);

	User authenticate(User user) throws AuthenticationException;

	List<User> findByEmailAndPhoneNumber(String email, String phone) throws AuthenticationException;
}
