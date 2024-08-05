package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.npc.Ghost;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = {
            { mock(Square.class), mock(Square.class), mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Verifies the board has the correct width.
     */
    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    /**
     * Verifies the board has the correct height.
     */
    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    /**
     * Verify that squares at key positions are properly set.
     * 
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 2",
            "0, 1"
    })
    void testSquareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

    @Test
    void createBoardTest1() {
        /*
         * 1. grid = null (C1) and Board object is null (C5)
         */
        try {
            new Board(null);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void createBoardTest2() {
        /*
         * 2. grid.length = 0 (C2) and Board object is null (C5)

         */
        try {
            Square[][] grid = new Square[0][0];
            new Board(grid);
        } catch (AssertionError e) {
            assertEquals("Initial grid cannot contain null squares", e.getMessage());
        }
    }

    @Test
    void createBoardTest3() {
        /*
         * 3. grid.length = 1 and grid[random][random] = null (C3) and Board object is null (C5)
         */
        try {
            Square[][] grid = new Square[1][1];
            grid[0][0] = null;
            new Board(grid);
        } catch (AssertionError e) {
            assertEquals("Initial grid cannot contain null squares", e.getMessage());
        }
    }

    @Test
    void createBoardTest4() {
        /*
         * 4. grid.length = 1 and grid[random][random] != null (C4) and Board object is not null and its height and width are not equal to the inputed ones (C6)
         */
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        Board board = new Board(grid);

        assertNotNull(board);

        assertNotEquals(grid.length, board.getHeight()+1);
        assertNotEquals(grid[0].length, board.getWidth()+1);
    }

    @Test
    void createBoardTest5() {
        /*
         * 5. grid.length = 1 and grid[random][random] != null (C4) and Board object is not null and its height and width are equal to the inputed ones (C7)
         */
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        Board board = new Board(grid);

        assertNotNull(board);

        assertEquals(grid.length, board.getHeight());
        assertEquals(grid[0].length, board.getWidth());
    }

        @Test
    void testCreateLevel1() {
        /*
         * 1. board = null (C1) and ghosts = null (C4) and startPositions = null (C7) and Level object is null (C12)
         */
        Board board = null;

        List<Ghost> ghosts = null;

        List<Square> startPositions = null;

        try {
            Level level = new Level(board, ghosts, startPositions, null);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testCreateLevel2() {
        /*
         * 2. board != null and board.getGhostCount() = 0 and board.getPelletCount() = 0 (C2) and startPositions != null and startPositions.size() = 0 (C8) and Level object is not null and has a board (C14)
         */
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        Board board = new Board(grid);
        
        List<Ghost> ghosts = new ArrayList<Ghost>();

        List<Square> startPositions = new ArrayList<Square>();

        Level level = new Level(board, ghosts, startPositions, null);
        assertNotNull(level);
    }

    @Test
    void testCreateLevel3() {
        /*
         * 3. board != null and board.getGhostCount() >= 1 or board.getPelletCount() >= 1 (C3) and startPositions != null and startPositions.size() >= 1 (C9) and Level object is not null and has a board (C14)
         */
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();
        Board board = new Board(grid);

        List<Ghost> ghosts = new ArrayList<Ghost>();
        ghosts.add(mock(Ghost.class));

        List<Square> startPositions = new ArrayList<Square>();
        startPositions.add(mock(Square.class));

        Level level = new Level(board, ghosts, startPositions, null);

        assertNotNull(level);
    }
}
