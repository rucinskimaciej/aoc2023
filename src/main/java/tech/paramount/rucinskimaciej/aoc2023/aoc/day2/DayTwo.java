package tech.paramount.rucinskimaciej.aoc2023.aoc.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import lombok.Getter;
import tech.paramount.rucinskimaciej.aoc2023.aoc.BaseDay;

import static tech.paramount.rucinskimaciej.aoc2023.aoc.day2.Cube.BLUE;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day2.Cube.GREEN;
import static tech.paramount.rucinskimaciej.aoc2023.aoc.day2.Cube.RED;

public class DayTwo implements BaseDay {

    @Override
    public int star1(List<String> input) {
        return input.stream()
                .map(Game::new)
                .filter(game -> game.isPossible(Map.of(RED, 12, GREEN, 13, BLUE, 14)))
                .mapToInt(Game::getId)
                .sum();
    }

    @Override
    public int star2(List<String> input) {
        BiFunction<Game, Cube, Integer> maxInDraw =
                (game, cube) -> game.getDraws().stream().map(draw -> draw.getOrDefault(cube, 0)).max(Integer::compareTo).orElseThrow();
        return input.stream()
                .map(Game::new)
                .map(game -> {
                    var red = maxInDraw.apply(game, RED);
                    var green = maxInDraw.apply(game, GREEN);
                    var blue = maxInDraw.apply(game, BLUE);
                    return red * green * blue;
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}

@Getter
class Game {
    private final int id;
    private final List<Map<Cube, Integer>> draws;

    public Game(String gameData) {
        this.id = Integer.parseInt(gameData.substring(gameData.indexOf(" ") + 1, gameData.indexOf(":")));
        this.draws = mapDraws(gameData.substring(gameData.indexOf(":") + 2));
    }

    private List<Map<Cube, Integer>> mapDraws(String gameData) {
        List<Map<Cube, Integer>> output = new ArrayList<>();
        for (String singleDrawString : gameData.split("; ")) {
            Map<Cube, Integer> singleDraw = new HashMap<>();
            for (String cubeDraw : singleDrawString.split(", ")) {
                String[] cubeDrawArr = cubeDraw.split(" ");
                var cube = Cube.parse(cubeDrawArr[1]);
                var cubeCount = Integer.parseInt(cubeDrawArr[0]);
                singleDraw.put(cube, cubeCount);
            }
            if (!singleDraw.isEmpty()) {
                output.add(singleDraw);
            }
        }
        return output;
    }

    public boolean isPossible(Map<Cube, Integer> cubesInSack) {
        for (Map<Cube, Integer> draw : draws) {
            for (Cube cube : draw.keySet()) {
                if (draw.get(cube) > cubesInSack.get(cube)) {
                    return false;
                }
            }
        }
        return true;
    }
}

enum Cube {
    RED, GREEN, BLUE;
    public static Cube parse(String cubeString) {
        for (Cube cube : values()) {
            if (cube.name().equalsIgnoreCase(cubeString)) {
                return cube;
            }
        }
        throw new NoSuchElementException("Could not parse `%s` to Cube enum.".formatted(cubeString));
    }
}
