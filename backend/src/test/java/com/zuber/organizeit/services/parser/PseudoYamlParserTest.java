package com.zuber.organizeit.services.parser;

import com.zuber.organizeit.Model.DevDTO;
import com.zuber.organizeit.Model.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class PseudoYamlParserTest {

    private static final String TASKS_CLEAN_PATH = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/parser/tasks_clean";
    private static final String REAL_TASKS_WITH_EMPTY_LINES = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/parser/realTasksWithEmptyLines";
    private static final String TEST_PROJECT_DIR = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/parser/testProject";
    @Test
    void parseTasksClean() {
        Optional<Task> task = PseudoYamlParser.parse(TASKS_CLEAN_PATH);
        Assertions.assertThat(task).isNotEmpty();
    }

    @Test
    void parseRealProjectFile() {
        Optional<Task> task = PseudoYamlParser.parse(TASKS_CLEAN_PATH);
        Assertions.assertThat(task).isNotEmpty();
    }

    @Test
    void parseRealProjectFile2() {
        Optional<Task> task = PseudoYamlParser.parse(REAL_TASKS_WITH_EMPTY_LINES);
        Task nestedSubtask = task.get().getSubTasks().get(1).getSubTasks().get(1);
        Assertions.assertThat(nestedSubtask.getName().strip()).isEqualTo("architektura oparta o immutable entities");
    }

    @Test
    void parseTestProject() {
        Optional<Task> task = PseudoYamlParser.parseDir(TEST_PROJECT_DIR);
    }


    @Test
    void traverseDirTest() throws IOException {
        PseudoYamlParser.populateDTOWithTasks(TEST_PROJECT_DIR);
        Assertions.assertThat(DevDTO.tasks.values()).isNotEmpty();
    }

}
