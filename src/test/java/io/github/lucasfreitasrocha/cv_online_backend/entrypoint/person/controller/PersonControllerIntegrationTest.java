package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.controller;

import io.github.lucasfreitasrocha.cv_online_backend.CvOnlineBackendApplication;
import io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person.PersonEntity;
import io.github.lucasfreitasrocha.cv_online_backend.dataprovider.person.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CvOnlineBackendApplication.class)
@ExtendWith(MockitoExtension.class)
class PersonControllerIntegrationTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void setDown() {
        repository.deleteAll();
    }

    @Test
    void createPersonSucess() throws Exception {
        mockMvc.perform(post("/person")
                .content("""
                        {
                              "name": "fulano",
                              "code": "code",
                              "address": "Rua dos bobos numero 0",
                              "phone": "22998245322",
                              "jobTitle": "Entregador",
                              "email": "fulano@emil.com",
                              "password": "123456"
                        }
                                """)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("code"));
                assertTrue(Objects.nonNull(repository.findByCode("code")));
    }

    @Test
    void createPersonErrorExistCode() throws Exception {
        PersonEntity entity = new PersonEntity();
        entity.setId(UUID.randomUUID());
        entity.setCode("codeError");
        repository.save(entity);
        mockMvc.perform(post("/person")
                        .content("""
                        {
                              "name": "fulano",
                              "code": "codeError",
                              "address": "Rua dos bobos numero 0",
                              "phone": "22998245322",
                              "jobTitle": "Entregador",
                              "email": "fulano@ts.com",
                              "password": "123456"
                        }
                                """)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("code"))
                .andExpect(jsonPath("$.errors[0].message").value("must be unique"));
    }
    @Test
    void createPersonOtherErrorExistCode() throws Exception {
        mockMvc.perform(post("/person")
                        .content("""
                        {
                              "name": "fulano",
                              "code": "1234567890101",
                              "address": "Rua dos bobos numero 0",
                              "phone": "22998245322",
                              "jobTitle": "Entregador",
                              "email": "fulano",
                              "password": "123456"
                        }
                                """)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].field").value("code"))
                .andExpect(jsonPath("$.errors[0].message").value("must be less than 12 characters"))
                .andExpect(jsonPath("$.errors[1].field").value("email"))
                .andExpect(jsonPath("$.errors[1].message").value("must be valid"));

    }

}