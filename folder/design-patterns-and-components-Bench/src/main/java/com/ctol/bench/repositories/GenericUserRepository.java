package com.ctol.bench.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.ctol.bench.model.User;

public interface GenericUserRepository extends Repository<User,Integer> {
	void delete(User user);

	List<User> findAll();

	User findByEmail(String email);

	User findByUsername(String username);

	User save(User user);
}
