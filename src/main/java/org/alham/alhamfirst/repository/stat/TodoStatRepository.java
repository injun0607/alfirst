package org.alham.alhamfirst.repository.stat;

import org.alham.alhamfirst.domain.document.stat.StatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoStatRepository extends MongoRepository<StatDocument, String>{
    StatDocument findByTodoIdx(long todoIdx);

    List<StatDocument> findByTodoIdxIn(List<Long> todoIdxList);


}
