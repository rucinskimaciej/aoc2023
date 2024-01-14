package tech.paramount.rucinskimaciej.aoc2023.aoc.day6;

import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDayTest;

import static org.assertj.core.api.Assertions.assertThat;

class DaySixTest extends BaseDayTest<DaySix> {

    @Override
    protected DaySix getDay() {
        return new DaySix();
    }

    @Test
    void marginCountTest() {
        assertThat(day.findWinnerValuesCount(DaySix.mapValuesStarOne(sample).get(0))).isEqualTo(4);
        assertThat(day.findWinnerValuesCount(DaySix.mapValuesStarOne(sample).get(1))).isEqualTo(8);
        assertThat(day.findWinnerValuesCount(DaySix.mapValuesStarOne(sample).get(2))).isEqualTo(9);
        assertThat(day.findWinnerValuesCount(DaySix.mapValuesStarTwo(sample))).isEqualTo(71503);
    }

    @Test
    void starTest() {
        assertThat(day.star1(data)).isEqualTo("3316275");
        assertThat(day.star2(data)).isEqualTo("27102791");
    }

}