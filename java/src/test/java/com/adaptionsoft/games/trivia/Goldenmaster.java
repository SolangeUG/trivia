package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GoldenmasterRunner;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Goldenmaster {

    @Test
    public void testAgainstGoldenmaster() {
        String seedsString = readFileContent("seeds");
        String[] seeds = Arrays
                            .stream(seedsString.split("\\|"))
                            .filter(seed -> !seed.isEmpty())
                            .toArray(String[]::new);

        for (String seed : seeds) {
            String goldemasterOutput = readFileContent("outputForSeed_" + seed);
            List<String> players = Arrays.asList("Bartomeu", "David", "Toni");
            GoldenmasterRunner testRunner = new GoldenmasterRunner(players,
                    "target/test-classes/testOutputForSeed_" + seed, Long.valueOf(seed));

            testRunner.run();
            String gameOutput = readFileContent("testOutputForSeed_" + seed);

            assertEquals(gameOutput, goldemasterOutput);
        }
    }

    private String readFileContent(String filename) {
        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            return String.join("", Files.readAllLines(filePath));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
