package com.zuber.organizeit.configuration;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.Task;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;

@Configuration
public class DbConf {

    @Autowired
    TaskRepository taskRepository;

    @Bean
    public InitializingBean initDB() {
        return () -> {
            Task parent = new Task(
                    null,
                    "n 0 old parent note",
                    true,
                    Arrays.asList(
                            new Task(
                                    null,
                                    "n 1 child id 2 note",
                                    false,
                                    new LinkedList<>(),
                                    new LinkedList<>()

                            ),
                            new Task(
                                    null,
                                    "n 1 child id 3 note",
                                    false,
                                    Arrays.asList(
                                            new Task(
                                                    null,
                                                    "n 2 child id 4 note",
                                                    false,
                                                    Arrays.asList(
                                                            new Task(
                                                                    null,
                                                                    "n 3 child id 5 note",
                                                                    false,
                                                                    new LinkedList<>(),
                                                                    new LinkedList<>()
                                                            )
                                                    ),
                                                    new LinkedList<>()
                                            )
                                    ),
                                    new LinkedList<>()
                            )
                    ),
                    new LinkedList<>()
            );
            taskRepository.save(parent);
        };
    }
}
