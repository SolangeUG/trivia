package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GoldenmasterRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoldenmasterGenerator {

    public static void main(String[] args) {
        generate_goldenmaster();
    }

    private static void generate_goldenmaster() {
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

    private static void write_to_file(String seedFileName, StringBuilder seedBuilder) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(seedFileName);
            fileOutputStream.write(seedBuilder.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
