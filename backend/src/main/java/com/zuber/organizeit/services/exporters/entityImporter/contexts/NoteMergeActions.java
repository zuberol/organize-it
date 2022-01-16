package com.zuber.organizeit.services.exporters.entityImporter.contexts;


import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Note.Note;
import com.zuber.organizeit.services.exporters.entityImporter.ParseCtx;
import com.zuber.organizeit.services.exporters.entityImporter.MergeActionDef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteMergeActions {

    @Bean
    MergeActionDef<Note, NoteCtx> openFlashcardCtx() {
        return (noteCtx, linesIterator) -> {
            ParseCtx<Flashcard> flashcardParseCtx = null; // todo
//            noteCtx.pushNestedAncestor(flashcardParseCtx);
        };
    }

    @Bean
    MergeActionDef<Note, NoteCtx> closeFlashcardCtx() {
        return (noteCtx, linesIterator) -> {
            ParseCtx<Flashcard> flashcardParseCtx = null; // todo
//            noteCtx.peekNestedAncestors();
        };
    }



}
