package tech.paramount.rucinskimaciej.aoc2023.aoc.day2;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.Day;
import tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.DATA;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.SAMPLE;

class DayTwoTest {

    private List<String> sample;
    private List<String> data;
    private Day day;

    @BeforeEach
    void loadData() {
        sample = new DataReader().read(getClass(), SAMPLE);
        data = new DataReader().read(getClass(), DATA);
        day = new DayTwo();
    }

    @Test
    void sumPossibleGames() {
        assertThat(day.star1(sample)).isEqualTo(8);
        assertThat(day.star1(data)).isEqualTo(2085);
    }

    @Test
    void fewestCubesInGame() {
        assertThat(day.star2(sample)).isEqualTo(2286);
        assertThat(day.star2(data)).isEqualTo(79315);
    }

}