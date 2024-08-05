package nl.tudelft.jpacman.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Game game;
    private Level level;

    @BeforeEach
    void setUp() {
        game = spy(Game.class);
        level = mock(Level.class);
        when(game.getLevel()).thenReturn(level);
    }

    @Test
    void testGameStart1() {
        assertEquals(game.isInProgress(), false);
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(1);
        game.start();
        assertEquals(game.isInProgress(), true);
    }

    @Test
    void testGameStart2() {
        assertEquals(game.isInProgress(), false);
        when(level.isAnyPlayerAlive()).thenReturn(false);
        when(level.remainingPellets()).thenReturn(1);
        game.start();
        assertEquals(game.isInProgress(), false);
    }

    @Test
    void testGameStart3() {
        assertEquals(game.isInProgress(), false);
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(1);
        game.start();
        assertEquals(game.isInProgress(), true);
    }

    @Test
    void testGameStart4() {
        assertEquals(game.isInProgress(), false);
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertEquals(game.isInProgress(), false);
    }

    @Test
    void testGameStart5() {
        assertEquals(game.isInProgress(), false);
        when(level.isAnyPlayerAlive()).thenReturn(false);
        when(level.remainingPellets()).thenReturn(1);
        game.start();
        assertEquals(game.isInProgress(), false);
    }
}