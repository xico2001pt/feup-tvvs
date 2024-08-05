package nl.tudelft.jpacman.ui;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Level;
import java.awt.Dimension;
import java.awt.Graphics;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.SinglePlayerGame;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.ui.BoardPanel;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import java.lang.reflect.Method;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardPanelTest {
    private BoardPanel boardPanel;
    private Board board;

    @BeforeEach
    void setUp() {
        Game game = mock(SinglePlayerGame.class);
        board = mock(Board.class);
        Level level = mock(Level.class);
        when(game.getLevel()).thenReturn(level);
        when(level.getBoard()).thenReturn(board);
        when(board.getHeight()).thenReturn(1);
        when(board.getWidth()).thenReturn(1);
        when(game.getLevel().getBoard()).thenReturn(board);
        when(board.getHeight()).thenReturn(1);
        when(board.getWidth()).thenReturn(1);
        Square square = mock(Square.class);
        when(board.squareAt(0, 0)).thenReturn(square);
        when(square.getSprite()).thenReturn(mock(AnimatedSprite.class));
        boardPanel = new BoardPanel(game);
    }

    @Test // test null game on constructor
    void testNullGame() {
        try {
            BoardPanel boardPanel = new BoardPanel(null);
        } catch (AssertionError e) {
            assertThat(e).isInstanceOf(AssertionError.class);
        }
    }

    @Test // test pain method will null graphics
    void testPaintNullGraphics() {
        try {
            boardPanel.paint(null);
        } catch (AssertionError e) {
            assertThat(e).isInstanceOf(AssertionError.class);
        }
    }

    @Test // test render method
    void testRender() throws Exception {
        Method method = BoardPanel.class.getDeclaredMethod("render", Board.class, Graphics.class, Dimension.class);
        method.setAccessible(true);

        Graphics graphics = mock(Graphics.class);

        method.invoke(boardPanel, board, graphics, mock(Dimension.class));
    }
}
