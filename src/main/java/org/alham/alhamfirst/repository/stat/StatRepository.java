package org.alham.alhamfirst.repository.stat;

import org.alham.alhamfirst.document.stat.StatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatRepository extends MongoRepository<StatDocument, String>{
    StatDocument findByTodoIdx(long todoIdx);
}
