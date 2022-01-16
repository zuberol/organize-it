package com.zuber.organizeit.services.planner;

import com.zuber.organizeit.domain.Task.Done;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.domain.Task.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.time.Duration.ofDays;
import static java.time.LocalDateTime.now;

class ShortTermStreakResolverTest {

    @Test
    void shouldComputeStreak() {
    }

    @Test
    void resolveStreakForRootTask() {
    }

//    @Test
//    @Disabled
//    void shouldComputeStreak() {
//        resolveStreakForRootTask(initTest());
//    }


    private List<Task> initTasks(int daysOffset, Boolean ...isDone) {

        List<Task> tasks = new ArrayList<>();

        for(int i=0; i<isDone.length; ++i) {
            tasks.add(
                    Task.builder()
                            .status(TaskStatus.builder()
                                    .done(Done.builder().when(
                                                    now().plus(ofDays(i + daysOffset))
                                            )
                                            .build())
                                    .build()
                            )
                            .build()
            );
        }
        return tasks;
    }

    private List<Task> initTest() {
        return Stream.concat(
                initTasks(3, true, false, true, true, false, false, true, false, true, true, true, true, true).stream(),
                initTasks(1, true, true, true, false, false, true, false, true, true, true, true, true).stream()
        ).toList();
    }

}