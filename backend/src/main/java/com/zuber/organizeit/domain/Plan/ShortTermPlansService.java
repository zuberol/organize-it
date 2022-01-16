package com.zuber.organizeit.domain.Plan;


import com.zuber.organizeit.domain.DomainService;
import com.zuber.organizeit.domain.Repository.EntityDAO;
import com.zuber.organizeit.domain.Task.TaskService;
import org.springframework.stereotype.Service;

@Service
public class ShortTermPlansService implements DomainService {

    private final EntityDAO entityDAO;
    public final TaskService taskService;

    public ShortTermPlansService(EntityDAO entityDAO, TaskService taskService) {
        this.entityDAO = entityDAO;
        this.taskService = taskService;
    }

    public int resolvePlanStatus(String rootTaskName, String planName) {
        var plan = entityDAO.findPlanByName(planName)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        var rootTask = plan.getRootTasks().stream().filter(task -> task.getName().equals(rootTaskName))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Root task not found"));
        return taskService.resolveStreak(rootTask.getSubTasks());
    }

}
