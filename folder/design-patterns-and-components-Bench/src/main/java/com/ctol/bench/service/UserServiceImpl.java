package com.ctol.bench.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctol.bench.exception.AuthenticationException;
import com.ctol.bench.model.User;
import com.ctol.bench.repositories.UserRepository;
import com.ctol.bench.utils.Hasher;
import com.ctol.bench.utils.Validation;
import com.mongodb.annotations.Beta;

@Service
public class UserServiceImpl  implements UserService {

		@Autowired
		private UserRepository repository;
		
		@Autowired
		private PasswordEncoder bcryptEncoder;

		@Override
		public User authenticate(User user) throws AuthenticationException {
			User userInDatabase = findByEmail(user.getEmail());
			if (userInDatabase == null) {
				throw new AuthenticationException("User doesn't exist");
			}
			if (!Hasher.authenticate(user.getPassword().toCharArray(), userInDatabase.getPassword())) {
				throw new AuthenticationException("Incorrect credentials");
			}
			return userInDatabase;
		}

		@Override
		public User create(User user) throws AuthenticationException {
			List<User> usersInDatabase = findByEmailAndPhoneNumber(user.getEmail(), user.getPhoneNumber());
			if (user.getPhoneNumber() == null) {
				throw new AuthenticationException("Phone Number required for signing in");
			}
			if (usersInDatabase.size() != 0) {
				for (User _user : usersInDatabase) {
					if (_user.getEmail().equals(user.getEmail())) {
						throw new AuthenticationException("User already exists, please check email.");
					} else if (_user.getPhoneNumber().equals(user.getPhoneNumber())) {
						throw new AuthenticationException("User already exists, please check phone number.");
					}
				}
			}
			if (!Validation.checkPasswordRegex(user.getPassword())) {
				throw new AuthenticationException("Password doesn't match required pattern");
			}
			if (!Validation.checkPhoneRegex(user.getPhoneNumber())) {
				throw new AuthenticationException("Phone Number doesn't match required pattern");
			}
			user.setObjectId(ObjectId.get());
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			return repository.save(user);
		}

		@Override
		public User delete(ObjectId id) {
			User user = findByObjectId(id);
			if (user != null) {
				repository.delete(user);
			}
			return user;
		}

		@Override
		public List<User> findAll() {
			return repository.findAll();
		}

		@Override
		public User findByEmail(String email) {
			return repository.findByEmail(email);
		}

		@Override
		public List<User> findByEmailAndPhoneNumber(String email, String phone) throws AuthenticationException {
			return repository.findByEmailAndPhoneNumber(email, phone);
		}

		@Override
		public User findByObjectId(ObjectId id) {
			return repository.findByObjectId(id);
		}

		@Override
		public User update(User user) {
			return repository.save(user);
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// username is the email.
			User user = findByEmail(username);
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					new ArrayList<>());
		}
		

}
