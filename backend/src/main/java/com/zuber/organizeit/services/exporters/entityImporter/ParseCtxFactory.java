package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Note.Note;
import com.zuber.organizeit.domain.Note.NotePart;
import com.zuber.organizeit.services.exporters.entityImporter.contexts.FlashcardCtx;
import com.zuber.organizeit.services.exporters.entityImporter.contexts.NoteCtx;
import com.zuber.organizeit.services.exporters.entityImporter.contexts.NotePartCtx;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;
import org.springframework.stereotype.Component;

@Component
public class ParseCtxFactory implements CtxCreator {

    @Override
    public <E extends BaseEntity<Long>> ParseCtx<E> getLevelAwareNewCtx(Class<E> initCtx) {
        return getLevelAwareNewCtx(initCtx, 0);
    }

    @Override
    public <E extends BaseEntity<Long>> ParseCtx<E> getLevelAwareNewCtx(Class<E> initCtx, int level) {

        if(initCtx.equals(Note.class)) return (ParseCtx<E>) new NoteCtx(Note.builder().build(), level);
        else if(initCtx.equals(Flashcard.class)) return  (ParseCtx<E>) new FlashcardCtx(Flashcard.builder().build(), level);
        else if(initCtx.equals(NotePart.class)) return  (ParseCtx<E>) new NotePartCtx(NotePart.builder().build(), level);
        else throw new ParserIllegalState("Ctx not known for class: " + initCtx.getName());

    }

    @Override
    public ParseCtx<?> getLevelAwareNewCtx(String ctxTag, int level) {

        return switch (ctxTag) {
            case "Flashcard", "Fc": yield getLevelAwareNewCtx(Flashcard.class, level);
            case "Note", "N": yield getLevelAwareNewCtx(Note.class, level);
            case "NotePart", "NP": yield getLevelAwareNewCtx(NotePart.class, level);
            default: throw new ParserIllegalState("ctx not known");
        };

    }

}