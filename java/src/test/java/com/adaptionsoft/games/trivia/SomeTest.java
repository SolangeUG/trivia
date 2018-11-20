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
    public void generate_goldenmaster() {
        String seedFileName = "src/test/resources/seeds";
        StringBuilder seedBuilder = new StringBuilder();
        for (int i = 0; i < 1000; ++i) {
            long seed = (long) Math.ceil(1000000 * Math.random());
            String fileName = "src/test/resources/outputForSeed_" + seed;
            seedBuilder.append(seed).append("|");
            List<String> players = Arrays.asList("Bartomeu", "David", "Toni");
            GoldenmasterRunner testRunner = new GoldenmasterRunner(players, fileName, seed);
            testRunner.run();
        }
        write_to_file(seedFileName, seedBuilder);
    }

    private void write_to_file(String seedFileName, StringBuilder seedBuilder) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(seedFileName);
            fileOutputStream.write(seedBuilder.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_against_goldenmaster() {
        String goldemasterOutput = read_file_content("outputForSeed_1");
        List<String> players = Arrays.asList("Bartomeu", "David", "Toni");
        GoldenmasterRunner testRunner = new GoldenmasterRunner(players, "target/test-classes/testOutputForSeed_1", 1);

        testRunner.run();
        String gameOutput = read_file_content("testOutputForSeed_1");

        assertEquals(gameOutput, goldemasterOutput);
    }
}
