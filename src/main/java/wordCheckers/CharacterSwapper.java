package wordCheckers;

import helpers.CheckerHelper;

import java.util.ArrayList;
import java.util.List;

public class CharacterSwapper {
    private CheckerHelper checkerHelper;

    public CharacterSwapper(CheckerHelper checkerHelper) {
        this.checkerHelper = checkerHelper;
    }


    public List<String> swapCharacters( WordList wordList, String word) {
        char[] wordArray = word.toCharArray();
        int wordLength = wordArray.length;
        List<String> suggestions = new ArrayList<>();
        for(int i = 0; i < wordLength -1; i++) {
            char tempChar = wordArray[i];
            wordArray[i] = wordArray[i+1];
            wordArray[i+1] = tempChar;
            String swapWord = String.valueOf(wordArray);
            checkerHelper.addWordIfCorrect(suggestions, wordList, swapWord);
            wordArray[i+1] = wordArray[i];
            wordArray[i] = tempChar;
        }
        return suggestions;
    }
}
