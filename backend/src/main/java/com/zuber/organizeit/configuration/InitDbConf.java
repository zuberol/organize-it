package com.zuber.organizeit.configuration;

import com.zuber.organizeit.services.exporters.FlashcardExporterS;
import com.zuber.organizeit.services.exporters.ProjectExporterS;
import com.zuber.organizeit.services.exporters.SnippetExporterS;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    final ProjectExporterS projectExporterS;
    final FlashcardExporterS flashcardExporterS;
    final SnippetExporterS snippetExporterS;

    public InitDbConf(ProjectExporterS projectExporterS, FlashcardExporterS flashcardExporterS, SnippetExporterS snippetExporterS) {
        this.projectExporterS = projectExporterS;
        this.flashcardExporterS = flashcardExporterS;
        this.snippetExporterS = snippetExporterS;
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


}

