package com.zuber.organizeit.services;

import java.util.function.Function;

abstract class FunctionalPseudoYMLParser {

    private static final Function<String, Integer> toLeadingTabs = line -> {
        char[] chars = line.toCharArray();
        int count = 0;
        while(count < chars.length && chars[count] == '\t') ++count;
        return count;
    };

//
//
//    public static Task parse(Path ymlPath) {
//        try {
//            Files.readAllLines(ymlPath)
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}
