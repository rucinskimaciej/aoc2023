package tech.paramount.rucinskimaciej.aoc2023.aoc.day3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tech.paramount.rucinskimaciej.aoc2023.aoc.Day;

public class DayThree implements Day {
    @Override
    public int star1(List<String> input) {
        var sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += enginePartsNumbersInLine(i, input).stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    @Override
    public int star2(List<String> input) {
        return 0;
    }

    List<Integer> enginePartsNumbersInLine(int row, List<String> lines) {
        List<Integer> partNumbers = new ArrayList<>();
        var curLine = lines.get(row);
        StringBuilder numberBuilder = null;
        var numStart = -1;
        var numEnd = -1;
        for (int col = 0; col < curLine.toCharArray().length; col++) {
            var curChar = curLine.charAt(col);
            var isDigit = Character.isDigit(curLine.charAt(col));
            if (isDigit) {
                if (numberBuilder == null) {
                    numberBuilder = new StringBuilder();
                    numStart = col;
                }
                numberBuilder.append(curChar);
            }

            if ((!isDigit || col + 1 == curLine.toCharArray().length) && numberBuilder != null) {
                numEnd = col;
                Optional<Point> symbolPoint = findSymbol(lines, row, numStart, numEnd);
                if (symbolPoint.isPresent()) {
                    partNumbers.add(Integer.parseInt(numberBuilder.toString()));
                }
                numberBuilder = null;
            }
        }

//        System.out.println("Row %s count: %s >>>>>> ".formatted(row, partNumbers.size()) + partNumbers);
        return new ArrayList<>(partNumbers);
    }

    private Optional<Point> findSymbol(List<String> lines, int row, int numStart, int numEnd) {
        numStart = numStart - 1;
        for (int i = row - 1; i <= row + 1; i ++) {
            for (int j = numStart; j <= numEnd; j++) {
                try {
                    var curChar = lines.get(i).charAt(j);
                    if (!Character.isDigit(curChar) && curChar != '.') {
                        return Optional.of(new Point(i, j));
                    }
                } catch (IndexOutOfBoundsException e) {
                    // continue
                }
            }
        }
        return Optional.empty();
    }

}
