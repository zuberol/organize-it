package com.zuber.organizeit.domain.Task;

import com.zuber.organizeit.domain.Plan.PlanStatus;
import com.zuber.organizeit.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;


@Component
public class StreakResolver {

    static int resolveStreak(List<Task> tasks){
        var sorted = tasks.stream()
                .sorted(comparing(t -> t.getStatus().getTimeEstimates().getCreated()))
                .toList();

        List<Task> recentStreakTasks = new ArrayList<>();
        for (Task next : sorted) {
            if (next.getStatus().getDone().isDone()) recentStreakTasks.add(next);
            else recentStreakTasks.clear();
        }
        return recentStreakTasks.size();
    }



}
