package com.jasonvillar.userapi.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimarySequenceRepository extends MongoRepository<PrimarySequence, String> {
}