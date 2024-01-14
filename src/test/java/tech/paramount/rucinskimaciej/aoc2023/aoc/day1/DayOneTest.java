package tech.paramount.rucinskimaciej.aoc2023.aoc.day1;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.DATA;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.SAMPLE;

class DayOneTest {

    private List<String> sample;
    private List<String> data;
    private DayOne day1;

    @BeforeEach
    void loadData() {
        sample = new DataReader().read(getClass(), SAMPLE);
        data = new DataReader().read(getClass(), DATA);
        day1 = new DayOne();
    }

    @Test
    void singleLineNumberTest() {
            assertThat(day1.singleLineDigitMapper(sample.get(0))).isEqualTo(12);
            assertThat(day1.singleLineDigitMapper(sample.get(1))).isEqualTo(38);
            assertThat(day1.singleLineDigitMapper(sample.get(2))).isEqualTo(15);
            assertThat(day1.singleLineDigitMapper(sample.get(3))).isEqualTo(77);
    }

    @Test
    void sumCalibrationValues() {
        assertThat(day1.star1(sample)).isEqualTo("351");
        assertThat(day1.star1(data)).isEqualTo("54708");
    }

    @Test
    void sumCalibrationValuesFromNumberAndNumeral() {
        assertThat(day1.star2(sample)).isEqualTo("423");
    }

    @Test
    void firstNumberInLine() {
        var firstNumbers = new int[]{1, 3, 1, 7, 2, 8, 1, 2, 4, 1, 7};
        for (int i = 0; i < sample.size(); i++) {
            assertThat(day1.findFirstNumber(sample.get(i)))
                    .as("Line " + i)
                    .isEqualTo(String.valueOf(firstNumbers[i]));
        }
    }

    @Test
    void lastNumberInLine() {
        var lastNumbers = new int[]{2, 8, 5, 7, 9, 3, 3, 4, 2, 4, 6};
        for (int i = 0; i < sample.size(); i++) {
            assertThat(day1.findLastNumber(sample.get(i)))
            .as("Line " + i)
            .isEqualTo(String.valueOf(lastNumbers[i]));
        }
    }

    @Test
    void sumCalibrationValuesStar2() {
        assertThat(day1.star2(sample)).isEqualTo("423");
        assertThat(day1.star2(data)).isEqualTo("54087");
    }

}