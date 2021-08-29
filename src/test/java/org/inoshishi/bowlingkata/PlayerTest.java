package org.inoshishi.bowlingkata;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author René Pascal Esemann
 * created on 29.08.2021
 **/
public class PlayerTest {
    private Player player;
    private int score;

    @Mock
    private BowlingInput input;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player(input);
    }

    @Test
    public void gutter() {
        when(input.nextRoll())
                .thenReturn(0);

        score = player.playBowling();

        assertThat(score, equalTo(0));
    }

    @Test
    public void ones() {
        when(input.nextRoll())
                .thenReturn(1);

        score = player.playBowling();

        assertThat(score, equalTo(20));
    }

    @Test
    public void spare() {
        when(input.nextRoll())
                .thenReturn(5, 5, 5, 0);

        score = player.playBowling();

        assertThat(score, equalTo(20));
    }

    @Test
    public void allSpares() {
        when(input.nextRoll())
                .thenReturn(5);

        score = player.playBowling();

        assertThat(score, equalTo(150));
    }

    @Test
    public void strike() {
        when(input.nextRoll())
                .thenReturn(10, 3, 4, 0);

        score = player.playBowling();

        assertThat(score, equalTo(24));
    }

    @Test
    public void perfect() {
        when(input.nextRoll())
                .thenReturn(10);

        score = player.playBowling();

        assertThat(score, equalTo(300));
    }
}
