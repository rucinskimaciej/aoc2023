package tech.paramount.rucinskimaciej.aoc2023.aoc.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDay;

public class DaySix implements BaseDay {
    public static List<Pair<Long, Long>> mapValuesStarOne(List<String> input) {
        List<Pair<Long, Long>> output = new ArrayList<>();
        var list = input.stream()
                .map(i -> Arrays.asList(i.split(" ")))
                .map(i -> i.stream().filter(StringUtils::isNumeric).toList())
                .toList();
        for (int col = 0; col < list.get(0).size(); col++) {
            var raceTime = Long.parseLong(list.get(0).get(col));
            var timeToBeat = Long.parseLong(list.get(1).get(col));
            output.add(Pair.of(raceTime, timeToBeat));
        }
        return output;
    }

    public static Pair<Long, Long> mapValuesStarTwo(List<String> input) {
        Function<String, Long> lineMapper = line -> {
            var numbers = line.replaceAll(".*?(\\d+)".repeat(4), "$1$2$3$4");
            return Long.parseLong(numbers);
        };
        return Pair.of(lineMapper.apply(input.get(0)), lineMapper.apply(input.get(1)));
    }

    @Override
    public String star1(List<String> input) {

        List<Long> winnersCount = mapValuesStarOne(input).stream()
                .map(this::findWinnerValuesCount)
                .toList();

        var output = 1;
        for (Long o : winnersCount) {
            output *= o;
        }

        return String.valueOf(output);
    }

    @Override
    public String star2(List<String> input) {
        return String.valueOf(findWinnerValuesCount(mapValuesStarTwo(input)));
    }

    public long findWinnerValuesCount(Pair<Long, Long> input) {
        return findWinnerValuesCount(input.getLeft(), input.getRight());
    }

    public  long findWinnerValuesCount(long raceTime, long recordDistance) {
        holdTimeHistory.clear();

        var minHoldTimeToWin = new HoldTime(raceTime);
        var curValue = midValueOf(raceTime);
        while (holdTimeHistory.add(curValue)) {
            curValue = minHoldTimeToWin(curValue, raceTime, recordDistance, minHoldTimeToWin);
        }

        var result = minHoldTimeToWin.getHoldTime();
        while ((raceTime - --result) * result > recordDistance) {
            minHoldTimeToWin.setHoldTime(result);
        }

        return raceTime - 2 * (minHoldTimeToWin.getHoldTime() - 1) - 1;
    }

    private long minHoldTimeToWin(long holdTime, long raceTime, long recordDistance, HoldTime minHoldTimeToWin) {

        var remainingTime = raceTime - holdTime;
        var distance = holdTime * remainingTime;

        long nextValue;
        if (distance > recordDistance) {
            minHoldTimeToWin.setHoldTime(holdTime);
            nextValue = midValueOf(holdTime);
        } else {
            nextValue = threeSecondOf(holdTime);
        }
        return nextValue;
    }

    private long midValueOf(long raceTime) {
        if (raceTime % 2 == 0) {
            return raceTime / 2;
        }
        return (raceTime / 2) + 1;
    }

    private long threeSecondOf(long holdTime) {
        if (holdTime % 2 == 0) {
            return holdTime * 3 / 2;
        }
        return (holdTime * 3 / 2) + 1;
    }

    private static final Set<Long> holdTimeHistory = new HashSet<>();
}

@Data
@AllArgsConstructor
class HoldTime {
    private long holdTime;

    public void setHoldTime(long holdTime) {
        if (holdTime < this.holdTime) {
            this.holdTime = holdTime;
        }
    }
}
