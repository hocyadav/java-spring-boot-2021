package com.hari.springbootcacheimpl.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hari.springbootcacheimpl.dao.PersonDao;
import com.hari.springbootcacheimpl.entity.Person;

@RestController
@RequestMapping ("/api/v1/")
public class PersonController {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private Map<Integer, Person> personHashMap;//hashmap as cache : not good for distributed env

    @Autowired
    private Map<Integer, Person> personHashMap2;//hazelcast cache : good for distrubuted env

//    @GetMapping ("/{person_id}") //hashmap cache - working
//    public Optional<Person> getPerson(@PathVariable ("person_id") Integer person_id) {
//        System.out.println("personHashMap2 = " + personHashMap2);
//        System.out.println("personHashMap = " + personHashMap);
//        if (personHashMap.containsKey(person_id)) {
//            System.out.println("found in cache..");
//            return Optional.ofNullable(personHashMap.get(person_id));
//        }
//        System.out.println("Not found in cache..");
//        return personDao.findById(person_id);
//    }

    @GetMapping ("/{person_id}")
    public Optional<Person> getPerson(@PathVariable ("person_id") Integer person_id) {
        System.out.println("personHashMap2 = " + personHashMap2);
        if (personHashMap2.containsKey(person_id)) {
            System.out.println("found in cache..");
            return Optional.ofNullable(personHashMap2.get(person_id));
        }
        System.out.println("Not found in cache..");
        return personDao.findById(person_id);
    }

//    @PostMapping //hashmap cache - working
//    public Person getPerson(@RequestBody Person person) {
//        System.out.println("personHashMap = " + personHashMap);
//        Person person1 = personDao.save(person);
//        personHashMap.put(person1.getId(), person1);
//        return person1;
//    }

    @PostMapping
    public Person getPerson(@RequestBody Person person) {
        System.out.println("personHashMap2 = " + personHashMap2);
        Person person1 = personDao.save(person);
        personHashMap2.put(person1.getId(), person1);
        return person1;
    }

    @GetMapping ("/all")
    public List<Person> getAllPerson() {
        return personDao.findAll();
    }

}
