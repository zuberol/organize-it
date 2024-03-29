package com.zuber.organizeit.controllers;


import com.zuber.organizeit.domain.AppOrchestratorService;
import com.zuber.organizeit.domain.Note.Flashcard.Deck;
import com.zuber.organizeit.domain.Plan.ShortTermPlan;
import com.zuber.organizeit.domain.Plan.ShortTermPlanDTO;
import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Note.Flashcard.DeckTO;
import com.zuber.organizeit.domain.Status;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskService;
import com.zuber.organizeit.domain.Task.TaskTO;
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

    private final EntityDAO entityDao;
    private final AppOrchestratorService orchestratorS;
    private final TaskService taskService;

    @Autowired
    public PlansController(EntityDAO entityDao, AppOrchestratorService orchestratorS, TaskService taskService) {
        this.entityDao = entityDao;
        this.orchestratorS = orchestratorS;
        this.taskService = taskService;
    }


    @PostMapping("/plan")
    public ResponseEntity<ShortTermPlan> modifyPlan(@RequestBody ShortTermPlanDTO shortTermPlanDTO) {
        return ResponseEntity.of(ofNullable(shortTermPlanDTO).flatMap(entityDao::modifyPlan));
    }

    @GetMapping("/plans")
    public List<ShortTermPlan> getPlanByName(@RequestParam(required = false) String planName) {
        if (planName != null) {
            Utils.notImplementedYet.run();
            return null;
//            return entityDao.findAllNonArchivedTasks().stream().filter(project -> project.getName().equals(planName)).toList();
        } else {
            return entityDao.findAllPlans();
        }
    }

    @GetMapping("/plan/status")
    public Optional<Status> planStatus(@RequestParam(required = false, name = "id") Long planId) {
        Utils.notImplementedYet.run();
        return Optional.empty();
    }

    @PostMapping(value = "/plan/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ShortTermPlan createPlan(@RequestBody ShortTermPlanDTO shortTermPlanDTO) {
        return entityDao.createPlan(shortTermPlanDTO);
    }

    @GetMapping(value = "/task")
    public ResponseEntity<Task> getTaskById(@RequestBody TaskTO taskTO) {
        return ResponseEntity.of(ofNullable(taskTO).flatMap(entityDao::findTaskById));
    }

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> createModifyTask(@RequestBody TaskTO taskTO) {
        return ResponseEntity.of(ofNullable(taskTO).map(orchestratorS::newTaskInShortTermPlan));
    }

    @PostMapping(value = "/task/transfer", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> createModifyTask(@RequestBody TaskService.TransferTaskCmd cmd) {
        return ResponseEntity.of(ofNullable(cmd).map(taskService::transferTask));
    }

    @GetMapping(value = "/tasks/inbox")
    public List<Task> getInboxTasks() {
        return entityDao.getInboxTasks();
    }

    @PostMapping(value = "/task/subtask/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //todo zmienic nazwe endpointa
    public ResponseEntity<Task> appendNewSubtask(@RequestBody TaskTO taskTO) { // todo validate
        return ResponseEntity.of(ofNullable(taskTO).flatMap(entityDao::appendNewSubtask));
    }

    @PostMapping(value = "/deck/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Deck> createDeck(@RequestBody DeckTO deckDTO) {
        return ResponseEntity.of(ofNullable(deckDTO).map(entityDao::createDeck));

    }

}
