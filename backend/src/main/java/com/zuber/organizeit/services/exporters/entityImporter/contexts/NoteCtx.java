package com.zuber.organizeit.services.exporters.entityImporter.contexts;


import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.Note.Note;
import com.zuber.organizeit.domain.Note.NotePart;
import com.zuber.organizeit.domain.ReferenceResource.CodeReference;
import com.zuber.organizeit.domain.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.services.exporters.entityImporter.ParseCtx;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;

import java.util.List;

import static com.zuber.organizeit.services.exporters.entityImporter.ParseCtx.RawMetaTag.metaTagsFrom;
import static com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState.CANT_MERGE;
import static com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState.TAG_NOT_KNOWN;

public class NoteCtx implements ParseCtx<Note> {

    final private Note ctxEntity;
    final private int nestedLevel;
    private static final List<String> knownSubContexts = List.of("NotePart", "Flashcard");

    public NoteCtx(Note ctxEntity, int nestedLevel) {
        this.ctxEntity = ctxEntity;
        this.nestedLevel = nestedLevel;
    }

    @Override
    public ParseCtx<Note> mergeCtx(ParseCtx<?> ctx) {

        switch (ctx.getCtxEntity()) {
            case Flashcard f -> getCtxEntity().getFlashcards().add(f);
            case NotePart np -> getCtxEntity().getNoteParts().add(np);
            default -> throw new ParserIllegalState(CANT_MERGE + " Unexpected value: " + ctx.getCtxEntity());

        }

        return this;
    }


    @Override
    public ParseCtx<Note> mergeTag(RawMetaTag tag) {

        switch (tag.key()) {
            case "Note", "N" -> getCtxEntity().setName(tag.value().strip());
            case "Ref" -> getCtxEntity().getReferenceResources().add(
                    SimpleLinkResource.builder()
                            .referenceUrl(tag.value())
                            .caption(tag.value())
                            .build()

            );
            case "CodeRef" -> getCtxEntity().getReferenceResources().add(
              CodeReference.builder().locallySavedURI(tag.value()).build()
            );
            default -> throw new ParserIllegalState(TAG_NOT_KNOWN);
        }

        return this;
    }

    @Override
    public ParseCtx<Note> mergeLine(String line) {
        List<RawMetaTag> rawMetaTags = metaTagsFrom(line);
        rawMetaTags.forEach(this::mergeTag);
        return this;
    }

    @Override
    public Note getCtxEntity() {
        return this.ctxEntity;
    }

    @Override
    public int getLevel() {
        return nestedLevel;
    }

    @Override
    public String defaultSubCtxTag() {
        String notePart = "NotePart";
        return knownSubContexts.stream().filter(sctx -> sctx.equals(notePart)).findAny().orElseThrow(ParserIllegalState::new);
    }

    @Override
    public List<String> allowedSubContextsTags() {
        return knownSubContexts;
    }

    @Override
    public Class<Note> getCtxEntityClass() {
        return Note.class;
    }

}
