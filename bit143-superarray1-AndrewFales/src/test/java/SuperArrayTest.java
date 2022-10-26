import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

public class SuperArrayTest {
    private static SuperArray accounts;
    @BeforeAll
    private static void init() {
        accounts = new SuperArray(5);
        accounts.add(new Account("user1", "1234"));
        accounts.add(new Account("user2", "abcd"));
        accounts.add(new Account("user1", "1234"));
        accounts.add(new Account("user3", "eeee"));
        accounts.add(new Account("user4", "aaaa"));
    }

    @Test
    public void testAdd() {
        assertThrows(ListIsFullException.class, () -> accounts.add(new Account("user5", "pwdd")));
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
        assertEquals(accounts.find(new Account("user3", "eeee")), 3);
        assertEquals(accounts.find(new Account("aa", "bb")), -1);
    }

    @Test
    public void testDeleteByIndex() {
        accounts.delete(3);
        assertEquals(-1, accounts.find(new Account("user3", "eeee")));
        assertEquals(3, accounts.find(new Account("user4", "aaaa")));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> accounts.get(4));
        assertEquals(4, accounts.size());
    }

    @Test
    public void testDeleteByAccount() {
        accounts.delete(new Account("user1", "1234"));
        System.out.println(accounts);
        assertEquals(4, accounts.size());
        assertEquals(3, accounts.find(new Account("user4", "aaaa")));
        assertEquals(0, accounts.find(new Account("user2", "abcd")));
    }
    
}
