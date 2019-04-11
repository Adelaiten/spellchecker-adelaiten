package wordCheckers;

import helpers.CheckerHelper;

import java.util.ArrayList;
import java.util.List;

public class CharacterReplacer {
    private CheckerHelper checkerHelper;

    public CharacterReplacer(CheckerHelper checkerHelper) {
        this.checkerHelper = checkerHelper;
    }


    public List<String> replaceCharacter(WordList wordList, String word) {
        char[] wordArray = word.toCharArray();
        List<String> suggestions = new ArrayList<>();
        List<Character> characterList = checkerHelper.createCharacterList(wordArray);
        int loopLength = wordArray.length;
        for(int i = 0; i < loopLength; i++) {
            for(char letter = 'a'; letter < 'z'; letter++) {
                char tempLetter = characterList.get(i);
                checkerHelper.changeLetters(characterList, i, letter);
                String newWord = checkerHelper.buildStringFromList(characterList);
                checkerHelper.addWordIfCorrect(suggestions, wordList, newWord);

                checkerHelper.changeLetters(characterList, i, tempLetter);
            }
        }
        return suggestions;
    }
}
