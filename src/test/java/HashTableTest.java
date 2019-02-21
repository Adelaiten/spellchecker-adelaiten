import hashers.BetterStringHasher;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class HashTableTest {

    @Test
    public void testAddMethod() {
        BetterStringHasher betterStringHasher = new BetterStringHasher();
        HashTable hashTable = new HashTable(100, betterStringHasher);
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
    public void testRemoveMethod() {
        BetterStringHasher betterStringHasher = new BetterStringHasher();
        HashTable hashTable = new HashTable(100, betterStringHasher);
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



}
