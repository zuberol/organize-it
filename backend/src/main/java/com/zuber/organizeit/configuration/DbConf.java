package com.zuber.organizeit.configuration;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TimeEstimates;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Configuration
@Profile("dev")
public class DbConf {

    @Autowired
    TaskRepository taskRepository;

    @Bean
    public InitializingBean initDB() {
        return () -> {
            Task parent = Task.builder()
                    .name("hehe")
                    .description("n 0 old parent  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                            "\n")
                    .isProject(true)
                    .subTasks(
                            List.of(
                            Task.builder()
                                    .name("xddasdasda")
                                    .description("n 1 child id 2 note")
                                    .subTasks(List.of())
                                    .tags(List.of())
                                    .timeEstimates(
                                            TimeEstimates.builder()
                                                    .timeEstimated(new Timestamp(Duration.ofDays(2).toMillis()))
                                                    .whenCreated(Timestamp.from(Instant.now()))
                                                    .build()
                                    ).build()
                            ,
                            Task.builder()
                                    .name("lorem ipsm")
                                    .description("n 1 child id 3 note")
                                    .subTasks(List.of(
                                            Task.builder()
                                                    .description("n 2 child id 4 note")
                                                    .subTasks(List.of())
                                                    .tags(List.of())
                                                    .timeEstimates(
                                                            TimeEstimates.builder()
                                                                    .timeEstimated(new Timestamp(Duration.ofDays(2).toMillis()))
                                                                    .timeSpent(new Timestamp(Duration.ofDays(4).toMillis()))
                                                                    .whenCreated(Timestamp.from(Instant.now()))
                                                                    .build()
                                                    ).build(),
                                            Task.builder()
                                                    .taskId(null)
                                                    .isProject(false)
                                                    .description("n 2 child id 4 note")
                                                    .subTasks(List.of())
                                                    .tags(List.of())
                                                    .timeEstimates(
                                                            TimeEstimates.builder()
                                                                    .timeEstimated(new Timestamp(Duration.ofDays(2).toMillis()))
                                                                    .timeSpent(new Timestamp(Duration.ofDays(4).toMillis()))
                                                                    .whenCreated(Timestamp.from(Instant.now()))
                                                                    .build()
                                                    ).build()
                                    ))
                                    .tags(List.of())
                                    .timeEstimates(
                                            TimeEstimates.builder()
                                                    .timeEstimated(new Timestamp(Duration.ofDays(2).toMillis()))
                                                    .timeSpent(new Timestamp(Duration.ofDays(4).toMillis()))
                                                    .whenCreated(Timestamp.from(Instant.now()))
                                                    .build()
                                    ).build()
                    )
            ).build();

            Task doneTask = Task.builder().name("undoneTaskkk")
                    .isDone(true)
                    .build();


            taskRepository.save(parent);
            taskRepository.save(doneTask);
        };
    }
}
