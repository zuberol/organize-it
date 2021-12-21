package com.zuber.organizeit.services.exporters.parsers.ctx;

import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Note.Note;
import com.zuber.organizeit.Model.Note.ReferenceResource.CodeReference;
import com.zuber.organizeit.Model.Note.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.Model.Tag;
import com.zuber.organizeit.services.exporters.parsers.MetaTagPair;
import com.zuber.organizeit.services.exporters.parsers.ParseContext;
import com.zuber.organizeit.services.exporters.parsers.SourceCodeParser;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static java.nio.file.Path.of;

public class NoteParseCtx implements ParseContext<Note> {

    private final Note ctxObject;
    private final int nestedLevel;
    private final ParseContext<?> parent;
    private final Stack<ParseContext<?>> children;
    private final Path noteFile;

    public NoteParseCtx(int nestedLevel, Path noteFile) {
        this.noteFile = noteFile;
        this.ctxObject = new Note();
        this.nestedLevel = nestedLevel;
        this.parent = this;
        this.children = new Stack<>();
    }

    @Override
    public ParseContext<?> merge(String line) {
        ParseContext<?> mergeCtx = this; // todo, nie zwraca faktycznie zmergowanego dla "F"
        if(!line.isBlank()){
            List<MetaTagPair> metaTagPairs = metaTagPairsFrom(line);
            if(metaTagPairs.size() != 0) {
                metaTagPairs.forEach(mp -> {
                    switch (mp.metaTag()) {
                        case "F" -> {
                            ParseContext<Flashcard> merged = new FlashcardParseCtx(nestedLevel + 1, this, noteFile.getParent()).merge(line);
                            this.children.push(merged);
                        }
                        case "T" -> {
                            if (ctxObject.getTags() == null) ctxObject.setTags(new LinkedList<>());
                            ctxObject.getTags().add(new Tag(mp.value()));
                        }
                        case "N" -> {
                            ctxObject.setName(mp.value());
                        }
                        case "Ref" -> {
                            if (ctxObject.getReferenceResources() == null)
                                ctxObject.setReferenceResources(new LinkedList<>());
                            ctxObject.getReferenceResources().add(new SimpleLinkResource(mp.value()));
                        }
                        case "CodeRef" -> {
                            Optional<CodeReference> codeReference = SourceCodeParser.parse(of(noteFile.getParent().toString(), mp.value()));
                            codeReference.ifPresent(ref -> ctxObject.getReferenceResources().add(ref));
                        }
                    }
                });
            }
            else {
                if(ctxObject.getContent() == null) ctxObject.setContent("");
                ctxObject.setContent(ctxObject.getContent() + "\n" + line);
            }
        }
        return mergeCtx;
    }

    // Getters
    @Override
    public ParseContext<?> parentCtx() {
        return parent;
    }

    @Override
    public List<ParseContext<?>> childrenCtx() {
        return children;
    }

    @Override
    public int nestedLevel() {
        return nestedLevel;
    }

    @Override
    public Note ctxObject() {
        return ctxObject;
    }

    @Override
    public String parsableTags() {
        return "N|T|F|CodeRef|Ref";
    }

}
