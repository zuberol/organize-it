package com.zuber.organizeit.controllers;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    void saveTask() {
        assertTrue(taskRepository.getIdFromSeq() > 20);
        System.out.println(taskRepository.findAll());
        assertTrue(taskRepository.findAll().size() > 20);
    }

    @Test
    void getProjects() {
    }



//    @RegisterExtension
//    static WebServerExtension server = WebServerExtension.builder()
//            .enableSecurity(false)
//            .build();
}