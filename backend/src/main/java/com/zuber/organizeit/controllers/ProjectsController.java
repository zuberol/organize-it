package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Repository.EntityDAO;
import com.zuber.organizeit.Model.Task.Project;
import com.zuber.organizeit.Model.Repository.ProjectsRepository;
import com.zuber.organizeit.Model.Task.Task;
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
    EntityDAO entityDao;

    @Autowired
    public ProjectsController(TaskRepository taskRepository, ProjectsRepository projectsRepository, EntityDAO entityDao) {
        this.taskRepository = taskRepository;
        this.projectsRepository = projectsRepository;
        this.entityDao = entityDao;
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

    @GetMapping("/project/undone/{projectName}")
    public List<Task> findUndoneSubTasks(@PathVariable("projectName") String taskName) {
        return entityDao.findUndoneTasks(taskName);
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
