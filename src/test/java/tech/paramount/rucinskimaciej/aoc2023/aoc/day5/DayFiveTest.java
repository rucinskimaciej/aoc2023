package tech.paramount.rucinskimaciej.aoc2023.aoc.day5;

import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDayTest;

import static org.assertj.core.api.Assertions.assertThat;

class DayFiveTest extends BaseDayTest<DayFive> {

    @Override
    protected DayFive getDay() {
        return new DayFive();
    }

    @Test
    void soilToLocationTest() {
        day.mapAlmanac(sample);
        assertThat(day.seedToLocation(79)).isEqualTo(82);
        assertThat(day.seedToLocation(14)).isEqualTo(43);
        assertThat(day.seedToLocation(55)).isEqualTo(86);
        assertThat(day.seedToLocation(13)).isEqualTo(35);
    }

    @Test
    void lowestLocationForSeeds() {
        assertThat(day.star1(sample)).isEqualTo("35");
        assertThat(day.star1(data)).isEqualTo("346433842");
    }
}