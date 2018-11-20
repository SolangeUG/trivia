package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

public class GoldmasterRunner {

    private PrintStream stdOutput;
    private List<String> players;
    private String fileName;
    private long seed;

    public GoldmasterRunner(List<String> players, String fileName, long seed) {
        this.players = players;
        this.fileName = fileName;
        this.seed = seed;
        redirectStdOut();
    }

    private void redirectStdOut() {
        stdOutput = System.out;
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

        System.setOut(stdOutput);
    }

}
