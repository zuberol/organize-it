package com.zuber.organizeit.configuration;

import com.zuber.organizeit.services.exporters.FlashcardExporterService;
import com.zuber.organizeit.services.exporters.ProjectExporterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    ProjectExporterService projectExporterService;
    FlashcardExporterService flashcardExporterService;

    public InitDbConf(ProjectExporterService projectExporterService, FlashcardExporterService flashcardExporterService) {
        this.projectExporterService = projectExporterService;
        this.flashcardExporterService = flashcardExporterService;
    }

    private static final Path testProject = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/wrapProject/testProject");
        private static final Path projectsDir = Path.of("/home/jakub/Desktop/organize-it-db/projects");
        private static final Path testFlashcardDir = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/testFlashcardDir");

        @Override
        public void run(String...args) {
            projectExporterService.initDb(List.of(projectsDir));
            flashcardExporterService.initDb(List.of(testFlashcardDir));
        }


}

