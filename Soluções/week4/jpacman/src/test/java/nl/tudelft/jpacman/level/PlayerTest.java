package nl.tudelft.jpacman.level;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.AnimatedSprite;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test the set alive method
public class PlayerTest {
    private Player player;
    private AnimatedSprite deathSprite = mock(AnimatedSprite.class);

    @BeforeEach
    void setUp() {
        player = new Player(null, deathSprite);
    }

    @Test
    void testAlive() {
        // Set alive
        player.setAlive(true);
        assertThat(player.isAlive()).isTrue();
    }
}
