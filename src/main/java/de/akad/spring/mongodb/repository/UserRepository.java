package de.akad.spring.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.akad.spring.mongodb.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
  List<UserModel> findByPublished(boolean published);
  List<UserModel> findByTitleContaining(String title);
}
