package com.hari.mongodb.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hari.mongodb.dao.BookRepo;
import com.hari.mongodb.entity.Book;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HariomYadav
 * @since 10/10/20
 */
@RestController
@RequestMapping(value = "/v1/book")
@Slf4j
public class BookResource {

    @Autowired
    BookRepo bookRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        log.info("BookResource.getAllBooks");
        return bookRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book saveBook(@RequestBody Book book) {
        log.info("BookResource.saveBook");
        return bookRepo.save(book);
    }

}
