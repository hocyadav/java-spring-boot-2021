package com.hari.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

/**
 * @author HariomYadav
 * @since 10/10/20
 */
@Getter
@Setter
@Document(collection = "books")
public class Book {
    //NOTE : no auto generated annotaion in mongo db
    @Id
    int id;
    String name;
    String description;

}
