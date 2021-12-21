package com.zuber.organizeit.services.exporters.parsers;

import com.zuber.organizeit.Model.Note.Note;
import com.zuber.organizeit.Model.Note.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.services.exporters.parsers.ctx.NoteParseCtx;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class NoteParserTest {

    private final Path ograniczonaFunkcjonalnosc = Paths.get("/home/jakub/IdeaProjects/personal/organize-it/backend/src/test/java/com/zuber/organizeit/services/NoteParser/testRootNote/ograniczona-funkcjonalnosc-note");

    @Test
    void useWithSameLevelPolicy() throws IOException {
        NoteParser noteParser = new NoteParser();
        Optional<NoteParseCtx> noteParseCtx = noteParser.useWithSameLevelPolicy(ograniczonaFunkcjonalnosc);


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