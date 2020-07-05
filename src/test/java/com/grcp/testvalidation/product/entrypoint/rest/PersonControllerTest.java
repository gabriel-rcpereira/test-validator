package com.grcp.testvalidation.product.entrypoint.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grcp.testvalidation.product.entrypoint.rest.customvalidator.PersonProgrammaticallyValidator;
import com.grcp.testvalidation.product.entrypoint.rest.json.person.PersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.WebDataBinder;

import static com.grcp.testvalidation.product.entrypoint.rest.json.person.JobPositionRequest.DEVELOPER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { PersonController.class, PersonProgrammaticallyValidator.class })
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldCreatePerson_expectedCreated() throws Exception {
        PersonRequest request = PersonRequest.builder()
                .name("test name person")
                .username("tnameperson")
                .jobPositionRequest(DEVELOPER)
                .build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldCreatePerson_expectedBadRequest() throws Exception {
        PersonRequest request = PersonRequest.builder()
                .name("tes")
                .username("tson")
                .jobPositionRequest(DEVELOPER)
                .build();

        String json = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
                    .contentType(APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest());
    }
}
