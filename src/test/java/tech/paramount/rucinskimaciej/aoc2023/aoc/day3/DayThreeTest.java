package tech.paramount.rucinskimaciej.aoc2023.aoc.day3;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDayTest;
import tech.paramount.rucinskimaciej.aoc2023.aoc.Day;

import static org.assertj.core.api.Assertions.assertThat;

class DayThreeTest extends BaseDayTest {

    @Override
    protected Day getDay() {
        return new DayThree();
    }

    @Test
    void partNumbersInLine() {
        var day = (DayThree) this.day;
        assertThat(day.enginePartsNumbersInLine(0, sample)).as("line 0").isEqualTo(List.of(467));
        assertThat(day.enginePartsNumbersInLine(1, sample)).as("line 1").isEqualTo(List.of());
        assertThat(day.enginePartsNumbersInLine(2, sample)).as("line 2").isEqualTo(List.of(35, 633));
        assertThat(day.enginePartsNumbersInLine(3, sample)).as("line 3").isEqualTo(List.of());
        assertThat(day.enginePartsNumbersInLine(4, sample)).as("line 4").isEqualTo(List.of(617));
        assertThat(day.enginePartsNumbersInLine(5, sample)).as("line 5").isEqualTo(List.of());
        assertThat(day.enginePartsNumbersInLine(6, sample)).as("line 6").isEqualTo(List.of(592));
        assertThat(day.enginePartsNumbersInLine(7, sample)).as("line 7").isEqualTo(List.of(755));
        assertThat(day.enginePartsNumbersInLine(8, sample)).as("line 8").isEqualTo(List.of());
        assertThat(day.enginePartsNumbersInLine(9, sample)).as("line 9").isEqualTo(List.of(664, 598));
    }

    @Test
    void partNumbersSum() {
        assertThat(day.star1(sample)).isEqualTo(4361);
        assertThat(day.star1(data)).isEqualTo(517021);
    }
}