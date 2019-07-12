package com.ctol.bench.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ctol.bench.model.User;

public interface UserRepository extends GenericUserRepository, MongoRepository<User,Integer> {
	
	@Query("{ $or : [ {'email': ?0}, {'phoneNumber': ?1} ] }")
	List<User> findByEmailAndPhoneNumber(String email, String phoneNumber);
	
	User findByObjectId(ObjectId _id);
}
