package com.zuber.organizeit.services.exporters.parser;

import com.zuber.organizeit.Model.Note.Flashcard.Flashcard;
import com.zuber.organizeit.Model.Note.Flashcard.Statistic;
import com.zuber.organizeit.Model.Note.ReferenceResource.CodeReference;
import com.zuber.organizeit.Model.Note.ReferenceResource.SimpleLinkResource;
import com.zuber.organizeit.services.exporters.SourceCodeParser;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static com.zuber.organizeit.Model.Note.Flashcard.Statistic.toDifficulty;
import static java.nio.file.Path.of;

public class FlashcardParseCtx implements ParseContext<Flashcard> {

    private final Flashcard ctxObject;
    private final int nestedLevel;
    private final ParseContext<?> parent;
    private final Stack<ParseContext<?>> children;
    private final Path lookupCodeRefHere;

    public FlashcardParseCtx(int nestedLevel, ParseContext<?> parent, Path lookupCodeRefHere) {
        this.nestedLevel = nestedLevel;
        this.lookupCodeRefHere = lookupCodeRefHere;
        this.ctxObject = new Flashcard();
        this.parent = parent;
        this.children = new Stack<>();
    }

    @Override
    public ParseContext<Flashcard> merge(String line) {
        if(!line.isBlank()){
            List<MetaTagPair> metaTagPairs = metaTagPairsFrom(line);
            if(metaTagPairs.size() != 0) {
                metaTagPairs.forEach(mp -> {
                    switch (mp.metaTag()) {
//                        case "Imp" -> {
//                            // ?
//                        }
                        case "F" -> {
                            ctxObject.setQuestion(mp.value());
                        }
                        case "Lvl" -> {
                            if(ctxObject.getStatistic() == null) ctxObject.setStatistic(new Statistic());
                            ctxObject.getStatistic().setDifficulty(toDifficulty(mp.value()));
                        }
                        case "Ref" -> {
                            if(ctxObject.getReferenceResources() == null) ctxObject.setReferenceResources(new LinkedList<>());
                            ctxObject.getReferenceResources().add(new SimpleLinkResource(mp.value()));
                        }
                        case "CodeRef" -> {
                            Optional<CodeReference> codeReference = SourceCodeParser.parse(of(lookupCodeRefHere.toString(), mp.value()));
                            codeReference.ifPresent(ref -> ctxObject.getReferenceResources().add(ref));
                        }
                    }
                });
            }
            else {
                if(ctxObject.getLongAnswer() == null) ctxObject.setLongAnswer("");
                ctxObject.setLongAnswer(ctxObject().getLongAnswer() + "\n" + line);
            }
        }
        return this;
    }

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
    public Flashcard ctxObject() {
        return ctxObject;
    }

    @Override
    public String parsableTags() {
        return "Imp|Lvl|CodeRef|Ref|F";
    }

}
