package org.inoshishi.bowlingkata;

import org.inoshishi.bowlingkata.exceptions.InvalidGameException;
import org.inoshishi.bowlingkata.exceptions.InvalidRollException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author René Pascal Esemann
 * created on 27.08.2021
 **/
public class BowlingGameTest {
    private BowlingGame game;

    private int[] getOneSpare() {
        int[] oneSpare = new int[20];
        oneSpare[0] = 5;
        oneSpare[1] = 5;
        oneSpare[2] = 5;
        return oneSpare;
    }

    private int[] getOnes() {
        int[] ones = new int[20];
        Arrays.fill(ones, 1);
        return ones;
    }

    private int[] getFives() {
        int[] fives = new int[21];
        Arrays.fill(fives, 5);
        return fives;
    }

    private int[] getStrike() {
        int[] strike = new int[20];
        strike[0] = 10;
        strike[1] = 3;
        strike[2] = 4;
        return strike;
    }

    private int[] getPerfectGame() {
        int[] perfectGame = new int[12];
        Arrays.fill(perfectGame, 10);
        return perfectGame;
    }

    @DataProvider( name = "TestData")
    public Object[][] testData () {
        return new Object[][] {
                {"gutterGame", new int[20], 0},
                {"throwOnlyOnes", getOnes(), 20},
                {"rollASpare", getOneSpare(), 20},
                {"allSpares", getFives(), 150},
                {"rollAStrike", getStrike(), 24},
                {"perfectGame", getPerfectGame(), 300}
        };
    }

    @DataProvider ( name = "exceptionRolls")
    public Object[][] exceptionRolls() {
        return new Object[][] {
                {-1}, {11}
        };
    }

    @DataProvider ( name = "invalidGameRolls")
    public Object[][] invalidGameRolls () {
        return new Object[][] {
                {30}, {11}
        };
    }

    @BeforeMethod
    public void setUp () {
        game = new BowlingGame();
    }

    @Test (dataProvider = "TestData")
    public void playGame (String testCase, int[] rolls, int expectedScore) throws Exception{
        game.rollMany(rolls);

        assertThat( testCase, game.score(), equalTo(expectedScore));
    }

    @Test ( dataProvider = "exceptionRolls", expectedExceptions = InvalidRollException.class)
    public void rollInvalid ( int invalidRoll ) throws Exception{
        game.roll(invalidRoll);
    }

    @Test ( expectedExceptions = InvalidGameException.class, dataProvider = "invalidGameRolls")
    public void invalidGame (int gameLength) throws Exception {
        int[] invalidGame = new int[gameLength];
        game.rollMany(invalidGame);
    }
}
