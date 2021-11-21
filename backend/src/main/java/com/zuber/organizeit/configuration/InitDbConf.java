package com.zuber.organizeit.configuration;

import com.zuber.organizeit.services.exporter.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    Exporter exporter;

    @Autowired
    public InitDbConf(Exporter exporter) {
        this.exporter = exporter;
    }

        private static final Path testProject = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/parser/testProject");

        @Override
        public void run(String...args) throws Exception {
//            PseudoYamlParser.populateDTOWithTasks(path); //todo broken to bylo chyba
            exporter.initDb(List.of(testProject));
        }


}

