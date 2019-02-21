import hashers.BetterStringHasher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


public class HashTableTest {

    private BetterStringHasher betterStringHasher;
    private HashTable hashTable;

    @Before
    public void init() {
        betterStringHasher = Mockito.mock(BetterStringHasher.class);
        hashTable = new HashTable(100, betterStringHasher);
    }

    @Test
    public void testAddMethod() {
        hashTable.add("test");
        hashTable.add("smash");
        hashTable.add("blank");
        Assertions.assertAll(
                () -> Assertions.assertTrue(hashTable.lookup("test")),
                () -> Assertions.assertTrue(hashTable.lookup("smash")),
                () -> Assertions.assertTrue(hashTable.lookup("blank")),
                () -> Assertions.assertFalse(hashTable.lookup("notexist"))
        );
    }

    @Test
    public void testIfAddThrowsNullPointerExceptionWhenWordIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> hashTable.add(null));
    }


    @Test
    public void testRemoveMethod() {
        hashTable.add("test");
        hashTable.add("smash");
        hashTable.add("blank");
        hashTable.remove("smash");
        hashTable.remove("blank");
        Assertions.assertAll(
                () -> Assertions.assertTrue(hashTable.lookup("test")),
                () -> Assertions.assertFalse(hashTable.lookup("smash")),
                () -> Assertions.assertFalse(hashTable.lookup("blank"))
        );
    }

    @Test
    public void testIfRemoveMethodThrowsNullWhenStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> hashTable.remove(null));
    }


    @Test
    public void testIfLookUpThrowsNullPointerExceptionWhenNull() {
        Assertions.assertThrows(NullPointerException.class, () -> hashTable.remove(null));
    }

}
