import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BstTest {
    private BST bst;
    @BeforeEach
    public void init() {
        bst = new BST();
        bst.add(4);
        bst.add(8);
        bst.add(2);
        bst.add(6);
        bst.add(10);
        bst.add(12);
        bst.add(7);
    }

    @Test
    public void testFind() {
        assertTrue(bst.hasKey(8));
        assertFalse(bst.hasKey(14));
    }

    @Test
    public void testHeight() {
        assertEquals(3, bst.height());
    }

    @Test
    public void testInOrder() {
        BST tmpBst = new BST();
        assertEquals("", tmpBst.inOrderString());
        assertEquals("2 4 6 7 8 10 12", bst.inOrderString());
    }
    @Test
    public void testPreOrder() {
        BST tmpBst = new BST();
        assertEquals("", tmpBst.preOrderString());
        assertEquals("4 2 8 6 7 10 12", bst.preOrderString());
    }

    @Test
    public void testLevelOrder() {
        BST tmpBst = new BST();
        assertEquals("", tmpBst.levelOrderString());
        assertEquals("4 2 8 6 10 7 12", bst.levelOrderString());
    }

    @Test
    public void testAdd() {
        bst.add(13);
        bst.add(3);
        assertTrue(bst.hasKey(13));
        assertEquals(9, bst.size());
        assertEquals(4, bst.height());
        assertEquals("4 2 8 3 6 10 7 12 13", bst.levelOrderString());
    }

    @Test 
    public void testDelete() {
        bst.add(13);
        bst.add(3);
        bst.delete(10);
        bst.delete(4);
        assertEquals(7, bst.size());
        assertEquals(3, bst.height());
        assertEquals("6 2 8 3 7 12 13", bst.levelOrderString());
    }
}
