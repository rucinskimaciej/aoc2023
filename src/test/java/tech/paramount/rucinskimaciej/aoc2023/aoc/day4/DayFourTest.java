package tech.paramount.rucinskimaciej.aoc2023.aoc.day4;

import java.util.function.Function;
import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDayTest;

import static org.assertj.core.api.Assertions.assertThat;

class DayFourTest extends BaseDayTest<DayFour> {

    @Override
    protected DayFour getDay() {
        return new DayFour();
    }

    @Test
    void scratchcardScoreTest() {
        Function<Integer, AbstractIntegerAssert<?>> scratchcardScoreAssertAs =
                cardIndex -> assertThat(day.scratchcardScore(sample.get(cardIndex))).as("Card " + (cardIndex + 1));
        scratchcardScoreAssertAs.apply(0).isEqualTo(8);
        scratchcardScoreAssertAs.apply(1).isEqualTo(2);
        scratchcardScoreAssertAs.apply(2).isEqualTo(2);
        scratchcardScoreAssertAs.apply(3).isEqualTo(1);
        scratchcardScoreAssertAs.apply(4).isEqualTo(0);
        scratchcardScoreAssertAs.apply(5).isEqualTo(0);
    }

    @Test
    void scratchcardWorthSum() {
        assertThat(day.star1(sample)).isEqualTo(13);
        assertThat(day.star1(data)).isEqualTo(21105);
    }

    @Test
    void cardInstanceCountTest() {
        Function<Integer, AbstractIntegerAssert<?>> assertThat = cardIndex -> {
            day.cardInstanceCount(sample, cardIndex);
            return assertThat(day.scratchesInstancesMap.get(cardIndex + 1)).as("Card " + (cardIndex + 1));
        };
        assertThat.apply(0).isEqualTo(1);
        assertThat.apply(1).isEqualTo(2);
        assertThat.apply(2).isEqualTo(4);
//        assertThat.apply(3).isEqualTo(8);
//        assertThat.apply(4).isEqualTo(14);
//        assertThat.apply(5).isEqualTo(1);

    }
}