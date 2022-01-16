package com.zuber.organizeit.services.exporters.entityImporter.contexts;

import com.zuber.organizeit.domain.Note.Flashcard.Flashcard;
import com.zuber.organizeit.domain.ReferenceResource.CodeReference;
import com.zuber.organizeit.domain.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.services.exporters.entityImporter.ParseCtx;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;

import java.util.List;

import static com.zuber.organizeit.services.exporters.entityImporter.ParseCtx.RawMetaTag.metaTagsFrom;
import static com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState.TAG_NOT_KNOWN;
import static com.zuber.organizeit.utils.Utils.combine;

public class FlashcardCtx implements ParseCtx<Flashcard> {

    final private Flashcard ctxEntity;
    final private int nestedLevel;
    private static final List<String> knownSubContexts = List.of();


    public FlashcardCtx(Flashcard ctxEntity, int nestedLevel) {
        this.ctxEntity = ctxEntity;
        this.nestedLevel = nestedLevel;
    }

    @Override
    public ParseCtx<Flashcard> mergeCtx(ParseCtx<?> ctx) {
        throw new ParserIllegalState("No known subContexts");
    }

    @Override
    public ParseCtx<Flashcard> mergeLine(String line) {
        List<RawMetaTag> rawMetaTags = metaTagsFrom(line);
        if(rawMetaTags.isEmpty()) ctxEntity.setLongAnswer(combine(ctxEntity.getQuestion(), line));
        else rawMetaTags.forEach(this::mergeTag);
        return this;
    }

    @Override
    public ParseCtx<Flashcard> mergeTag(RawMetaTag tag) {

        switch (tag.key()) {
            case "Flashcard", "F" -> getCtxEntity().setQuestion(tag.value());
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
    public Flashcard getCtxEntity() {
        return ctxEntity;
    }

    @Override
    public Class<Flashcard> getCtxEntityClass() {
        return Flashcard.class;
    }

    @Override
    public int getLevel() {
        return nestedLevel;
    }

    @Override
    public String defaultSubCtxTag() {
        return null;
    }

    @Override
    public List<String> allowedSubContextsTags() {
        return knownSubContexts;
    }


}
