package com.zuber.organizeit.configuration;

import com.zuber.organizeit.services.exporter.ExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Profile("dev")
public class InitDbConf implements CommandLineRunner {

    ExporterService exporterService;

    @Autowired
    public InitDbConf(ExporterService exporterService) {
        this.exporterService = exporterService;
    }

        private static final Path testProject = Path.of("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporter/wrapProject/testProject");

        @Override
        public void run(String...args) throws Exception {
            exporterService.initDb(List.of(testProject));
        }


}

