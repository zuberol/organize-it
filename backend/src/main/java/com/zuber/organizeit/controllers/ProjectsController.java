package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Project;
import com.zuber.organizeit.Model.Repository.ProjectsRepository;
import com.zuber.organizeit.Model.Task;
import com.zuber.organizeit.Model.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"}, allowCredentials = "true")
@RequestMapping("/api")
public class ProjectsController {

    TaskRepository taskRepository;
    ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsController(TaskRepository taskRepository, ProjectsRepository projectsRepository) {
        this.taskRepository = taskRepository;
        this.projectsRepository = projectsRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        return ResponseEntity.of(taskRepository.findById(id));
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Task saveTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteTask(@RequestBody Task task) {
        taskRepository.delete(task);
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectsRepository.findAll();
    }

    @PostMapping(value = "/project", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Project saveProject(Project project) {
        return projectsRepository.save(project);
    }
}
