package tech.paramount.rucinskimaciej.aoc2023.aoc.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDay;

@Slf4j
public class DayFour implements BaseDay {

    @Override
    public String star1(List<String> input) {
        return "" + input.stream()
                .mapToInt(this::scratchcardScore)
                .sum();
    }

    @Override
    public String star2(List<String> input) {
        return "";
    }

    public int scratchcardScore(String cardInput) {
        return (int) Math.pow(2, winningNumbersInScratchcardCount(cardInput) - 1);
    }

    private int winningNumbersInScratchcardCount(String cardInput) {
        var cardNumber = cardNumber(cardInput);
        var scratchcardWinnersCount = scratchcardWinnersMap.get(cardNumber);
        if (scratchcardWinnersCount == null) {
            scratchcardWinnersCount = 0;
            var winners =
                    Arrays.stream(cardInput.substring(cardInput.indexOf(":") + 1, cardInput.indexOf("|")).trim().split(" "))
                            .distinct().filter(StringUtils::isNumeric).toList();
            var scratches =
                    Arrays.stream(cardInput.substring(cardInput.indexOf("|") + 1).trim().split(" "))
                            .distinct().filter(StringUtils::isNumeric).toList();
            for (String winner : winners) {
                if (scratches.contains(winner)) {
                    scratchcardWinnersCount++;
                }
            }
            scratchcardWinnersMap.put(cardNumber, scratchcardWinnersCount);
        }
        
        return scratchcardWinnersCount;
    }

    private int cardNumber(String cardInput) {
        return Integer.parseInt(cardInput.replaceFirst(".* (\\d+):.*", "$1"));
    }

    final Map<Integer, Integer> scratchcardWinnersMap = new HashMap<>();
    final Map<Integer, Integer> scratchesInstancesMap = new HashMap<>();

    public void cardInstanceCount(List<String> cards, int cardIndex) {
        var cardNumber = cardIndex + 1;
        scratchesInstancesMap.merge(cardNumber, 1, Integer::sum);
        int winners = winningNumbersInScratchcardCount(cards.get(cardIndex));
        for (int i = cardIndex + 1; (i < cards.size() && i < winners + 1); i++) {
            cardInstanceCount(cards, i);
        }
    }
}
