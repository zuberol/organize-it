package com.zuber.organizeit.configuration.dev;

import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Plan.ShortTermPlan;
import com.zuber.organizeit.domain.Plan.ShortTermPlanRepository;
import com.zuber.organizeit.domain.Repository.FlashcardsRepository;
import com.zuber.organizeit.domain.Repository.TagsRepository;
import com.zuber.organizeit.domain.Repository.TaskRepository;
import com.zuber.organizeit.domain.Tag;
import com.zuber.organizeit.domain.Task.Task;
import com.zuber.organizeit.services.exporters.entityImporter.EntityImporterS;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    final TaskRepository taskRepository;
    final FlashcardsRepository flashcardsRepository;
    final ShortTermPlanRepository shortTermPlanRepository;
    final EntityImporterS entityImporterS;
    final TagsRepository tagsRepository;

    private static final Path testProject = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/wrapProject/testProject");
    private static final Path projectsDir = Path.of("/home/jakub/Desktop/organize-it-db/projects");
    private static final Path testFlashcardDir = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/testFlashcardDir");
    private static final Path testSnippetDir = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/testSnippet");
    private static final Path snippetsDir = Path.of("/home/jakub/Desktop/organize-it-db/snippets");


    public InitDbConf(TaskRepository taskRepository, FlashcardsRepository flashcardsRepository, ShortTermPlanRepository shortTermPlanRepository, EntityImporterS entityImporterS, TagsRepository tagsRepository) {
        this.taskRepository = taskRepository;
        this.flashcardsRepository = flashcardsRepository;
        this.shortTermPlanRepository = shortTermPlanRepository;
        this.entityImporterS = entityImporterS;
        this.tagsRepository = tagsRepository;
    }

    @Override
    public void run(String... args) {}

    @Bean
    public InitializingBean initDB() {
        return () -> {

            Task parent = Task.builder()
                    .name("hehe")
                    .description("n 0 old parent  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                    .subTasks(
                            List.of(
                                    Task.builder()
                                            .name("xddasdasda")
                                            .description("n 1 child id 2 note")
                                            .subTasks(List.of())
//                                            .tags(List.of())
//                                            .timeEstimates(
//                                                    TimeEstimates.builder()
//                                                            .estimatedEnd(new Timestamp(Duration.ofDays(2).toMillis()))
//                                                            .created(Timestamp.from(Instant.now()))
//                                                            .build()
//                                            )
                                            .build()
                                    ,
                                    Task.builder()
                                            .name("lorem ipsm")
                                            .description("n 1 child id 3 note")
                                            .subTasks(List.of(
                                                    Task.builder()
                                                            .description("n 2 child id 4 note")
                                                            .subTasks(List.of())
//                                                            .tags(List.of())
//                                                            .timeEstimates(
//                                                                    TimeEstimates.builder()
//                                                                            .estimatedEnd(new Timestamp(Duration.ofDays(2).toMillis()))
//                                                                            .spent(new Timestamp(Duration.ofDays(4).toMillis()))
//                                                                            .created(Timestamp.from(Instant.now()))
//                                                                            .build()
//                                                            )
                                                            .build(),
                                                    Task.builder()
                                                            .description("n 2 child id 4 note")
                                                            .subTasks(List.of())
//                                                            .tags(List.of())
//                                                            .timeEstimates(
//                                                                    TimeEstimates.builder()
//                                                                            .estimatedEnd(new Timestamp(Duration.ofDays(2).toMillis()))
//                                                                            .spent(new Timestamp(Duration.ofDays(4).toMillis()))
//                                                                            .created(Timestamp.from(Instant.now()))
//                                                                            .build()
//                                                            )
                                                            .build()
                                            ))
//                                            .tags(List.of())
//                                            .timeEstimates(
//                                                    TimeEstimates.builder()
//                                                            .estimatedEnd(new Timestamp(Duration.ofDays(2).toMillis()))
//                                                            .spent(new Timestamp(Duration.ofDays(4).toMillis()))
//                                                            .created(Timestamp.from(Instant.now()))
//                                                            .build()
//                                            )
                                            .build()
                            )
                    ).build();

            Task doneTask = Task.builder().name("undoneTaskkk")
//                    .isDone(true)
                    .build();


//            List<Task> persistedTasks = taskRepository.saveAll(List.of(parent, doneTask));
            List<Task> persistedTasks = List.of( doneTask);
            ShortTermPlan shortTermPlan = ShortTermPlan.builder()
                    .name("No smoking")
//                    .rootTasks(persistedTasks)
                    .description("kidshkagdaisghdkash")
                    .build();
            ShortTermPlan shortTermPlan2 = ShortTermPlan.builder()
                    .name("Running")
                    .rootTasks(persistedTasks)
                    .description("jty7rf78utmnjty")
                    .build();
//
//            plan = planRepository.save(plan);
//            plan2 = planRepository.save(plan2);
//
////            plan2.getRootTasks().clear();

            shortTermPlan = shortTermPlanRepository.save(shortTermPlan);

//            taskRepository.save(doneTask);
            shortTermPlan.getRootTasks().add(doneTask);
            shortTermPlanRepository.save(shortTermPlan);

//            shortTermPlanRepository.save(shortTermPlan2);

            shortTermPlanRepository.save(
                    ShortTermPlan.builder()
                            .name("inbox")
                            .rootTasks(List.of())
                            .build()
            );

//            shortTermPlan.getRootTasks().get(0).setName("jak to zobacze to ok");
//            shortTermPlan.setName("detached name, nie powinienem tego zobaczyc po refreshu");
//            planRepository.

//            System.out.println("before refresh: " + plan.getName());
//            em.refresh(plan);
//            System.out.println("after refresh: " + plan.getName());
//            System.out.println(plan.getRootTasks().get(0).getName());
//




        };
    }

    @Bean
    public InitializingBean initFlashcards() {
        return () -> {
            Flashcard flashcard1 = new Flashcard();
            flashcard1.setTags(List.of(new Tag("docker")));
            flashcardsRepository.save(flashcard1);

            Flashcard flashcard2 = new Flashcard();
            flashcard2.setTags(List.of(new Tag("dockerd"), new Tag("hehe")));
            flashcardsRepository.save(flashcard2);

            Flashcard flashcard3 = new Flashcard();
            flashcard3.setTags(List.of(new Tag("hehed")));
            flashcardsRepository.save(flashcard3);

        };
    }

    @Bean
    public InitializingBean importerTest() {
        return () -> {
            final String file = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/GenericParser/backet-structure-note";
//            Note note = entityImporterS.importEntity(Note.class, Path.of(file));
        };
    }

    @Bean
    public InitializingBean uniqueTagsTest() {
        return () -> {

//            tagsRepository.saveAll(List.of(Tag.builder().mainName("hehe").build(),Tag.builder().mainName("hehe").build()));


        };
    }





}

