package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.ParserIllegalState;
import com.zuber.organizeit.services.exporters.entityImporter.exceptions.UnexpectedClassException;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Pattern;

import static com.zuber.organizeit.services.exporters.entityImporter.ParseCtx.ONE_LEVEL;
import static com.zuber.organizeit.services.exporters.entityImporter.ParseCtx.toLevel;
import static com.zuber.organizeit.utils.Utils.firstNonNullOrNull;
import static com.zuber.organizeit.utils.Utils.not;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.regex.Pattern.compile;

public class YmlTreeParser<E extends BaseEntity<Long>> implements StructuralParser<E> {

    final Stack<ParseCtx<?>> ctxStack;
    final CtxCreator ctxCreator;
    private final Pattern yamlPattern = compile("^(?<level>\\s*)((?<ctxTag>\\w+): )?(?<ctxTagValue>.+)\\s*$");
    private final Class<E> expectedClass;
    private int firstCtxLvl;

    public YmlTreeParser(CtxCreator ctxCreator, Class<E> expectedClass) {
        this.ctxCreator = ctxCreator;
        this.ctxStack = new Stack<>();
        this.expectedClass = expectedClass;
    }



    @Override
    public Optional<E> run(List<String> lines) {
        lines = prepare(lines);
        Optional<E> entity;
        parseStructure(lines.listIterator());
        if(ctxStack.size() != 1) throw new ParserIllegalState("Should be only one item on stack right now");
        ParseCtx<?> entityCtx = ctxStack.pop();
        try {
            entity = of(expectedClass.cast(entityCtx.getCtxEntity()));
        } catch (ClassCastException ex) {
            throw new UnexpectedClassException("Class -> " + entityCtx.getCtxEntityClass());
        }

        return entity;
    }

    public void parseStructure(ListIterator<String> lines) {
        boolean seenCtx = false;
        while (lines.hasNext() && not(seenCtx)) {
            String line = lines.next();
            var matchYml = yamlPattern.matcher(line);
            if(matchYml.find()){
                seenCtx = true;
                if(matchYml.group("ctxTag") == null) throw new ParserIllegalState("Ctx tag is missing");
                var firstCtx = ctxCreator
                        .getLevelAwareNewCtx(matchYml.group("ctxTag"), toLevel(matchYml.group("level")))
                        .mergeLine(line);
                firstCtxLvl = firstCtx.getLevel();
                ctxStack.push(firstCtx);
            }
        }

        while (lines.hasNext()) {
            var line = lines.next();
            var matchYml = yamlPattern.matcher(line);
            if(matchYml.find()){
                var ctxTag = matchYml.group("ctxTag");
                var currentLineLevel = toLevel(matchYml.group("level"));
                if(currentLineLevel > firstCtxLvl) {

                    Optional<String> foundTagInCtx = ofNullable(firstNonNullOrNull(ctxTag, ctxStack.peek().defaultSubCtxTag()))
                            .flatMap(tag -> ctxStack.peek().allowedSubContextsTags().stream()
                                    .filter(allowedTag -> allowedTag.equals(tag))
                                    .findFirst());
                    boolean shouldCreateCtx = foundTagInCtx.isPresent();
                    if(shouldCreateCtx){
                        zipStack(currentLineLevel - ONE_LEVEL);
                        foundTagInCtx
                                .ifPresent(t -> ctxStack.push(ctxCreator.getLevelAwareNewCtx(t, currentLineLevel)).mergeLine(line));
                    } else {
                        zipStack(currentLineLevel - ONE_LEVEL);
                        ctxStack.peek().mergeLine(line);
                    }


                } else throw new ParserIllegalState("First ctx must be the lowest.");
            }
        }
        zipStack(firstCtxLvl);
    }


    private void zipStack(int zipLevel) { // wlacznie
        if(ctxStack.empty()) throw new ParserIllegalState("reducing empty stack");
        else {
            var siblings = new Stack<ParseCtx<?>>();
            siblings.push(ctxStack.pop());
            while(siblings.peek().getLevel() != zipLevel) {
                var siblingsLvl = siblings.peek().getLevel();
                if(!ctxStack.empty()) {
                    var next = ctxStack.pop();
                    if(next.getLevel() == siblingsLvl) siblings.push(next);
                    else if(next.getLevel() + ONE_LEVEL == siblingsLvl) {
                        while (!siblings.empty()) next.mergeCtx(siblings.pop());
                        siblings.push(next);
                    }
                    else throw new ParserIllegalState("Levels mismatch. Bad format");
                }
                else throw new ParserIllegalState("cant reduce");
            }
            while(!ctxStack.empty() && siblings.peek().getLevel() == zipLevel) siblings.push(ctxStack.pop());
//            siblings.forEach(System.out::println);
//            siblings.forEach(ctxStack::push);
            while (!siblings.empty()) ctxStack.push(siblings.pop());
        }
    }

    private List<String> prepare(List<String> lines) {
        lines = lines.stream().filter(s -> !s.isBlank()).toList();
        return lines;
    }
}
