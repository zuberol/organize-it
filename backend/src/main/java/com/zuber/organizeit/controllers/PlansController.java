package com.zuber.organizeit.controllers;


import com.zuber.organizeit.domain.Plan.ShortTermPlan;
import com.zuber.organizeit.domain.Plan.PlanDTO;
import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskDTO;
import com.zuber.organizeit.domain.Plan.ShortTermPlansService;
import com.zuber.organizeit.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.Optional.ofNullable;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://85.222.6.237:3000"}, allowCredentials = "true")
@RequestMapping("/api")
public class PlansController {

    EntityDAO entityDao;
    ShortTermPlansService shortTermPlansService;

    @Autowired
    public PlansController(EntityDAO entityDao) {
        this.entityDao = entityDao;
    }


    @PostMapping("/plan")
    public ResponseEntity<ShortTermPlan> modifyPlan(@RequestBody PlanDTO planDTO) {
        return ResponseEntity.of(
                ofNullable(planDTO).flatMap(entityDao::modifyPlan)
        );
    }

    public int getPlanStreakStatus(String rootTaskName, String planName) {
        return shortTermPlansService.resolvePlanStatus(rootTaskName, planName);
    }

    @GetMapping("/plans")
    public List<ShortTermPlan> getPlanByName(@RequestParam(required = false) String planName) {
        if(planName != null){
            Utils.notImplementedYet.run();
            return null;
//            return entityDao.findAllNonArchivedTasks().stream().filter(project -> project.getName().equals(planName)).toList();
        } else {
            return entityDao.findAllPlans();
        }
    }

    @GetMapping("/plan/new")
    public ShortTermPlan createPlan(@RequestBody PlanDTO planDTO) {
        Utils.notImplementedYet.run();
        return null;
    }


    @GetMapping(value = "/task")
    public ResponseEntity<Task> getTaskById(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.of(
                ofNullable(taskDTO).flatMap(entityDao::findById)
        );
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> modifyTask(@RequestBody TaskDTO taskDTO) { // todo validate
        return ResponseEntity.of(
                ofNullable(taskDTO).flatMap(entityDao::modifyTask)
        );
    }

    @PostMapping(value = "/task/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @RequestParam(required = false) Long parentTaskId) { // todo validate
        if(parentTaskId != null) return ResponseEntity.of(ofNullable(taskDTO).flatMap(dto -> entityDao.appendSubtask(dto, parentTaskId)));
        else return ResponseEntity.of(
                ofNullable(taskDTO).flatMap(entityDao::createTask)
        );
    }


    @GetMapping(value = "/task/inbox")
    public List<Task> getInboxTasks() {
        return entityDao.getInboxTasks();
    }

    @PostMapping(value = "/task/subtask/put", consumes = {MediaType.APPLICATION_JSON_VALUE}) //todo zmienic nazwe endpointa
    public ResponseEntity<Task> appendNewSubtask(@RequestBody TaskDTO taskDTO) { // todo validate
        return ResponseEntity.of(
               ofNullable(taskDTO).flatMap(entityDao::appendNewSubtask)
        );
    }

}
