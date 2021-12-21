package com.zuber.organizeit.services.exporters.parsers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Optional;

import static com.zuber.organizeit.services.exporters.parsers.ParseContext.getLevel;
import static java.util.Optional.*;

@Component
public class NoteParser {

    private static final String NOTE_SUFFIX = "note";

    // use only for NoteFiles
    public Optional<NoteParseCtx> useWithSameLevelPolicy(Path noteFile) throws IOException, ParseException {
        NoteParseCtx rootCtx = null;

        try {
            if(!noteFile.toString().endsWith(NOTE_SUFFIX)) throw new ParseException(noteFile + " must end with " + NOTE_SUFFIX + "suffix");
            Iterator<String> lines = Files.readAllLines(noteFile).stream()
                    .filter(l -> !l.isBlank())
                    .iterator();
            if(lines.hasNext()) {
                rootCtx = new NoteParseCtx(-1, noteFile);
                ParseContext<?> mergedCtx = rootCtx;
                while (lines.hasNext()) mergedCtx = findMergeCtxAndMerge(lines.next(), mergedCtx);
            }
        } catch (IOException | ParseException ex){
            ex.printStackTrace();
        }

        return ofNullable(rootCtx);
    }

    private ParseContext<?> findMergeCtxAndMerge(String line, @NotNull ParseContext<?> startingCtx) {
        ParseContext<?> mergeCtx = findMergeCtxOnLevel(getLevel(line), startingCtx, null)
                .orElseThrow(() -> new IllegalStateException("Ctx not found. Too many indents maybe."));
        mergeCtx.merge(line);
        return mergeCtx;
    }

    private Optional<ParseContext<?>> findMergeCtxOnLevel(int lineLevel, @NotNull ParseContext<?> ctx, ParseContext<?> prevCtx) {
        Optional<ParseContext<?>> mergeCtx = empty();
        ParseContext<?> next;
        if(ctx != prevCtx) {
            if(ctx.nestedLevel() > lineLevel - 1) {
                next = ctx.parentCtx();
                if(next != prevCtx) mergeCtx = findMergeCtxOnLevel(lineLevel, next, ctx);
            }
            else if(ctx.nestedLevel() < lineLevel - 1) {
                next = ctx.findChildrenHead();
                if(next != prevCtx) mergeCtx = findMergeCtxOnLevel(lineLevel, next, ctx);
            }
            else {
                mergeCtx = of(ctx);
            }
        }
        return mergeCtx;
    }

}
