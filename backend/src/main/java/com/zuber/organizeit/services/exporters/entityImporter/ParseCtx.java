package com.zuber.organizeit.services.exporters.entityImporter;

import com.zuber.organizeit.domain.BaseEntity;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zuber.organizeit.utils.Utils.not;
import static java.util.Arrays.stream;

public interface ParseCtx<E extends BaseEntity<Long>> {

    String defaultSubCtxTag();
    List<String> allowedSubContextsTags();
    E getCtxEntity();

    Class<E> getCtxEntityClass();
    int getLevel();
    int ONE_LEVEL = 2;

    ParseCtx<E> mergeCtx(ParseCtx<?> ctx);
    ParseCtx<E> mergeTag(RawMetaTag tag);
    ParseCtx<E> mergeLine(String line);

    static int toLevel(String line) {
        Matcher matcher = Pattern.compile("^(?<level>\s*)").matcher(line);
        String extractLevel = matcher.find() ? matcher.group("level") : "";
        int sum = extractLevel.chars().map(ch ->
                switch (ch) {
                    case '\t':
                        yield 2;

                    case ' ':
                        yield 1;
                    default:
                        throw new IllegalArgumentException("Unexpected value: " + ch + " passed to getLevel()");
                }
        ).sum();
        return sum % 2 == 1 ? sum -1 : sum;
    }

    record RawMetaTag(String key, String value){

        public static List<RawMetaTag> metaTagsFrom(String line) {
            LinkedList<String> keyValues = toKeyValues(line, line);
            LinkedList<RawMetaTag> metaTags = new LinkedList<>();
            Pattern keyVals = Pattern.compile("^(?<key>\\w+): (?<vals>.*)");
            keyValues.forEach(kv -> {
                Matcher matcher = keyVals.matcher(kv);
                while (matcher.find()) {
                    String key = matcher.group("key");
                    stream(matcher.group("vals").split(",")).map(String::strip)
                            .forEach(val -> metaTags.add(new RawMetaTag(key, val)));

                }
            });
            return metaTags.stream().filter(m -> not(m.key.isBlank()) && not(m.value.isBlank())).toList();
        }
        private static LinkedList<String> toKeyValues(String line, String s) {
            var matchTag = Pattern.compile("(?<tag>\\w+): ").matcher(s);

            LinkedList<Integer> startIndexes = new LinkedList<>();
            while (matchTag.find()) startIndexes.add(matchTag.start());

            LinkedList<String> metaTagKeyValues = new LinkedList<>();

            ListIterator<Integer> starts = startIndexes.listIterator();
            while (starts.hasNext()) {
                int keyValueStart = starts.next();
                int keyValueEnd;
                if(starts.hasNext()) {
                    keyValueEnd = starts.next();
                    starts.previous();

                } else keyValueEnd = line.length();
                metaTagKeyValues.add(line.substring(keyValueStart, keyValueEnd));
            }
            return metaTagKeyValues;
        }

    }



}
