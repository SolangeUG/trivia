package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GoldmasterRunner;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SomeTest {

    public String read_file_content(String filename) {

        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            return Files.readAllLines(filePath)
                                    .stream()
                                    .reduce("", (s, s2) -> s += s2);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Test
    public void generate_goldmaster() {
        long seed = 10000;
        String fileName = "outputForSeed_" + seed;
        List<String> players = Arrays.asList("Bartolomeu", "David", "Toni");
        GoldmasterRunner testRunner = new GoldmasterRunner(players, fileName, seed);
        testRunner.run();
    }

    @Test
    public void test_against_goldmaster() {
        String goldemasterOutput = read_file_content("outputForSeed_1");
        List<String> players = Arrays.asList("Bartolomeu", "David", "Toni");
        GoldmasterRunner testRunner = new GoldmasterRunner(players, "target/test-classes/testOutputForSeed_1", 1);

        testRunner.run();
        String gameOutput = read_file_content("testOutputForSeed_1");

        assertEquals(gameOutput, goldemasterOutput);
    }
}
