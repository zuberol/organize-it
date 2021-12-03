package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public List<Task> getAllProjects() {
        return entityDao.findAllNonArchivedProjects();
    }

    @GetMapping(value = "/task")
    public ResponseEntity<Task> getTaskById(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::findById)
        );
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> modifyTask(@RequestBody TaskDTO taskDTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::modifyTask)
        );
    }

    @PostMapping(value = "/task/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) { // todo validate
        return ResponseEntity.of(
                Optional.ofNullable(taskDTO).flatMap(entityDao::createTask)
        );
    }


    @GetMapping(value = "/task/inbox")
    public List<Task> getInboxTasks() {
        return entityDao.getInboxTasks();
    }

    @PostMapping(value = "/task/subtask/put", consumes = {MediaType.APPLICATION_JSON_VALUE}) //todo zmienic nazwe endpointa
    public ResponseEntity<Task> appendNewSubtask(@RequestBody TaskDTO taskDTO) { // todo validate
        return ResponseEntity.of(
               Optional.ofNullable(taskDTO).flatMap(entityDao::appendNewSubtask)
        );
    }

}
