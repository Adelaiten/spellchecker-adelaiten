package wordCheckers;

import helpers.CheckerHelper;

import java.util.ArrayList;
import java.util.List;

public class CharacterDeleter {
    private CheckerHelper checkerHelper;

    public CharacterDeleter(CheckerHelper checkerHelper) {
        this.checkerHelper = checkerHelper;
    }

    public List<String> deleteCharacter(WordList wordList, String word) {
        char[] wordArray = word.toCharArray();
        List<String> suggestions = new ArrayList<>();
        List<Character> characterList = checkerHelper.createCharacterList(wordArray);
        int loopLength = wordArray.length;

        for(int i = 0; i < loopLength; i++) {
            char tempLetter = characterList.get(i);
            characterList.remove(i);
            String newWord = checkerHelper.buildStringFromList(characterList);
            checkerHelper.addWordIfCorrect(suggestions, wordList, newWord);

            characterList.add(i, tempLetter);
        }

        return suggestions;
    }
}
