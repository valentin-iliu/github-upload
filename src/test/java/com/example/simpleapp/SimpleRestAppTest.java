package com.example.simpleapp;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.simpleapp.repository.AddressRepository;
import com.example.simpleapp.repository.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleRestAppTest
{

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private AddressRepository addressRepository;

  @BeforeEach
  public void deleteAllBeforeTests()
    throws Exception
  {
    personRepository.deleteAll();
    addressRepository.deleteAll();
  }

  @Test
  public void testReturnRepositoryIndex()
    throws Exception
  {
    mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$._links.people").exists());
  }

  @Test
  public void testCreatePerson()
    throws Exception
  {
    MvcResult mvcResult =
        mockMvc
            .perform(post("/people").content("{\"firstName\": \"Jane\", \"lastName\": \"Jones\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("people/")))
            .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc
        .perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Jane"))
        .andExpect(jsonPath("$.lastName").value("Jones"));
  }

  @Test
  public void testFindByLastName()
    throws Exception
  {

    mockMvc
        .perform(post("/people").content("{ \"firstName\": \"Jane\", \"lastName\":\"Jones\"}"))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/people/search/findByLastName?name={name}", "Jones"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.people[0].firstName").value("Jane"));
  }

  @Test
  public void testUpdatePersonRemoveLastName()
    throws Exception
  {
    MvcResult mvcResult =
        mockMvc
            .perform(post("/people").content("{\"firstName\": \"Jane\", \"lastName\":\"Jones\"}"))
            .andExpect(status().isCreated())
            .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");

    mockMvc.perform(put(location).content("{\"firstName\": \"Gina\"}")).andExpect(status().isNoContent());

    mockMvc
        .perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Gina"))
        .andExpect(jsonPath("$.lastName").doesNotExist());
  }

  @Test
  public void testPatchPerson()
    throws Exception
  {

    MvcResult mvcResult =
        mockMvc
            .perform(post("/people").content("{\"firstName\": \"Jane\", \"lastName\":\"Jones\"}"))
            .andExpect(status().isCreated())
            .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");

    mockMvc.perform(patch(location).content("{\"firstName\": \"Julia\"}")).andExpect(status().isNoContent());

    mockMvc
        .perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Julia"))
        .andExpect(jsonPath("$.lastName").value("Jones"));
  }

  @Test
  public void testDeletePerson()
    throws Exception
  {

    MvcResult mvcResult =
        mockMvc
            .perform(post("/people").content("{ \"firstName\": \"Gina\", \"lastName\":\"Jones\"}"))
            .andExpect(status().isCreated())
            .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(delete(location)).andExpect(status().isNoContent());

    mockMvc.perform(get(location)).andExpect(status().isNotFound());
  }
}
