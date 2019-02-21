import hashers.BetterStringHasher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BetterStringHasher betterStringHasher = new BetterStringHasher();
        String fileName = "/home/karol/codecool/Codecool-advanced-module/I TW-Week/spellchecker-adelaiten/src/main/resources/wordlist.txt";
        WordList wordList = new WordList(fileName, betterStringHasher);
        WordChecker wordChecker = new WordChecker(wordList);
        System.out.println(wordChecker.getSuggestions("makecake"));

//        List<String> test = new ArrayList<>();
//        test.add("kupa");
//        test.add("test");
//        test.add(2, "chleb");
//        System.out.println(test);
    }
}
