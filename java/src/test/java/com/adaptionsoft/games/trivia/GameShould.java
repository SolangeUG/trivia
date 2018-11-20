package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GameShould {

    private PrintStream stdOutput = System.out;

    @Test
    public void add_a_player() {
        String expected = "David was added";
        expected += "They are player number 1";

        OutputStream byteArrayOutputstream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputstream));

        Game game = new Game();
        game.add("David");

        assertEquals(byteArrayOutputstream.toString().replace(System.lineSeparator(), ""),
                expected);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(stdOutput);
    }
}
