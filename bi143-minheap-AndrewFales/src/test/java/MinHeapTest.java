import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MinHeapTest {
    private MinHeap<String> names = new MinHeap<>();

    @BeforeEach
    public void init() {
        names.offer("Alice", 1.2);
        names.offer("Bob", 2.4);
        names.offer("Jane", -1);
        names.offer("John", 1.1);
        names.offer("Caleb", 3.4);
        names.offer("Jasmine", 2.2);
    }

    @Test
    public void testContains() {
        assertTrue(names.contains("Alice"));
        assertTrue(names.contains("Jane"));
        assertTrue(names.contains("Jasmine"));
        assertTrue(names.contains("Caleb"));
        assertFalse(names.contains("Robert"));
    }

    @Test
    public void testHeapPeek() {
        assertEquals("Jane", names.peek());
        assertEquals("Jane", names.peek());
        assertEquals(6, names.size());
    }

    @Test
    public void testHeapPoll() {
        assertEquals("Jane", names.poll());
        assertEquals("John", names.poll());
        assertEquals(4, names.size());
        assertEquals("Alice", names.poll());
    }
}
