package com.zuber.organizeit.services.exporter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileParserTest {
    int countLeadingTabs(String s) {
        return countLeadingTabs(s, 0);
    }

    private int countLeadingTabs(String s, int offset) {
        int numTabs = 0;
        if (s.substring(offset).matches("^\\t")) numTabs = countLeadingTabs(s, offset+4);
        return numTabs;
    }


    @Test
    public void yamlProjectToTree() throws IOException {

        Path path = Paths.get("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/integration/spaces");
        String read = Files.readAllLines(path).get(0);

        Assertions.assertEquals(2, countLeadingTabs("\t\t"));
    }
}