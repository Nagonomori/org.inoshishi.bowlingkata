package org.inoshishi.bowlingkata;

/**
 * @author René Pascal Esemann
 * created on 29.08.2021
 **/
public class Player {
    private final BowlingInput input;
    private final BowlingGame game;

    public Player(BowlingInput input) {
        this.input = input;
        game = new BowlingGame();
    }

    public int playBowling() {
        for(int i = 0; i < 21; i++) {
            rollBall();
        }
        return game.score();
    }

    private void rollBall() {
        try {
            tryRollBall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryRollBall() throws Exception {
        int roll = input.nextRoll();
        game.roll(roll);
    }
}
