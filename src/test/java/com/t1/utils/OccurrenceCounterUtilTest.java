package com.t1.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OccurrenceCounterUtilTest {

    public static Stream<Arguments> count() {
        return Stream.of(
                Arguments.of("aaabbc", Map.of('a', 3, 'b', 2, 'c', 1)),
                Arguments.of("aaaaabcccc", Map.of('c', 4, 'a', 5, 'b', 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void count(String text, Map<Character, Integer> expected) {
        LinkedHashMap<Character, Integer> actual = OccurrenceCounterUtil.count(text);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkOrder() {
        String text = "aaaaabcccc%";
        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('a', 5);
        expected.put('c', 4);
        expected.put('b', 1);
        expected.put('%', 1);
        LinkedHashMap<Character, Integer> actual = OccurrenceCounterUtil.count(text);
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(checkIfMapsEqual(expected,actual));
    }

    @Test
    void checkOrderWrong() {
        String text = "aaaaabcccc%";
        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('a', 5);
        expected.put('b', 1);
        expected.put('c', 4);
        expected.put('%', 1);
        LinkedHashMap<Character, Integer> actual = OccurrenceCounterUtil.count(text);
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertFalse(checkIfMapsEqual(expected,actual));
    }

    boolean checkIfMapsEqual(LinkedHashMap<Character, Integer> expected, LinkedHashMap<Character, Integer> actual) {
        Iterator<Map.Entry<Character, Integer>> iterator1 = actual.sequencedEntrySet().iterator();
        Iterator<Map.Entry<Character, Integer>> iterator2 = expected.sequencedEntrySet().iterator();
        while (iterator1.hasNext()) {
            if (!iterator2.next().equals(iterator1.next())) {
                return false;
            }
        }
        return true;
    }
}