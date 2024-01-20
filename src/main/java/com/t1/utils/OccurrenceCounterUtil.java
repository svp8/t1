package com.t1.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.*;
import java.util.stream.Collectors;

public class OccurrenceCounterUtil {
    public static LinkedHashMap<Character, Integer> count(String text) {
        Map<Character, Integer> occurs = new HashMap<>();
        for (char c : text.toCharArray()) {
            occurs.computeIfPresent(c, (key, val) -> val + 1);
            occurs.putIfAbsent(c, 1);
        }
        return occurs.entrySet()
                .stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
