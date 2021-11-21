package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.TaskTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"}, allowCredentials = "true")
@RequestMapping("/api")
public class ProjectsController {

    EntityDAO entityDao;

    @Autowired
    public ProjectsController(EntityDAO entityDao) {
        this.entityDao = entityDao;
    }

    @GetMapping("/projects")
    public List<TaskTO> getProjects() {
        return entityDao.findAllNonArchivedProjects();
    }

    @GetMapping(value = "/task")
    public ResponseEntity<TaskTO> getTask(@RequestBody TaskTO taskTO) {
        return ResponseEntity.of(
                Optional.ofNullable(taskTO).flatMap(entityDao::findById)
        );
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskTO> modifyTask(@RequestBody TaskTO taskTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskTO).flatMap(entityDao::modifyTask)
        );
    }

    @Transactional
    @PostMapping(value = "/task/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskTO> createProject(@RequestBody TaskTO taskTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskTO).flatMap(entityDao::createProject)
        );
    }

    @PostMapping(value = "/task/subtask/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskTO> appendNewSubtask(@RequestBody TaskTO taskTO) { // todo validate
        return ResponseEntity.of(
               Optional.ofNullable(taskTO).flatMap(entityDao::appendNewSubtask)
        );
    }

}
