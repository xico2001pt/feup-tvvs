package nl.tudelft.jpacman.npc.ghost;

import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.PacManSprites;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the various methods provided by the {@link Navigation} class.
 *
 * @author Jeroen Roosen
 *
 */
class NavigationTest {

    /**
     * Map parser used to construct boards.
     */
    private MapParser parser;

    /**
     * Set up the map parser.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        parser = new MapParser(new LevelFactory(sprites, new GhostFactory(
            sprites)), new BoardFactory(sprites));
    }

    /**
     * Verifies that the path to the same square is empty.
     */
    @Test
    void testShortestPathEmpty() {
        Board b = parser.parseMap(Lists.newArrayList(" ")).getBoard();
        Square s1 = b.squareAt(0, 0);
        Square s2 = b.squareAt(0, 0);
        List<Direction> path = Navigation
            .shortestPath(s1, s2, mock(Unit.class));
        assertThat(path).isEmpty();
    }

    /**
     * Verifies that if no path exists, the result is <code>null</code>.
     */
    @Test
    void testNoShortestPath() {
        Board b = parser
            .parseMap(Lists.newArrayList("#####", "# # #", "#####"))
            .getBoard();
        Square s1 = b.squareAt(1, 1);
        Square s2 = b.squareAt(3, 1);
        List<Direction> path = Navigation
            .shortestPath(s1, s2, mock(Unit.class));
        assertThat(path).isEmpty();
    }

    /**
     * Verifies that having no traveller ignores terrain.
     */
    @Test
    void testNoTraveller() {
        Board b = parser
            .parseMap(Lists.newArrayList("#####", "# # #", "#####"))
            .getBoard();
        Square s1 = b.squareAt(1, 1);
        Square s2 = b.squareAt(3, 1);
        List<Direction> path = Navigation.shortestPath(s1, s2, null);
        assertThat(path).containsExactly(Direction.EAST, Direction.EAST);
    }

    /**
     * Tests if the algorithm can find a path in a straight line.
     */
    @Test
    void testSimplePath() {
        Board b = parser.parseMap(Lists.newArrayList("####", "#  #", "####"))
            .getBoard();
        Square s1 = b.squareAt(1, 1);
        Square s2 = b.squareAt(2, 1);
        List<Direction> path = Navigation
            .shortestPath(s1, s2, mock(Unit.class));
        assertThat(path).containsExactly(Direction.EAST);
    }

    /**
     * Verifies that the algorithm can find a path when it has to take corners.
     */
    @Test
    void testCornerPath() {
        Board b = parser.parseMap(
            Lists.newArrayList("####", "#  #", "## #", "####")).getBoard();
        Square s1 = b.squareAt(1, 1);
        Square s2 = b.squareAt(2, 2);
        List<Direction> path = Navigation
            .shortestPath(s1, s2, mock(Unit.class));
        assertThat(path).containsExactly(Direction.EAST, Direction.SOUTH);
    }

    /**
     * Verifies that the nearest object is detected.
     */
    @Test
    void testNearestUnit() {
        Board b = parser
            .parseMap(Lists.newArrayList("#####", "# ..#", "#####"))
            .getBoard();
        Square s1 = b.squareAt(1, 1);
        Square s2 = b.squareAt(2, 1);
        Square result = Navigation.findNearest(Pellet.class, s1).getSquare();
        assertThat(result).isEqualTo(s2);
    }

    /**
     * Verifies that there is no such location if there is no nearest object.
     */
    @Test
    void testNoNearestUnit() {
        Board b = parser.parseMap(Lists.newArrayList(" ")).getBoard();
        Square s1 = b.squareAt(0, 0);
        Unit unit = Navigation.findNearest(Pellet.class, s1);
        assertThat(unit).isNull();
    }

    /**
     * Verifies that there is ghost on the default board
     * next to cell [1, 1].
     *
     * @throws IOException if board reading fails.
     */
    @Test
    void testFullSizedLevel() throws IOException {
        try (InputStream i = getClass().getResourceAsStream("/board.txt")) {
            Board b = parser.parseMap(i).getBoard();
            Square s1 = b.squareAt(1, 1);
            Unit unit = Navigation.findNearest(Ghost.class, s1);
            assertThat(unit).isNotNull();
        }
    }

    @Test
    void makeGhostSquareTest1() {
        /*
         * 1. ghosts = null (C1) and ghost = null (C4) and square = null (C7)
        */
        PacManSprites sprites = mock(PacManSprites.class);
        parser = new MapParser(new LevelFactory(sprites, new GhostFactory(sprites)), new BoardFactory(sprites));
        LevelFactory levelCreator = new LevelFactory(sprites, new GhostFactory(sprites));
        List<Ghost> ghosts = new ArrayList<>(Arrays.asList(levelCreator.createGhost(), levelCreator.createGhost(), levelCreator.createGhost()));

        assertDoesNotThrow(() -> parser.makeGhostSquare(ghosts, levelCreator.createGhost()));
    }
}
