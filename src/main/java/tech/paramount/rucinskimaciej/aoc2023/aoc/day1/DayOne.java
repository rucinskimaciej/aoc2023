package tech.paramount.rucinskimaciej.aoc2023.aoc.day1;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DayOne {
    public String star1(List<String> sample) {
        return "" + sample.stream().map(this::singleLineDigitMapper).mapToInt(Integer::intValue).sum();
    }

    public int singleLineDigitMapper(String line) {
        var first = "";
        var last = "";
        for (String s : line.split("")) {
            if (!Character.isAlphabetic(s.charAt(0))) {
                if (first.isEmpty()) {
                    first = s;
                } else {
                    last = s;
                }
            }
        }
        if (last.isEmpty()) {
            last = first;
        }
        try {
            return Integer.parseInt(first + last);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public String star2(List<String> sample) {
        return "" + sample.stream()
                .map(line -> findFirstNumber(line) + findLastNumber(line))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public String findFirstNumber(String line) {
        return processLine(line, true);
    }

    public String findLastNumber(String line) {
        return processLine(line, false);
    }

    private String processLine(String line, boolean startFromBeginning) {
        int increment = startFromBeginning ? 1 : -1;
        int startIndex = startFromBeginning ? 0 : line.length() - 1;
        int endIndex = startFromBeginning ? line.length() : -1;

        for (int i = startIndex; i != endIndex; i += increment) {
            for (String key : numberMap.keySet()) {
                if (Character.isDigit(line.charAt(i))) {
                    return line.substring(i, i + 1);
                } else {
                    int keyLength = key.length();
                    int startSubstring = i - (startFromBeginning ? 0 : keyLength - 1);
                    int endSubstring = startSubstring + keyLength;
                    if (startSubstring >= 0 && endSubstring <= line.length() && line.substring(startSubstring, endSubstring).equals(key)) {
                        return String.valueOf(numberMap.get(key));
                    }
                }
            }
        }
        throw new NoSuchElementException("Could not find a number in line: " + line);
    }

    private static final Map<String, Integer> numberMap = Map.of(
            "zero", 0,
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
            );
}
