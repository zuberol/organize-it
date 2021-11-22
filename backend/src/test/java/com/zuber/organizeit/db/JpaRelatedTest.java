package com.zuber.organizeit.db;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.Task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@DataJpaTest
////@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class JpaRelatedTest {

    @TestConfiguration
    static public class DbConf {

        @Autowired
        TaskRepository taskRepository;

        //    @MockBean
//    @MockBean
        @Bean
        InitializingBean sendDatabase() {
            return () -> {
                Task parent = Task.builder()
                        .isProject(true)
                        .description("old parent note")
                        .tags(List.of())
                        .subTasks(
                        List.of(
                                Task.builder().description("child 1 note").build(),
                                Task.builder().description("child 2 note").build()
                        )
                ).build();
                taskRepository.save(parent);
            };
        }
    }

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void isInitialDataLoaded() {
        assertEquals(3, taskRepository.findAll().size());
    }


//    @Test
//    void saveParentAndItsChildren() {
//        LinkedList<Tag> tags = new LinkedList<>(List.of(new Tag(null, List.of("docker", "rybka")),
//                new Tag(null, List.of("java", "kawka"))));
//        Task parentTask = new Task(null, "parent task", true, null, null,
//                tags);
//        Task child1 = new Task(
//                null,
//                "child 1",
//                false,
//                parentTask,
//                null,
//                null
//
//        );
//        Task child2 = new Task(
//                null,
//                "child 1",
//                false,
//                parentTask,
//                null,
//                null
//
//        );
//        parentTask.setSubTasks(new LinkedList<>(List.of(child1, child2)));
//
//
//        testEntityManager.persist(parentTask);
//
//
//        assertEquals(3, taskRepository.findAll().size());
//    }
//
//    @Test
//    void middleUpdateShouldPropagateToOthers() {
//        Task upper = new Task(
//                null,
//                "upper",
//                true,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//        Task middle = new Task(
//                null,
//                "middle",
//                true,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//        Task lower = new Task(
//                null,
//                "lower",
//                true,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//
//        upper.getSubTasks().add(middle);
//        middle.setParentTask(upper);
//        middle.getSubTasks().add(lower);
//        lower.setParentTask(middle);
//
//        testEntityManager.persist(upper);
//        List<Task> tasks = taskRepository.findAll();
//        assertEquals(3, tasks.size());
//
//        final String MIDDLE_MODIFIED_NOTE = "middle modified note";
//        Task middleFromList = tasks.get(1);
//        middleFromList.setNote(MIDDLE_MODIFIED_NOTE);
//
//        assertEquals(MIDDLE_MODIFIED_NOTE, tasks.get(0).getSubTasks().get(0).getNote());
////        taskRepository.save(middleFromList);
//    }
//
//    @Test
//    void sameObjectOrCopyRepository() {
//        Task taskToPersist = new Task(
//                null,
//                "sameObjectOrCopyRepository",
//                true,
//                null,
//                null,
//                null
//        );
//        Task persistedTask = testEntityManager.persist(taskToPersist);
//        Assertions.assertNotNull(persistedTask.getTaskId());
//        Assertions.assertSame(persistedTask, taskToPersist);
//    }
//
//
//    @Test
//    void shallSaveAfterChange() {
//        Task childTask = new Task(
//                null,
//                "child task note",
//                false,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//        List<Task> subTasks = new LinkedList<>(List.of(childTask));
//        Task parentTask = new Task(
//                null,
//                "sameObjectOrCopyRepository",
//                true,
//                null,
//                subTasks,
//                null
//        );
//
//        Task persistedParent = testEntityManager.persist(parentTask);
//        persistedParent.setNote("changed note");
//        Long changedNoteTaskId = testEntityManager.persistAndGetId(parentTask, Long.class);
//
//
//        Task changedNoteTaskFromDB = testEntityManager.find(Task.class, changedNoteTaskId);
//        assertEquals("changed note", changedNoteTaskFromDB.getNote());
//        assertNotEquals("sameObjectOrCopyRepository", changedNoteTaskFromDB.getNote());
//
//    }
//
//    @Test
//    void doesLiveInCacheOrInDbToo() {
//        String INITIAL_NOTE = "initial note";
//        String WONT_PERSIST_LIVES_ONLY_IN_CACHE = "that wont be persisted";
//
//        Task task = new Task(
//                null,
//                INITIAL_NOTE,
//                false,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//
//        Task persistedTask = testEntityManager.persist(task);
//        persistedTask.setNote(WONT_PERSIST_LIVES_ONLY_IN_CACHE);
//        assertEquals(WONT_PERSIST_LIVES_ONLY_IN_CACHE, persistedTask.getNote());
//
//        // REFRESH CACHE SO WE LOST WONT_PERSIST_...
//        testEntityManager.refresh(persistedTask);
//        assertEquals(INITIAL_NOTE, persistedTask.getNote());
//
//    }
//
//    @Test
//    void saveTaskWithNullParent() {
//        Task task = new Task(
//                null,
//                "some note",
//                false,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//
//        taskRepository.save(task);
//    }
//
//    @Test // todo
//    void updateTaskWithNullParent() {
//        Task parent = new Task(
//                null,
//                "old parent note",
//                false,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//        parent = taskRepository.save(parent);
//
//        Task child = new Task(
//                null,
//                "old child note",
//                false,
//                null,
//                new LinkedList<>(),
//                new LinkedList<>()
//        );
//        child = taskRepository.save(child);
//
//
//
//
//
//    }


}
