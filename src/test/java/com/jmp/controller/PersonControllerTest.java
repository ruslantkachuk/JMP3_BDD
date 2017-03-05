package com.jmp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmp.JmpApplication;
import com.jmp.dto.PersonDto;
import com.jmp.entity.Person;
import com.jmp.repository.PersonRepository;

import cucumber.api.java8.En;

@SpringBootTest
@ContextConfiguration(classes = {JmpApplication.class})
public class PersonControllerTest implements En {

    private static final String PATH_PERSONS_ID = "/persons/{id}";
    private static final String PATH_PERSONS = "/persons";
    private static final String PERSON_EMAIL = "person@epam.com";
    private Integer PERSON_ID = 100;

    private ObjectMapper jsonObjectMapper;
    private ResultActions resultActions;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PersonRepository personRepository;


    public PersonControllerTest() {
        Before(() -> {
            jsonObjectMapper = new ObjectMapper();
            mockMvc = webAppContextSetup(webApplicationContext).build();
        });

        findPersonScenario();
        createPersonScenario();
        updatePersonScenario();
        deletePersonScenario();

    }

    private void findPersonScenario() {
        Given("^Person exists in database with id (\\d+) and available for search$", (Integer id) -> {
            personRepository.save(preparePerson(id));
            PERSON_ID = id;
        });
        When("^Find a person by id (\\d+)$", (Integer id) -> {
            try {
                resultActions = mockMvc.perform(get(PATH_PERSONS_ID, PERSON_ID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Then("^Get a PersonDto with id (\\d+)$", (Integer id) -> {
            try {
                resultActions.andExpect(jsonPath("$.id").value(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        And("^Get a response status code of (\\d+) after finding$", (Integer code) -> {
            assertThat(resultActions.andReturn().getResponse().getStatus(), is(equalTo(code)));
        });
    }

    private void createPersonScenario() {
        When("^Create a new person$", () -> {
            try {
                resultActions = mockMvc.perform(post(PATH_PERSONS).contentType(MediaType.APPLICATION_JSON).content(
                        jsonObjectMapper.writeValueAsString(preparePersonDto(PERSON_ID))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Then("^Get a response status code (\\d+) after creating$", (Integer code) -> {
            assertThat(resultActions.andReturn().getResponse().getStatus(), is(equalTo(code)));
        });
    }

    private void updatePersonScenario() {
        Given("^Person exists in database with id (\\d+) and available for update$", (Integer id) -> {
            personRepository.save(preparePerson(id));
            PERSON_ID = id;
        });
        When("^Update a person with id (\\d+)$", (Integer id) -> {
            try {
                resultActions = mockMvc.perform(put(PATH_PERSONS).contentType(MediaType.APPLICATION_JSON).content(
                        jsonObjectMapper.writeValueAsString(preparePersonDtoWithEmail(id))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Then("^Get a response status code (\\d+) after updating$", (Integer code) -> {
            assertThat(resultActions.andReturn().getResponse().getStatus(), is(equalTo(code)));
        });
    }

    private void deletePersonScenario() {
        Given("^Person exists in database with id (\\d+) and available for delete$", (Integer id) -> {
            personRepository.save(preparePerson(id));
            PERSON_ID = id;
        });
        When("^Delete a person with id (\\d+)$", (Integer id) -> {
            try {
                resultActions = mockMvc.perform(delete(PATH_PERSONS_ID, PERSON_ID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Then("^Get a response status code (\\d+) after deleting$", (Integer code) -> {
            assertThat(resultActions.andReturn().getResponse().getStatus(), is(equalTo(code)));
        });
    }

    private Person preparePerson(Integer id) {
        Person person = new Person();
        person.setId(id);
        return person;
    }

    private PersonDto preparePersonDto(Integer id) {
        PersonDto personDto = new PersonDto();
        personDto.setId(id);
        return personDto;
    }

    private PersonDto preparePersonDtoWithEmail(Integer id) {
        PersonDto personDto = new PersonDto();
        personDto.setId(id);
        personDto.setEmail(PERSON_EMAIL);
        return personDto;
    }
}
