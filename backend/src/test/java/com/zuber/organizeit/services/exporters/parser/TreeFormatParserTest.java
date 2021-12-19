package com.zuber.organizeit.services.exporters.parser;

import com.zuber.organizeit.Model.Note.Note;
import com.zuber.organizeit.Model.Note.ReferenceResource.SimpleLinkResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TreeFormatParserTest {

    private final Path ograniczonaFunkcjonalnosc = Paths.get("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/NoteParser/testRootNote/ograniczona-funkcjonalnosc-note");

    @Test
    void useWithSameLevelPolicy() throws IOException {

        TreeFormatParser treeFormatParser = new TreeFormatParser();
        Optional<NoteParseCtx> noteParseCtx = treeFormatParser.useWithSameLevelPolicy(Files.readAllLines(ograniczonaFunkcjonalnosc));


        Note note = noteParseCtx.get().ctxObject();
        assertThat(note.getName()).isEqualTo("notatka o dokerze");
        assertThat(note.getReferenceResources().get(0))
                .isEqualTo(new SimpleLinkResource("https://docs.docker.com/engine/"));
        assertThat(note.getReferenceResources().get(1))
                .isEqualTo(new SimpleLinkResource("https://docs.docker.com/get-started/overview/#docker-architecture"));

    }


    @Test
    void getLevelLine() throws IOException {
        assertThat(ParseContext.getLevel(Files.readAllLines(ograniczonaFunkcjonalnosc).get(0))).isEqualTo(1);
    }

}