package com.zuber.organizeit.services.exporters.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;

// todo make methods private somehow
public interface ParseContext<E> {

    ParseContext<?> merge(String line);

    // Getters
    E ctxObject();
    String parsableTags();
    ParseContext<?> parentCtx();
    List<ParseContext<?>> childrenCtx();
    int nestedLevel();

    default List<MetaTagPair> metaTagPairsFrom(String line) {
        LinkedList<MetaTagPair> metaTags = new LinkedList<>();
        Matcher metaTagPairMatcher = metaTagsPattern().matcher(line);
        while (metaTagPairMatcher.find()) {
            final String group = metaTagPairMatcher.group();
            final String metaTag = group.substring(0, group.indexOf(':')).replaceAll(":", "").strip();
            final String metaValues = group.substring(metaTag.length()+1);
            metaTags.addAll(
                    stream(metaValues.split(","))
                            .map(String::strip)
                            .map(metaVal -> new MetaTagPair(metaTag, metaVal))
                            .toList()
            );
        }
        return metaTags;
    }

    default ParseContext<?> findChildrenHead() {
        List<ParseContext<?>> childrenCtx = childrenCtx();
        if(childrenCtx == null || childrenCtx.size() == 0) throw new IllegalAccessError("Tryin to get non existing child");
        return childrenCtx.get(childrenCtx.size()-1);
    }

    default Pattern metaTagsPattern() { // todo to nie powinno byc public
        String patternWithInjectedTags = String.format(
                "(?<hehe>(?:(%s): [\\w\\s,#-@/.:]*)(?=(%s):))|(?:(%s): [\\w\\s,#-@./:]*)",
                parsableTags(),
                parsableTags(),
                parsableTags());
        return Pattern.compile(patternWithInjectedTags);
    }

    static private int charsCount(String line) {
        return (int)line.chars().count();
    }

    static int getLevel(String line) {
        Matcher matcher = Pattern.compile("^(?<indents>(?: {2}|\\t)*).*$").matcher(line);
        return matcher.find() ? charsCount(matcher.group("indents").replaceAll("[ ]{2}", "\t")) : 0;
    }


}