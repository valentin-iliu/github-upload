package com.example.simpleapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String street;
  private String city;
  private String state;
  private String postalCode;

  public String getStreet()
  {
    return street;
  }

  public void setStreet(String aStreet)
  {
    street = aStreet;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String aCity)
  {
    city = aCity;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String aState)
  {
    state = aState;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public void setPostalCode(String aPostalCode)
  {
    postalCode = aPostalCode;
  }

}
