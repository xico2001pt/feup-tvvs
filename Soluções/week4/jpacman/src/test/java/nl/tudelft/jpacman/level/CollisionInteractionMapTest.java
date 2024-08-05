package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionInteractionMapTest {
    private Method method;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        method = CollisionInteractionMap.class.getDeclaredMethod("getInheritance", Class.class);
        method.setAccessible(true);
    }

    @Test
    public void playerClassTest() throws InvocationTargetException, IllegalAccessException {
        CollisionInteractionMap collisionInteractionMap = new CollisionInteractionMap();
        List<Class<? extends Unit>> found = (List<Class<? extends Unit>>) method.invoke(collisionInteractionMap, Player.class);
        assertEquals(2, found.size());
    }

    @Test
    public void unitClassTest() throws InvocationTargetException, IllegalAccessException {
        CollisionInteractionMap collisionInteractionMap = new CollisionInteractionMap();
        List<Class<? extends Unit>> found = (List<Class<? extends Unit>>) method.invoke(collisionInteractionMap, Unit.class);
        assertEquals(1, found.size());
    }
}
