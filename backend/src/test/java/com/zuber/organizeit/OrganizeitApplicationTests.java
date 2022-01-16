package com.zuber.organizeit;

import com.zuber.organizeit.domain.Repository.EntityDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizeitApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EntityDAO entityDAO;

    @Test
    @Disabled("undone afer changes")
    void getAllProjects() {

//        String EXPECTED_TESTING_TITLE = "some getAllProjects project";
//        projectsRepository.save(
//                new Project(null, EXPECTED_TESTING_TITLE, null, "testing", null));
//
//        assertThatCode(() -> mockMvc.perform(get(URI.create("http://localhost:8080/api/projects")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", new JacksonMappingProvider()).isString()))
//                .doesNotThrowAnyException();
    }
}
