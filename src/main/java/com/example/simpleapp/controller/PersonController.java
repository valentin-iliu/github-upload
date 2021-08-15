package com.example.simpleapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.simpleapp.domain.Address;
import com.example.simpleapp.domain.Person;
import com.example.simpleapp.repository.PersonRepository;

@RepositoryRestController
public class PersonController
{
  private final PersonRepository personRepository;

  @Autowired
  public PersonController(PersonRepository aPersonRepository)
  {
    personRepository = aPersonRepository;
  }

  @PostMapping(path = "/people/{aPersonId}/addresses/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addAddress(@RequestBody Address aNewAddress, @PathVariable Long aPersonId)
  {
    Optional<Person> container = personRepository.findById(aPersonId);
    if (container.isPresent()) {
      Person person = container.get();
      List<Address> addresses = person.getAddresses();
      if (addresses == null) {
        addresses = new ArrayList<Address>();
      }
      addresses.add(aNewAddress);
      personRepository.save(person);
    }
    return new ResponseEntity<Object>(HttpStatus.OK);
  }
}
