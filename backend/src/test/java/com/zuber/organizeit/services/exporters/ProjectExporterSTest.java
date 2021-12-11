package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Repository.TaskRepository;
import com.zuber.organizeit.Model.Task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest//(classes = {FlashcardsController.class})
@DataJpaTest
@Disabled("Broken")
//@RunWith(SpringRunner.class)
class ProjectExporterSTest {


    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EntityManager entityManager;

    @Autowired
    ProjectExporterS projectExporterS;

//
//    @TestConfiguration
//    static class EmployeeServiceImplTestContextConfiguration {
//
//        @Bean
//        ExporterService exporterService(){
//            return new ExporterService(new EntityDAO(taskRepository, entityManager));
//        }
//    }

//    @Autowired
//    public ExporterServiceTest(TaskRepository taskRepository, ExporterService exporterService) {
//        this.taskRepository = taskRepository;
//        this.exporterService = exporterService;
//    }

    @Test
    @Disabled
    void yamlProjectToTree() throws IOException {

        Path path = Paths.get("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/integration/spaces");
        String read = Files.readAllLines(path).get(0);

        Assertions.assertEquals(2, countLeadingTabs("\t\t"));
    }

    @Test
    void shouldUpdateExistingProject() {

        Task testProject = Task.builder()
                .name("testProject")
                .locallySavedURI("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/wrapProject/testProject")
                .build();
        Task dyscyplinaProject = Task.builder()
                .name("dyscyplina")
                .locallySavedURI("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/wrapProject/testProject/dyscyplina")
                .build();
        LinkedList<Task> subTasks = new LinkedList<>();
        subTasks.add(dyscyplinaProject);
        testProject.setSubTasks(subTasks);
        Task save = taskRepository.save(testProject);
        taskRepository.flush();
        assertThat(taskRepository.findAll().size()).isEqualTo(2);

        projectExporterS.initDb(List.of(Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/testProject")));
        taskRepository.flush();
        assertThat(taskRepository.findTaskByName("testProject").get().getSubTasks().size()).isEqualTo(5);

    }


    private int countLeadingTabs(String s) {
        return countLeadingTabs(s, 0);
    }

    private int countLeadingTabs(String s, int offset) {
        int numTabs = 0;
        if (s.substring(offset).matches("^\\t")) numTabs = countLeadingTabs(s, offset+4);
        return numTabs;
    }

}