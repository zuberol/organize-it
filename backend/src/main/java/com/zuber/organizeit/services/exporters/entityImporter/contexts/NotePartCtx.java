package com.zuber.organizeit.services.exporters.entityImporter.contexts;

import com.zuber.organizeit.domain.Note.NotePart;
import com.zuber.organizeit.domain.ReferenceResource.CodeReference;
import com.zuber.organizeit.domain.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.services.exporters.entityImporter.ParseCtx;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;

import java.util.List;

import static com.zuber.organizeit.services.exporters.entityImporter.ParseCtx.RawMetaTag.metaTagsFrom;
import static com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState.TAG_NOT_KNOWN;
import static com.zuber.organizeit.utils.Utils.combine;
import static java.lang.String.format;

public class NotePartCtx implements ParseCtx<NotePart> {


    private final int nestedLevel;
    final private NotePart ctxEntity;
    private static final String defaultTag = "NotePart";
    private static final List<String> knownSubCtxTags = List.of(defaultTag, "Flashcard", "Fc");

    public NotePartCtx(NotePart ctxEntity, int nestedLevel) {
        this.nestedLevel = nestedLevel;
        this.ctxEntity = ctxEntity;
    }


    @Override
    public NotePart getCtxEntity() {
        return ctxEntity;
    }

    @Override
    public Class<NotePart> getCtxEntityClass() {
        return NotePart.class;
    }

    @Override
    public ParseCtx<NotePart> mergeCtx(ParseCtx<?> ctx) {

        if(ctx.getCtxEntity().getClass().equals(NotePart.class)) {
            getCtxEntity().getSubParts().add( (NotePart) ctx.getCtxEntity() );
        }
        else {
            throw new ParserIllegalState("ctx not known");
        }

        return this;
    }

    @Override
    public ParseCtx<NotePart> mergeTag(RawMetaTag tag) {

        switch (tag.key()) {
            case "NotePart", "N" -> getCtxEntity().setText(tag.value());
            case "Ref" -> getCtxEntity().getReferenceResources().add(
                    SimpleLinkResource.builder().referenceUrl(tag.value()).build()
            );
            case "CodeRef" -> getCtxEntity().getReferenceResources().add(
                   CodeReference.builder().locallySavedURI(tag.value()).build()
            );
            default -> throw new ParserIllegalState(TAG_NOT_KNOWN + format(" tag: %s", tag.key()));
        }

        return this;
    }

    @Override
    public ParseCtx<NotePart> mergeLine(String line) {
        List<RawMetaTag> rawMetaTags = metaTagsFrom(line);
        if(rawMetaTags.isEmpty()) ctxEntity.setText(combine(ctxEntity.getText(), line));
        else rawMetaTags.forEach(this::mergeTag);
        return this;
    }

    @Override
    public String toString() {
        return "NotePartCtx{"
                 + ctxEntity.getText() +
                '}';
    }

    @Override
    public int getLevel() {
        return nestedLevel;
    }

    @Override
    public String defaultSubCtxTag() {
        return defaultTag; // todo glupie to
    }

    @Override
    public List<String> allowedSubContextsTags() {
        return knownSubCtxTags;
    }


}
