package com.example.social_network_api.repository;

import com.example.social_network_api.model.Thought;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThoughtRepository extends MongoRepository<Thought, String> {
}
