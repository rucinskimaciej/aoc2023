package tech.paramount.rucinskimaciej.aoc2023.aoc.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;


public class DataReader {

    public List<String> read(Class<?> clazz, DataType dataType) {
        for (String subDir : clazz.getPackageName().split("\\.")) {
            if (subDir.startsWith("day")) {
               return read("aoc/%s/%s".formatted(subDir, dataType.fileName));
            }
        }
        throw new RuntimeException("Well, fuck... we've got nothing!");
    }

    private List<String> read(String resource) {
        Optional<URL> data = Optional.ofNullable(this.getClass().getClassLoader().getResource(resource));
        try {
            String path = data.orElseThrow().getPath();
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("Well, fuck...", e);
        }
    }


    @RequiredArgsConstructor
        public enum DataType {
            SAMPLE("sample"), DATA("data");

            private final String fileName;
        }
}
