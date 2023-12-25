package tech.paramount.rucinskimaciej.aoc2023.aoc.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDay;

import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.FERTILIZER_TO_WATER;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.HUMIDITY_TO_LOCATION;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.LIGHT_TO_TEMPERATURE;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.SEED_TO_SOIL;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.SOIL_TO_FERTILIZER;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.TEMPERATURE_TO_HUMIDITY;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day5.DayFive.ConversionType.WATER_TO_LIGHT;

public class DayFive implements BaseDay {
    private AlmanacData almanac;

    @Override
    public String star1(List<String> input) {
        mapAlmanac(input);
        return "" + almanac.seeds.stream()
                .map(this::seedToLocation)
                .mapToLong(Long::longValue)
                .min().orElseThrow();
    }

    @Override
    public String star2(List<String> input) {
        return null;
    }

    public long seedToLocation(long seed) {
        var soil = findDestination(SEED_TO_SOIL, seed);
        var fertilizer = findDestination(SOIL_TO_FERTILIZER, soil);
        var water = findDestination(FERTILIZER_TO_WATER, fertilizer);
        var light = findDestination(WATER_TO_LIGHT, water);
        var temperature = findDestination(LIGHT_TO_TEMPERATURE, light);
        var humidity = findDestination(TEMPERATURE_TO_HUMIDITY, temperature);
        return findDestination(HUMIDITY_TO_LOCATION, humidity);
    }

    public void mapAlmanac(List<String> input) {
        var seedsLine = input.get(0);
        var seeds = Arrays.stream(seedsLine.substring(seedsLine.indexOf(" ") + 1).split(" "))
                .map(Long::parseLong)
                .toList();
        almanac = new AlmanacData(seeds, parseAlmanacData(input.subList(2, input.size())));
    }

    private List<DataLine> parseAlmanacData(List<String> dataLines) {
        List<DataLine> almanacData = new ArrayList<>();
        ConversionType conversionType = null;
        for (String dataLine : dataLines) {
            if (dataLine.endsWith(":")) {
                conversionType = ConversionType.fromType(dataLine.substring(0, dataLine.indexOf(" ")));
            } else if (!dataLine.isBlank()) {
                var lineData = Arrays.stream(dataLine.split(" ")).map(Long::parseLong).toList();
                almanacData.add(new DataLine(lineData.get(0), lineData.get(1), lineData.get(2), conversionType));
            }
        }
        return almanacData;
    }

    private long findDestination(ConversionType conversionType, long source) {
        return almanac.dataLines.stream()
                .filter(line -> line.conversionType == conversionType)
                .filter(line -> source >= line.source && source <= (line.source + line.range))
                .map(line -> line.destination + source - line.source)
                .findFirst()
                .orElse(source);
    }

    @RequiredArgsConstructor
    @Getter
    enum ConversionType {
        SEED_TO_SOIL("seed-to-soil", "seed", "soil"),
        SOIL_TO_FERTILIZER("soil-to-fertilizer", "soil", "fertilizer"),
        FERTILIZER_TO_WATER("fertilizer-to-water", "fertilizer", "water"),
        WATER_TO_LIGHT("water-to-light", "water", "light"),
        LIGHT_TO_TEMPERATURE("light-to-temperature", "light", "temperature"),
        TEMPERATURE_TO_HUMIDITY("temperature-to-humidity", "temperature", "humidity"),
        HUMIDITY_TO_LOCATION("humidity-to-location", "humidity", "location");

        private final String typeName;
        private final String source;
        private final String destination;

        static ConversionType fromType(String type) {
            return Arrays.stream(ConversionType.values())
                    .filter(conversionType -> conversionType.typeName.equals(type))
                    .findFirst().orElseThrow();
        }
    }

    @RequiredArgsConstructor
    @Getter
    enum DataType {
        DESTINATION, SOURCE, RANGE
    }

    private record DataLine(long destination, long source, long range, ConversionType conversionType) {}

    private record AlmanacData(List<Long> seeds, List<DayFive.DataLine> dataLines) {}
}
