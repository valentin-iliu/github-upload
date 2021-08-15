package com.example.simpleapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String firstName;
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
  //  @JoinTable(
  //      name = "address_person",
  //      joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
  private List<Address> addresses;

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String aFirstName)
  {
    firstName = aFirstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String aLastName)
  {
    lastName = aLastName;
  }

  public List<Address> getAddresses()
  {
    return addresses;
  }

  public void setAddresses(List<Address> aAddresses)
  {
    addresses = aAddresses;
  }
}
