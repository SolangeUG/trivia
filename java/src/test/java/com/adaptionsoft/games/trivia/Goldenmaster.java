package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GoldenmasterRunner;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Goldenmaster {

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
    public void test_against_goldenmaster() {
        String seedsString = read_file_content("seeds");
        String[] seeds = Arrays.stream(seedsString.split("\\|")).filter(seed -> !seed.isEmpty()).toArray(String[]::new);

        for (String seed : seeds) {
            String goldemasterOutput = read_file_content("outputForSeed_" + seed);
            List<String> players = Arrays.asList("Bartomeu", "David", "Toni");
            GoldenmasterRunner testRunner = new GoldenmasterRunner(players,
                    "target/test-classes/testOutputForSeed_" + seed, Long.valueOf(seed));

            testRunner.run();
            String gameOutput = read_file_content("testOutputForSeed_" + seed);

            assertEquals(gameOutput, goldemasterOutput);
        }
    }
}
