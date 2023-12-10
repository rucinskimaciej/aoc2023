package tech.paramount.rucinskimaciej.aoc2023.aoc;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader;

import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.DATA;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.utils.DataReader.DataType.SAMPLE;

public abstract class BaseDayTest<Day extends BaseDay> {

    protected List<String> sample;
    protected List<String> data;
    protected Day day;

    @BeforeEach
    void loadData() {
        sample = new DataReader().read(getClass(), SAMPLE);
        data = new DataReader().read(getClass(), DATA);
        day = getDay();
    }

    protected abstract Day getDay();

}
