package com.zuber.organizeit.services.exporters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zuber.organizeit.services.exporters.parser.ParseContext.getLevel;

public class RegexTest {

    @Test
    public void regexTest() {

        String parsableTags = "M|Ref|F|T";

        String test = "M: trene, more M: dsadasdas T: dsada  sdas M: dsadasdas M: dsadasdas M: dsadasdas Ref: dsadasdas M: dsadasdas F: dsa, , ,dasda, s M: dsadasc, xzczxdasT: lassst, dasdas";
        String injectedTags = String.format("(?<hehe>(?:(%s): [\\w\\s,]*)(?=(%s):))|(?:(%s): [\\w\\s,]*)", parsableTags, parsableTags, parsableTags);
        Matcher matcher = Pattern.compile(injectedTags).matcher(test);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println("thehehehe");


    }


    @Test
    public void regexTestWeirdStrs() {

        String parsableTags = "M|Ref|F|T";

        String test = "              M: trene, more M: dsadasdas T: dsada  sdas Ref: dsadasdas M: dsadasdas F: dsa, T: lassst, dasdas           ";
        String injectedTags = String.format("(?<hehe>(?:(%s): [\\w\\s,]*)(?=(%s):))|(?:(%s): [\\w\\s,]*)", parsableTags, parsableTags, parsableTags);
        Matcher matcher = Pattern.compile(injectedTags).matcher(test);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println("thehehehe");


    }


    @Test
    public void regexTestWeirdEmpty() {

        String parsableTags = "M|Ref|F|T";

        String test = "               ";
        String injectedTags = String.format("(?<hehe>(?:(%s): [\\w\\s,]*)(?=(%s):))|(?:(%s): [\\w\\s,]*)", parsableTags, parsableTags, parsableTags);
        Matcher matcher = Pattern.compile(injectedTags).matcher(test);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println("thehehehe");


    }

    @Test
    public void level() {
        String line = "    \t\t  kdjsbhadhksahg askdjbaskjdashj bd \t\t    dsadas";

        Assertions.assertThat(getLevel(line)).isEqualTo(5L);
    }





}
