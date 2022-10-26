import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    private LinkedList<Account> accounts;

    @BeforeEach
    public void init() {
        accounts = new LinkedList<>();
    }

    @Test
    public void testGet() throws ListIndexOutOfBoundException {
        Account u1 = new Account("user1", "1234");
        Account u2 = new Account("user2", "abcd");
        Account u3 = new Account("user3", "eeee");
        assertThrows(ListIndexOutOfBoundException.class, () -> accounts.get(0));
        accounts.push(u1);
        accounts.push(u2);
        accounts.push(u3);
        assertEquals(u2, accounts.get(1));
    }

    @Test
    public void testPush() throws ListIndexOutOfBoundException {
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user2", "abcd"));
        accounts.push(new Account("user3", "eeee"));

        assertEquals(3, accounts.size());
        assertEquals("user3", accounts.get(2).getUserName());
    }

    @Test
    public void testInsert() throws ListIndexOutOfBoundException {
        accounts.insert(0, new Account("user1", "1234"));
        assertEquals(1, accounts.size());
        assertEquals("user1", accounts.get(0).getUserName());
        
        assertThrows(ListIndexOutOfBoundException.class, () -> accounts.insert(10, null));
        Account u3 = new Account("user3", "abcdefg");
        accounts.push(u3);
        Account u2 = new Account("user2", "abcd");
        accounts.insert(1, u2);
        assertEquals(u2, accounts.get(1));
        assertEquals(3, accounts.size());

        Account u4 = new Account("user4", "fffff");
        accounts.insert(accounts.size() - 1, u4);
        assertEquals(u4, accounts.get(2));
        assertEquals(u3, accounts.get(3));
    }

    @Test
    public void testFind() {
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user2", "abcd"));
        accounts.push(new Account("user3", "eeee"));

        assertEquals(1, accounts.find(new Account("user2", "abcd")));
        assertEquals(-1, accounts.find(new Account("user6", "abcd")));
    }

    @Test
    public void testDelete() throws ListIndexOutOfBoundException, EmptyListException {
        Account u1 = new Account("user1", "1234");
        Account u2 = new Account("user2", "abcd");
        Account u3 = new Account("user3", "eeee");
        accounts.push(u1);
        accounts.push(u2);
        accounts.push(u3);

        accounts.delete(1);
        assertEquals(2, accounts.size());
        assertEquals(0, accounts.find(u1));
        assertEquals(1, accounts.find(u3));
        assertEquals(-1, accounts.find(u2));
        accounts.delete(0);
        accounts.delete(0);
        assertEquals(0, accounts.size());
        accounts.push(u1);
        accounts.push(u2);
        assertEquals(2, accounts.size());
        assertEquals(1, accounts.find(u2));
        assertEquals(0, accounts.find(u1)); 
    }
}
