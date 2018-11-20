package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

public class GoldenmasterRunner {

    private PrintStream standardOutput;
    private List<String> players;
    private long seed;

    public GoldenmasterRunner(List<String> players, String fileName, long seed) {
        this.players = players;
        this.seed = seed;
        redirectStandardOutput(fileName);
    }

    private void redirectStandardOutput(String fileName) {
        standardOutput = System.out;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            System.setOut(new PrintStream(fileOutputStream));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Game aGame = new Game();

        for (String player: players) {
            aGame.add(player);
        }

        Random rand = new Random(seed);
        boolean notAWinner;

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (notAWinner);

        System.setOut(standardOutput);
    }

}
