package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.Note.Note;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState.CANT_MERGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YmlTreeParserTest {

    private static final String testDir = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/entityImporter";
    private static final String throwDir = "/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/exporters/entityImporter/throw";

//    @Test
//    void withBlanks() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(testDir + "/2");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        noteYmlTreeParser.run(lines);
//
//    }
//
//    @Test
//    void withAllTags() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(testDir + "/3");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        noteYmlTreeParser.run(lines);
//        System.out.println("hehe");
//    }
//
////Docker engine
////  Ref: https://docs.docker.com/engine/, https://docs.docker.com/get-started/overview/#docker-architecture
////
////  Czy "docker" i "dockerd" musza byc na jednej maszynie?
////    http://docker.com Prio: 3
////    CodeRef: NestedCR.java
////
////    some note prttttttt
////      Prio: 4
////  docker, uberhard
////  Ref: https://www.baeldung.com/spring-boot-testing
////    Nie, dockerd moze byc remote
////
////  CodeRef: CodeRefTest.java
//
//    @Test
//    @DisplayName("should be able to create reference resource in note")
//    void referenceInNote() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(testDir + "/3");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        Optional<Note> run = noteYmlTreeParser.run(lines);
////        assertThat(run.get().getReferenceResources()).hasSize(3);
//
//        System.out.println("hehhe");
//    }
//
//    @Test
//    @DisplayName("ZIP SHOULD WORK HERE")
//    void file5() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(testDir + "/5-merge");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        Optional<Note> run = noteYmlTreeParser.run(lines);
//        assertThat(run.get().getReferenceResources()).hasSize(1);
//
//        System.out.println("hehhe");
//    }
//
//
//
//
//
//    // throw dir
//
//    @Test
//    @DisplayName("First yml line must have proper entity tag")
//    void shouldThrow() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(throwDir + "/throw-2");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        IllegalStateException ex = assertThrows(ParserIllegalState.class, () -> noteYmlTreeParser.run(lines));
//        assertThat(ex.getMessage()).isEqualTo("Ctx tag is missing");
//
//    }
//
//    @Test
//    @DisplayName("Should throw on unknown metaTag")
//    void shouldThrow2() throws IOException {
//        CtxCreator ctxCreator = new ParseCtxFactory();
//        var firstTest = Paths.get(throwDir + "/throw-1");
//        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
//        List<String> lines = Files.readAllLines(firstTest);
//
//        IllegalStateException ex = assertThrows(ParserIllegalState.class, () -> noteYmlTreeParser.run(lines));
//        assertThat(ex.getMessage()).isEqualTo(CANT_MERGE);
//
//    }

    @Test
    @DisplayName("Should create proper ctx'es with default line merge")
    void shouldThrow3() throws IOException {
        CtxCreator ctxCreator = new ParseCtxFactory();
        var firstTest = Paths.get(testDir + "/3");
        YmlTreeParser<Note> noteYmlTreeParser = new YmlTreeParser<>(ctxCreator, Note.class);
        List<String> lines = Files.readAllLines(firstTest);

        noteYmlTreeParser.run(lines);

    }


}