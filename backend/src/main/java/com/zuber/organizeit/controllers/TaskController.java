package com.zuber.organizeit.controllers;



import com.zuber.organizeit.Model.Task;
import com.zuber.organizeit.Model.TaskDTO;
import com.zuber.organizeit.Model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"}, allowCredentials = "true")
public class TaskController {

    TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("/tasks")
    public Stream<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> TaskDTO.builder()
                        .task_id(task.getTask_id())
                        .note(task.getNote())
                        .subTasksIds(task.getSubTasks().stream().map(Task::getTask_id).collect(Collectors.toList()))
                        .build()
                );
    }

    @GetMapping("/root")
    public Optional<Task> getRootTask() {
        return taskRepository.findById(1L);
    }





//
//    private Function<Person, Set<PersonDTO>> findSiblings = person -> person.getParent().getChildren().stream()
//            .map(p -> PersonDTO.builder().id(p.getId()).fullName(p.getFullName()).build()).collect(Collectors.toSet());
//
//    private Function<Person, PersonDTO> mapToPersonDTO = p -> PersonDTO.builder().id(p.getId()).fullName(p.getFullName()).parent(p.getParent()).children(p.getChildren()).build();

}
