package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
class ProjectsControllerTest {

    @Autowired
    private TaskRepository taskRepository;


//    @Autowired
//    private MockMvc mockMvc;



    @Test
    void getProjects() {
    }


//    @RegisterExtension
//    static WebServerExtension server = WebServerExtension.builder()
//            .enableSecurity(false)
//            .build();
}