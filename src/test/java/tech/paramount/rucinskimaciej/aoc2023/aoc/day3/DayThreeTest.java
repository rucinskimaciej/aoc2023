package tech.paramount.rucinskimaciej.aoc2023.aoc.day3;

import java.awt.Point;
import java.util.List;
import java.util.function.Function;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDayTest;

import static org.assertj.core.api.Assertions.assertThat;

class DayThreeTest extends BaseDayTest<DayThree> {

    @Override
    protected DayThree getDay() {
        return new DayThree();
    }

    @Test
    void partNumbersInLine() {
        Function<Integer, ListAssert<Integer>> sampleMapperAssertion =
                row -> assertThat(day.enginePartsInLine(row, sample).stream().map(EnginePart::id).toList()).as("line " + row);
        sampleMapperAssertion.apply(0).isEqualTo(List.of(467));
        sampleMapperAssertion.apply(1).isEqualTo(List.of());
        sampleMapperAssertion.apply(2).isEqualTo(List.of(35, 633));
        sampleMapperAssertion.apply(3).isEqualTo(List.of());
        sampleMapperAssertion.apply(4).isEqualTo(List.of(617));
        sampleMapperAssertion.apply(5).isEqualTo(List.of());
        sampleMapperAssertion.apply(6).isEqualTo(List.of(592));
        sampleMapperAssertion.apply(7).isEqualTo(List.of(755));
        sampleMapperAssertion.apply(8).isEqualTo(List.of());
        sampleMapperAssertion.apply(9).isEqualTo(List.of(664, 598));
    }

    @Test
    void partNumbersSum() {
        assertThat(day.star1(sample)).isEqualTo("4361");
        assertThat(day.star1(data)).isEqualTo("517021");
    }

    @Test
    void gearRatioTest() {
        Function<Point, AbstractIntegerAssert<?>> gearRatioAssertion = point -> assertThat(day.gearRatio(point, sample)).as("Point: " + point);
        gearRatioAssertion.apply(new Point(3,1)).isEqualTo(16345);
        gearRatioAssertion.apply(new Point(5, 8)).isEqualTo(451490);
        gearRatioAssertion.apply(new Point(3, 4)).isEqualTo(0);
    }

    @Test
    void gearRatioSumTest() {
        assertThat(day.star2(sample)).isEqualTo("467835");
        assertThat(day.star2(data)).isEqualTo("81296995");
    }
}