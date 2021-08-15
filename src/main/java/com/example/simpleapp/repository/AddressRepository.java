package com.example.simpleapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.simpleapp.domain.Address;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository
  extends PagingAndSortingRepository<Address, Long>
{
}
