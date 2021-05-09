package com.zuber.organizeit.controllers;


import com.zuber.organizeit.Model.Task;
import com.zuber.organizeit.Model.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Task getRootTask(@RequestParam Long id) {
        return taskRepository.findById(id).get();
    }

    @GetMapping("/roott")   // todo optionale jakos innaczej dzialaja
    public Task getRootTaskk(@RequestParam Long id) {
        return taskRepository.findById(id).orElseThrow();
    }


    @PostMapping(value = "/task", consumes = "application/json")
    public ResponseEntity<?>  saveTask(@RequestBody Task task) {
        if(task.getTaskId() == null) {
            final Task parentTask = taskRepository.findById(task.getParentTask().getTaskId()).get();
            task.setTaskId(taskRepository.getIdFromSeq());
            task.setParentTask(parentTask);
            taskRepository.save(task);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/projects")
    public List<Task> getProjects() {
        return List.of(taskRepository.findById(1L).get());
    }

}
