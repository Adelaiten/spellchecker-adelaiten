package wordCheckers;

import java.util.ArrayList;
import java.util.List;

public class WordSplitter {

    public List<String> splitWords(WordList wordList, String word) {
        char[] wordArray = word.toCharArray();
        List<String> suggestions = new ArrayList<>();
        int loopLength = wordArray.length;
        for(int i = 1; i <= loopLength; i++) {
            String firstSplittedWord = word.substring(0, i);
            String secondSplittedWord = word.substring(i, loopLength);
            addSplittedWhenWordsCorrect(suggestions, wordList, firstSplittedWord, secondSplittedWord);
        }
        return suggestions;
    }

    private void addSplittedWhenWordsCorrect(List<String> suggestions, WordList wordList, String firstSplittedWord, String secondSplittedWord) {
        if(wordList.lookup(firstSplittedWord) && wordList.lookup(secondSplittedWord)) {
            suggestions.add(firstSplittedWord);
            suggestions.add(secondSplittedWord);
        }
    }
}
