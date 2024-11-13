package org.alham.alhamfirst.repository.stat;

import org.alham.alhamfirst.document.stat.UserStatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStatRepository extends MongoRepository<UserStatDocument, String> {
    UserStatDocument findByUserId(long userId);
}
