package com.example.demojava.jpa_set_convertor.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.example.demojava.jpa_set_convertor.HelperConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
@Getter
@Setter
@ToString
@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//important both id + generated value
    int id;

    @Convert(converter = HelperConverter.class)//working
    List<String> phoneNum = new ArrayList<>();
}
