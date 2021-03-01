package com.hari.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hari.mongodb.entity.Book;

/**
 * @author HariomYadav
 * @since 10/10/20
 */
@Repository
public interface BookRepo extends MongoRepository<Book, Integer> {}
