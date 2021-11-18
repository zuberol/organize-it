package com.zuber.organizeit.configuration;

import com.zuber.organizeit.services.parser.PseudoYamlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class PseudoYamlInitDb implements CommandLineRunner {


        private static final String path = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/parser/testProject";

        @Override
        public void run(String...args) throws Exception {
            PseudoYamlParser.populateDTOWithTasks(path); //todo broken to bylo chyba
        }


}

