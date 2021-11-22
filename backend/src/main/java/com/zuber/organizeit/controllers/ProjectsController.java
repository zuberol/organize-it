package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.TaskDto;
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

    @GetMapping("/tasks")
    public List<TaskDto> getProjects() {
        return entityDao.findAllNonArchivedProjects();
    }

    @GetMapping(value = "/task")
    public ResponseEntity<TaskDto> getTask(@RequestBody TaskDto taskDTO) {
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::findById)
        );
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDto> modifyTask(@RequestBody TaskDto taskDTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::modifyTask)
        );
    }

    @Transactional
    @PostMapping(value = "/task/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDto> createProject(@RequestBody TaskDto taskDTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::createProject)
        );
    }

    @PostMapping(value = "/task/subtask/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDto> appendNewSubtask(@RequestBody TaskDto taskDTO) { // todo validate
        return ResponseEntity.of(
               Optional.ofNullable(taskDTO).flatMap(entityDao::appendNewSubtask)
        );
    }

}
