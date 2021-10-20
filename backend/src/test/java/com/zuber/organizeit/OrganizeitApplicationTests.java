package com.zuber.organizeit;

import static org.assertj.core.api.Assertions.*;


import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.zuber.organizeit.Model.Project;
import com.zuber.organizeit.Model.Repository.ProjectsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizeitApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProjectsRepository projectsRepository;

    @Test
    void getAllProjects() {

        String EXPECTED_TESTING_TITLE = "some getAllProjects project";
        projectsRepository.save(
                new Project(null, EXPECTED_TESTING_TITLE, null, "testing", null));

        assertThatCode(() -> mockMvc.perform(get(URI.create("http://localhost:8080/api/projects")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", new JacksonMappingProvider()).isString()))
                .doesNotThrowAnyException();
    }
}
