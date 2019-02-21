
import hashers.StringHasher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordList {
    private final HashTable hashTable;

    public WordList(String fileName, StringHasher stringHasher) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        int n = Integer.parseInt(bufferedReader.readLine());
        this.hashTable = new HashTable((int)((double)n * 1.2), stringHasher);

        for (int i = 0; i < n; ++i) {
            this.hashTable.add(bufferedReader.readLine().trim().toUpperCase());
        }
        bufferedReader.close();
    }

    public boolean lookup(String string) {
        return this.hashTable.lookup(string.toUpperCase());
    }
}
