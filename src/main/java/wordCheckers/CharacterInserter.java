package wordCheckers;

import helpers.CheckerHelper;

import java.util.ArrayList;
import java.util.List;

public class CharacterInserter {
    private CheckerHelper checkerHelper;

    public CharacterInserter(CheckerHelper checkerHelper) {
        this.checkerHelper = checkerHelper;
    }


    public List<String> insertCharacter(WordList wordList, String word) {
        char[] wordArray = word.toCharArray();
        List<String> suggestions = new ArrayList<>();
        List<Character> characterList = checkerHelper.createCharacterList(wordArray);
        int loopLength = wordArray.length;


        for(int i= 0; i <= loopLength; i++) {
            for(char letter = 'a'; letter < 'z'; letter++) {
                characterList.add(i, letter);
                String newWord = checkerHelper.buildStringFromList(characterList);
                checkerHelper.addWordIfCorrect(suggestions,wordList, newWord);
                characterList.remove(i);
            }

        }
        return suggestions;
    }

}
