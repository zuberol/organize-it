package com.zuber.organizeit.domain.Task;


import com.zuber.organizeit.domain.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements DomainService {

    public final StreakResolver streakResolver;


    public TaskService(StreakResolver streakResolver) {
        this.streakResolver = streakResolver;
    }


    public int resolveStreak(List<Task> tasks){
        return streakResolver.resolveStreak(tasks);
    }

}
