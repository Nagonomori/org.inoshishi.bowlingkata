package org.inoshishi.bowlingkata;

import org.inoshishi.bowlingkata.exceptions.InvalidGameException;
import org.inoshishi.bowlingkata.exceptions.InvalidRollException;

/**
 * @author René Pascal Esemann
 * created on 27.08.2021
 **/
public class BowlingGame {
    private final int[] rolls;
    private int rollCount;
    private int score;
    private int firstInFrame;

    public BowlingGame() {
        rolls = new int[21];
        rollCount = 0;
        score = 0;
        firstInFrame = 0;
    }

    /**
     * Entering the pins that have fallen in one bowling-roll
     * @param pinsFallen points for the roll
     * @throws InvalidRollException if pins are smaller 0 or larger 10
     */
    public void roll(int pinsFallen) throws Exception {
        checkPinsFallen(pinsFallen);
        rolls[rollCount++] = pinsFallen;
    }

    private void checkPinsFallen(int pinsFallen) throws Exception {
        if (isPinsFallenInvalid(pinsFallen))
            throw new InvalidRollException("Invalid number of pins. Number must be between 0 and 10");
    }

    private boolean isPinsFallenInvalid(int pinsFallen) {
        return pinsFallen < 0 || pinsFallen > 10;
    }

    /**
     * Entering the values for a whole bowling game.
     * @param rolls array with the points per roll in the game
     * @throws InvalidRollException if a single roll is smaller than 0 or larger than 10
     */
    public void rollMany(int[] rolls) throws Exception{
        checkIfGameIsValid(rolls);
        for(int roll : rolls)
            roll(roll);
    }

    private void checkIfGameIsValid(int[] rolls) throws InvalidGameException {
        if (isGameNotValid(rolls))
            throw new InvalidGameException( "A valid game has at least 12 rolls (perfect game) " +
                    "and maximal 21 rolls (last frame is spare)" );
    }

    private boolean isGameNotValid(int[] rolls) {
        return rolls.length > 21 || rolls.length < 12;
    }

    /**
     * Method that calculates the final score of the game
     * @return score of the game
     */
    public int score() {
        for(int frame = 0; frame < 10; frame++){
            incrementScore();
        }

        return score;
    }

    private void incrementScore() {
        if (isStrike())
            addStrikePoints();
        else if (isSpare())
            addSparePoints();
        else
            addPoints();

    }

    private void addPoints() {
        score += twoBallsInFrame();
        firstInFrame += 2;
    }

    private void addSparePoints() {
        score += 10 + nextBallForSpare();
        firstInFrame += 2;
    }

    private void addStrikePoints() {
        score += 10 + nextTwoBallsForStrike();
        firstInFrame++;
    }

    private int nextTwoBallsForStrike() {
        return rolls[firstInFrame + 1] + rolls[firstInFrame + 2];
    }


    private boolean isStrike() {
        return rolls[firstInFrame] == 10;
    }

    private int twoBallsInFrame() {
        return rolls[firstInFrame] + rolls[firstInFrame + 1];
    }

    private int nextBallForSpare() {
        return rolls[firstInFrame + 2];
    }

    private boolean isSpare() {
        return twoBallsInFrame() == 10;
    }
}
