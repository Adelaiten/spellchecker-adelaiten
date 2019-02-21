import hashers.BetterStringHasher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BetterStringHasher betterStringHasher = new BetterStringHasher();
        String fileName = "/home/karol/codecool/Codecool-advanced-module/I TW-Week/spellchecker-adelaiten/src/main/resources/wordlist.txt";
        WordList wordList = new WordList(fileName, betterStringHasher);
        WordChecker wordChecker = new WordChecker(wordList);
        System.out.println(wordChecker.getSuggestions("atteunated"));
    }
}
