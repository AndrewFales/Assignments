import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

public class SuperArrayTest {
    private SuperArray<Account> accounts;
    @BeforeEach
    private void init() {
        accounts = new SuperArray<>();
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user2", "abcd"));
        accounts.push(new Account("user3", "eeee"));
    }

    @Test
    public void testPush() {
        accounts.push(new Account("user4", "tttt"));
        assertEquals(4, accounts.size());
        assertEquals(4, accounts.capacity());
        accounts.push(new Account("user5", "zzzz"));
        assertEquals(6, accounts.capacity());
    }

    @Test
    public void testAdd() {
        accounts.add(1, new Account("user1.5", "bbbb"));
        assertEquals(4, accounts.size());
        assertEquals(4, accounts.capacity());
        assertEquals("user2", accounts.get(2).getUserName());
        assertEquals("user3", accounts.get(3).getUserName());
        accounts.add(3, new Account("user2.5", "cccc"));
        assertEquals(5, accounts.size());
        assertEquals(6, accounts.capacity());
    }

    @Test
    public void testPop() {
        Account ac = (Account)accounts.pop();
        assertEquals(ac, new Account("user3", "eeee"));
        assertEquals(2, accounts.size());
    }

    @Test
    public void testRead() {
        Account u2 = accounts.get(1);
        assertEquals(u2.getUserName(), "user2");
        assertEquals(u2.getPassword(), "abcd");

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> accounts.get(7));
    }

    @Test
    public void testUpdate() {
        Account acc = new Account("us", "pw");
        accounts.update(2, acc);
        Account u3 = accounts.get(2);
        assertEquals(u3.getUserName(), "us");
        assertEquals(u3.getPassword(), "pw");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> accounts.update(8, acc));

    }

    @Test
    public void testFind() {
        assertEquals(accounts.find(new Account("user3", "eeee")), 2);
        assertEquals(accounts.find(new Account("aa", "bb")), -1);
    }

    @Test
    public void testDeleteByIndex() {
        accounts.delete(0);
        assertEquals(-1, accounts.find(new Account("user1", "1234")));
        assertEquals(1, accounts.find(new Account("user3", "eeee")));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> accounts.get(4));
        assertEquals(2, accounts.size());
    }

    @Test
    public void testDeleteByAccount() {
        accounts.delete(new Account("user1", "1234"));
        assertEquals(2, accounts.size());
        assertEquals(0, accounts.find(new Account("user2", "abcd")));
    }
    
}
