package com.example.simpleapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.simpleapp.domain.Address;
import com.example.simpleapp.domain.Person;
import com.example.simpleapp.repository.AddressRepository;
import com.example.simpleapp.repository.PersonRepository;

@RepositoryRestController
public class AddressController
{
  private final PersonRepository personRepository;
  private final AddressRepository addressRepository;

  @Autowired
  public AddressController(PersonRepository aPersonRepository, AddressRepository aAddressRepository)
  {
    personRepository = aPersonRepository;
    addressRepository = aAddressRepository;
  }

  @DeleteMapping(path = "/addresses/{aAddressId}")
  public ResponseEntity<Object> deleteAddress(@PathVariable Long aAddressId)
  {
    Optional<Address> container = addressRepository.findById(aAddressId);
    if (container.isPresent()) {
      Address addressToDelete = container.get();
      deleteAddressFromPersonAddresses(addressToDelete);
      addressRepository.deleteById(aAddressId);
    }
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

  private void deleteAddressFromPersonAddresses(Address aAddressToDelete)
  {
    for (Person person : personRepository.findAll()) {
      List<Address> addresses = person.getAddresses();
      if (addresses.contains(aAddressToDelete)) {
        addresses.remove(aAddressToDelete);
        personRepository.save(person);
      }
    }
  }
}
