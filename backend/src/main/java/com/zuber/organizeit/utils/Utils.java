package com.zuber.organizeit.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    // todo dynamic type casting?  notImplementedYet(List.clas)
    public static Runnable notImplementedYet = () -> {
        throw new IllegalArgumentException("not implemented yet");
    };

    public static boolean not(boolean bool){
        return !bool;
    }

    public static String combine(String ...strs) {
        return Arrays.stream(strs).filter(Objects::nonNull).map(String::strip).collect(Collectors.joining("\n"));
    }

    public static String firstNonNullOrNull(String ...strings) {
        return Arrays.stream(strings).filter(Objects::nonNull).findFirst().orElse(null);
    }


}
