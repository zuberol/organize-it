package com.zuber.organizeit.services.exporters.entityImporter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaTagsTest {

    @Test
    @DisplayName("metaTagsTest")
    void metaTagsPattern() {
        String s = "          \t Kot: file, mon, he  Money: one,two ,three";
        List<ParseCtx.RawMetaTag> actual = ParseCtx.RawMetaTag.metaTagsFrom(s);

        assertThat(actual).containsAll(List.of(
                new ParseCtx.RawMetaTag("Kot", "file"),
                new ParseCtx.RawMetaTag("Kot", "mon"),
                new ParseCtx.RawMetaTag("Kot", "he"),
                new ParseCtx.RawMetaTag("Money", "one"),
                new ParseCtx.RawMetaTag("Money", "two"),
                new ParseCtx.RawMetaTag("Money", "three")
        ));

        s = "          M:";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s)).isEmpty();

        s = "M: T: ";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s)).isEmpty();

        s = "";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s)).isEmpty();

        s = ": dsa";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s)).isEmpty();

        s = "M: ";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s)).isEmpty();

        s = "  CodeRef: CodeRefTest.java";
        assertThat(ParseCtx.RawMetaTag.metaTagsFrom(s).get(0).value()).isEqualTo("CodeRefTest.java");

        System.out.println("dsa");

    }



}
