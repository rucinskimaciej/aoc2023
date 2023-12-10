package tech.paramount.rucinskimaciej.aoc2023.aoc.day3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDay;

public class DayThree implements BaseDay {
    @Override
    public int star1(List<String> input) {
        var sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += enginePartsInLine(i, input).stream().map(EnginePart::id).mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    @Override
    public int star2(List<String> input) {
        var sum = 0;

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).toCharArray().length; x++) {
                if (input.get(y).charAt(x) == '*') {
                    sum += gearRatio(new Point(x, y), input);
                }
            }
        }
        return sum;
    }

    List<EnginePart> enginePartsInLine(int row, List<String> lines) {
        List<EnginePart> engineParts = new ArrayList<>();
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
                Optional<Point> symbolPoint = findSymbolPosition(lines, row, numStart, numEnd);
                if (symbolPoint.isPresent()) {
                    var partNumber = Integer.parseInt(numberBuilder.toString());
                    var point = symbolPoint.get();
                    engineParts.add(new EnginePart(partNumber, lines.get(point.y).charAt(point.x), point));
                }
                numberBuilder = null;
            }
        }

        return engineParts;
    }

    private Optional<Point> findSymbolPosition(List<String> lines, int row, int numStart, int numEnd) {
        numStart = numStart - 1;
        for (int i = row - 1; i <= row + 1; i ++) {
            for (int j = numStart; j <= numEnd; j++) {
                try {
                    var curChar = lines.get(i).charAt(j);
                    if (!Character.isDigit(curChar) && curChar != '.') {
                        return Optional.of(new Point(j, i));
                    }
                } catch (IndexOutOfBoundsException e) {
                    // continue
                }
            }
        }
        return Optional.empty();
    }

    public int gearRatio(Point point, List<String> lines) {
        List<EnginePart> engineGearPartsAtPoint = new ArrayList<>();
        for (int row = 0; row < lines.size(); row++) {
            enginePartsInLine(row, lines).stream()
                    .filter(part -> part.symbol() == '*')
                    .filter(part -> part.symbolPosition().equals(point))
                    .forEach(engineGearPartsAtPoint::add);
        }

        if (engineGearPartsAtPoint.size() > 1) {
            return engineGearPartsAtPoint.stream().map(EnginePart::id).reduce(1, (a, b) -> a * b);
        }

        return 0;
    }
}

record EnginePart(int id, char symbol, Point symbolPosition) {}
