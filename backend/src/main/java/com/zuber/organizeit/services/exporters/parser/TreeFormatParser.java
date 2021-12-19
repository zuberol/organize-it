package com.zuber.organizeit.services.exporters.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.zuber.organizeit.services.exporters.parser.ParseContext.getLevel;
import static java.util.Optional.*;

public class TreeFormatParser {

    // use only for NoteFiles
    public Optional<NoteParseCtx> useWithSameLevelPolicy(@NotNull Iterable<String> linesIterable) {
        NoteParseCtx rootCtx = null;
        Iterator<String> lines = StreamSupport.stream(linesIterable.spliterator(), false)
                .filter(l -> !l.isBlank())
                .iterator();
        if(lines.hasNext()) {
            rootCtx = new NoteParseCtx(0);
            ParseContext<?> mergedCtx = rootCtx;
            while (lines.hasNext()) mergedCtx = findMergeCtxAndMerge(lines.next(), mergedCtx);
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
