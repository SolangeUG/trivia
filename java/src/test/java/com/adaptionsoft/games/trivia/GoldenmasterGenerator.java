package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GoldenmasterRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoldenmasterGenerator {

    public static void main(String[] args) {
        generateGoldenmaster();
    }

    private static void generateGoldenmaster() {
        String seedFileName = "src/test/resources/seeds";
        StringBuilder seedBuilder = new StringBuilder();
        for (int i = 0; i < 1000; ++i) {
            long seed = (long) Math.ceil(1000000 * Math.random());
            seedBuilder.append(seed).append("|");

            List<String> players = Arrays.asList("Bartomeu", "David", "Toni");
            String fileName = "src/test/resources/outputForSeed_" + seed;
            GoldenmasterRunner testRunner = new GoldenmasterRunner(players, fileName, seed);
            testRunner.run();
        }
        writeToFile(seedFileName, seedBuilder);
    }

    private static void writeToFile(String seedFileName, StringBuilder seedBuilder) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(seedFileName);
            fileOutputStream.write(seedBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
