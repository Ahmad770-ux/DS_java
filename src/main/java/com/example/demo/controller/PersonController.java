/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
* PersonController is a RestController which has request mapping methods for RESTful requests such as:
* getAllPerson, createPerson, updatePerson, deletePerson, findByLastName…
*/

package com.example.demo.controller;

import com.example.demo.repository.PersonRepository;
import com.example.demo.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping
    public List<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@RequestParam("id") String id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> add(@RequestBody Person p) {
        p = repository.save(p);
//        return p; and should return Person 
            return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam("id") String id) {
        repository.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody Person p) {
        repository.save(p);
    }

    @GetMapping("/lastname/{lastName}")
    public List<Person> findByLastName(@RequestParam("lastName") String lastName) {
        return repository.findByLastName(lastName);
    }
   @GetMapping("/firstname/{firstName}")
    public List<Person> findByFirstName(@RequestParam("firstName") String firstName) {
        return repository.findByFirstName(firstName);
    }
    @GetMapping("/age/{age}")
    public List<Person> findByAgeGreaterThan(@RequestParam("age") int age) {
        return repository.findByAgeGreaterThan(age);
    }
    @GetMapping("/age/{age1}")
   public List<Person> findByAgeLessThan(@RequestParam("age1") int age1) {
       return repository.findByAgeLessThan(age1);
  }
        @GetMapping("/age/{ageSmall,ageBig}")
   public List<Person> findByAgeBetween(@RequestParam("ageSmall")int ageSmall,@RequestParam("ageBig")int ageBig ) {
       return repository.findByAgeBetween(ageSmall, ageBig );
  }
    
      @DeleteMapping("/people")
  public ResponseEntity<HttpStatus> deleteAllPeople() {
    //add your code here 
      repository.deleteAll();
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
