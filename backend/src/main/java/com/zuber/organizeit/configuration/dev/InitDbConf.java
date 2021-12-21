package com.zuber.organizeit.configuration.dev;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.Task.Task;
import com.zuber.organizeit.Model.Task.TimeEstimates;
import com.zuber.organizeit.services.exporters.parser.rm.NoteDirStructureParser;
import com.zuber.organizeit.services.exporters.rm.FlashcardExporterS;
import com.zuber.organizeit.services.exporters.ProjectExporterS;
import com.zuber.organizeit.services.exporters.SnippetExporterS;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    final ProjectExporterS projectExporterS;
    final FlashcardExporterS flashcardExporterS;
    final SnippetExporterS snippetExporterS;
    final NoteDirStructureParser noteDirStructureParser;
    final TaskRepository taskRepository;


    public InitDbConf(ProjectExporterS projectExporterS, FlashcardExporterS flashcardExporterS, SnippetExporterS snippetExporterS, NoteDirStructureParser noteDirStructureParser, TaskRepository taskRepository) {
        this.projectExporterS = projectExporterS;
        this.flashcardExporterS = flashcardExporterS;
        this.snippetExporterS = snippetExporterS;
        this.noteDirStructureParser = noteDirStructureParser;
        this.taskRepository = taskRepository;
    }

    private static final Path testProject = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/wrapProject/testProject");
        private static final Path projectsDir = Path.of("/home/jakub/Desktop/organize-it-db/projects");
        private static final Path testFlashcardDir = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/testFlashcardDir");
        private static final Path testSnippetDir = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/testSnippet");
        private static final Path snippetsDir = Path.of("/home/jakub/Desktop/organize-it-db/snippets");

        @Override
        public void run(String...args) {
            projectExporterS.initDb(List.of(projectsDir));
            flashcardExporterS.initDb(List.of(testFlashcardDir));
            snippetExporterS.initDb(List.of(snippetsDir));
        }

    @Bean
    public InitializingBean initDB() {
        return () -> {
            Task parent = Task.builder()
                    .name("hehe")
                    .description("n 0 old parent note")
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

