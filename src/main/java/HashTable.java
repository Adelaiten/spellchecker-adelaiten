import hashers.StringHasher;

import java.util.LinkedList;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your hash table here.  You are required to use the separate
 * chaining strategy that we discussed in lecture, meaning that collisions
 * are resolved by having each cell in the table be a linked list of all of
 * the strings that hashed to that cell.
 */

public class HashTable
{
	/**
   * The constructor is given a table size (i.e. how big to make the array)
   * and a hashers.StringHasher, which is used to hash the strings.
   *
   * @param tableSize number of elements in the hash array
   *        hasher    Object that creates the hash code for a string
   * @see StringHasher
   */
	private LinkedList<String>[] wordsArray;
	private StringHasher hasher;

	public HashTable(int tableSize, StringHasher hasher)
	{
		this.wordsArray = new LinkedList[tableSize];
		this.hasher = hasher;
	}


	/**
   * Takes a string and adds it to the hash table, if it's not already
   * in the hash table.  If it is, this method has no effect.
   *
   * @param s String to add
   */
	public void add(String s) {
	    throwNullPointerException(s);
        int index = getIndex(s);
        if(wordsArray[index] == null) {
            wordsArray[index] = new LinkedList<>();
            wordsArray[index].add(s);
        }else {
            wordsArray[index].add(s);
        }

	}


    /**
  * Takes a string and returns true if that string appears in the
	* hash table, false otherwise.
  *
  * @param s String to look up
  */
	public boolean lookup(String s) {
	    throwNullPointerException(s);

        LinkedList<String> list = getWordList(s);
        return list.contains(s);
	}


    /**
   * Takes a string and removes it from the hash table, if it
   * appears in the hash table.  If it doesn't, this method has no effect.
   *
   * @param s String to remove
  */
	public void remove(String s) {
        throwNullPointerException(s);
        LinkedList<String> list = getWordList(s);
        list.remove(s);
	}

    private void throwNullPointerException(String s) {
        if(s == null) {
            throw new NullPointerException();
}
    }


    private LinkedList<String> getWordList(String s) {
        int index = getIndex(s);
        return wordsArray[index];
    }


    private int getIndex(String s) {


	    return Math.floorMod(hasher.hash(s), wordsArray.length);
    }
}
