package com.example.social_network_api.repository;

import com.example.social_network_api.model.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends MongoRepository<Reaction, String> {
}
