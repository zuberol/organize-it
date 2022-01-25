package com.zuber.organizeit.domain.Task;


import com.zuber.organizeit.domain.DomainService;
import com.zuber.organizeit.domain.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TaskService implements DomainService {

    public final StreakResolver streakResolver;
    private final TaskRepository taskRepository;


    public TaskService(StreakResolver streakResolver, TaskRepository taskRepository) {
        this.streakResolver = streakResolver;
        this.taskRepository = taskRepository;
    }

    public int resolveStreak(List<Task> tasks){
        return streakResolver.resolveStreak(tasks);
    }

    public record TransferTaskCmd(Long removeFrom, Long appendTo, Long what){}

    public Task transferTask(TransferTaskCmd cmd) {
        var whatTask = ofNullable(cmd.what).flatMap(taskRepository::findById).orElseThrow(IllegalAccessError::new);
        ofNullable(cmd.appendTo)
                .flatMap(taskRepository::findById)
                .map(parent -> parent.appendSubtask(whatTask))
                .ifPresent(taskRepository::save);
        ofNullable(cmd.removeFrom)
                .flatMap(taskRepository::findById)
                .map(parent -> parent.removeSubtask(whatTask));
        return whatTask;
    }

}
