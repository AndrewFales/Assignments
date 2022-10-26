import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashMapTest {
    private HashMap map;
    @BeforeEach
    public void init() {
        map = new HashMap();
    }

    @Test
    public void testHash() {
        assertEquals(91043, map.hashFunction("key"));
        assertEquals(1538548999, map.hashFunction("string"));
    }

    @Test
    public void testPut() throws TableIsFullException {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
        assertEquals(6, map.size());
        assertEquals(
            "[{1055510:value1}, null, {1134242:value5}, {1075193:value2}, null, {1153925:value6}, " +
            "{1094876:value3}, null, null, {1114559:value4}]", map.toString());
        map.put("key7", "value7");
        assertEquals(7, map.size());
        assertEquals("[{1055510:value1}, null, {1134242:value5}, {1075193:value2}, null, {1153925:value6}, "+ 
        "{1094876:value3}, null, {1173608:value7}, {1114559:value4}]", map.toString());
        map.put("key8", "value8");
        map.put("key9", "value8");
        assertThrows(TableIsFullException.class, () -> map.put("key10", "value8"));
    }

    @Test
    public void testGet() throws TableIsFullException {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
        assertEquals("value3", map.get("key3"));
        assertEquals(null, map.get("key9"));   
    }

    @Test
    public void testUpdate() throws TableIsFullException {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        map.update("key3", "THREE");
        assertEquals("THREE", map.get("key3"));
        assertEquals(3, map.size());
        
    }

    @Test
    public void testDelete() throws TableIsFullException {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");

        assertEquals("value2", map.delete("key2"));
        assertEquals(5, map.size());
        assertEquals("[{1055510:value1}, null, {1134242:value5}, {0:value2}, null, "+ 
        "{1153925:value6}, {1094876:value3}, null, null, {1114559:value4}]", map.toString());
    }
}
