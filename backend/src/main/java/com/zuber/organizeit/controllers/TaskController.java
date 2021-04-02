package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Task;
import com.zuber.organizeit.Model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"}, allowCredentials = "true")
@RequestMapping("/api")
public class TaskController {

    TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


//    @GetMapping("/tasks")
//    public Stream<TaskDTO> getAllTasks() {
//        return taskRepository.findAll()
//                .stream()
//                .map(task -> TaskDTO.builder()
//                        .taskId(task.getTaskId())
//                        .note(task.getNote())
//                        .subTasksIds(task.getSubTasks().stream().map(Task::getTaskId).collect(Collectors.toList()))
//                        .build()
//                );
//    }

    @GetMapping("/root")
    public Optional<Task> getRootTask(@RequestParam Long id) {
        return taskRepository.findById(id);
    }

    @PutMapping(value = "/task", consumes = "application/json")
    public ResponseEntity<String> saveTask(@RequestBody Task task) {
//        List<Task> subtasks = taskRepository.findAllById(task.getSubTasks());

        System.out.println(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
