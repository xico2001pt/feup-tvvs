package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Test suite to confirm the API of {@link Square} works as desired.
 *
 * @author Jeroen Roosen 
 */
class SquareTest {

    /**
     * The square under test.
     */
    private Square square;

    private final Square[][] grid = {
        { mock(Square.class), mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Resets the square under test.
     */
    @BeforeEach
    void setUp() {
        square = new BasicSquare();
    }

    /**
     * Assert that the square holds the occupant once it has occupied it.
     */
    @Test
    void testOccupy() {
        Unit occupant = mock(Unit.class);
        square.put(occupant);

        assertThat(square.getOccupants()).contains(occupant);
    }

    /**
     * Assert that the square no longer holds the occupant after it has left the
     * square.
     */
    @Test
    void testLeave() {
        Unit occupant = mock(Unit.class);
        square.put(occupant);
        square.remove(occupant);

        assertThat(square.getOccupants()).doesNotContain(occupant);
    }

    /**
     * Assert that the order in which units entered the square is preserved.
     */
    @Test
    void testOrder() {
        Unit o1 = mock(Unit.class);
        Unit o2 = mock(Unit.class);
        square.put(o1);
        square.put(o2);

        assertThat(square.getOccupants()).containsSequence(o1, o2);
    }

    @Test
    void testSquareAt1() {
        /*
         * 1. x = -1, y = -1 (C1, C4) and Square object is null (C7)
         */
        try {
            board.squareAt(-1, -1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt2() {
        /*
         * 2. x = 0, y = -1 (C2, C4) and Square object is null (C7)
         */
        try {
            board.squareAt(0, -1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt3() {
        /*
         * 3. x = width - 1, y = -1 (C2, C4) and Square object is null (C7)
         */
        try {
            board.squareAt(2, -1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt4() {
        /*
         * 4. x = width, y = -1 (C3, C4) and Square object is null (C7)
         */
        try {
            board.squareAt(3, -1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt5() {
        /*
         * 5. x = width + 1, y = -1 (C3, C4) and Square object is null (C7)
         */
        try {
            board.squareAt(4, -1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt6() {
        /*
         * 6. x = -1, y = 0 (C1, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(-1, 0);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt7() {
        /*
         * 7. x = 0, y = 0 (C2, C5) and Square object is not null (C8 or C9)
         */
        Square square = board.squareAt(0, 0);
        assertNotNull(square);
    }

    @Test
    void testSquareAt8() {
        /*
         * 8. x = width - 1, y = 0 (C2, C5) and Square object is not null (C8 or C9)
         */
        try {
            board.squareAt(2, 0);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt9() {
        /*
         * 9. x = width, y = 0 (C3, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(3, 0);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt10() {
        /*
         * 10. x = width + 1, y = 0 (C3, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(4, 0);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt11() {
        /*
         * 11. x = -1, y = height - 1 (C1, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(-1, 1);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt12() {
        /*
         * 12. x = 0, y = height - 1 (C2, C5) and Square object is not null (C8 or C9)
         */
        Square square = board.squareAt(0, 2);
        assertNotNull(square);
    }

    @Test
    void testSquareAt13() {
        /*
         * 13. x = width - 1, y = height - 1 (C2, C5) and Square object is not null (C8 or C9)
         */
        try {
            board.squareAt(2, 2);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt14() {
        /*
         * 14. x = width, y = height - 1 (C3, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(3, 2);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt15() {
        /*
         * 15. x = width + 1, y = height - 1 (C3, C5) and Square object is null (C7)
         */
        try {
            board.squareAt(4, 2);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt16() {
        /*
         * 16. x = -1, y = height (C1, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(-1, 3);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt17() {
        /*
         * 17. x = 0, y = height (C2, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(0, 3);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt18() {
        /*
         * 18. x = width - 1, y = height (C2, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(2, 3);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt19() {
        /*
         * 19. x = width, y = height (C3, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(3, 3);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt20() {
        /*
         * 20. x = width + 1, y = height (C3, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(4, 3);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt21() {
        /*
         * 21. x = -1, y = height + 1 (C1, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(-1, 4);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt22() {
        /*
         * 22. x = 0, y = height + 1 (C2, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(0, 4);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt23() {
        /*
         * 23. x = width - 1, y = height + 1 (C2, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(2, 4);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt24() {
        /*
         * 24. x = width, y = height + 1 (C3, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(3, 4);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    void testSquareAt25() {
        /*
         * 25. x = width + 1, y = height + 1 (C3, C6) and Square object is null (C7)
         */
        try {
            board.squareAt(4, 4);
        } catch (AssertionError e) {
            assertEquals(null, e.getMessage());
        }
    }
}
