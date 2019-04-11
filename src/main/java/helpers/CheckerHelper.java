package helpers;

import wordCheckers.WordList;

import java.util.ArrayList;
import java.util.List;

public class CheckerHelper {

    public void addWordIfCorrect(List<String> suggestions, WordList wordList, String suggestedWord) {
        if (wordList.lookup(suggestedWord) && !suggestions.contains(suggestedWord)) {
            suggestions.add(suggestedWord);
        }
    }


    public List<Character> createCharacterList(char[] wordArray) {
        List<Character> characterList = new ArrayList<>();
        for(char c : wordArray) {
            characterList.add(c);
        }
        return characterList;
    }


     public String buildStringFromList(List<Character> list) {
        StringBuilder sb = new StringBuilder();
        for(char s : list) {
            sb.append(s);
        }
        return sb.toString();
    }


    public void changeLetters(List<Character> characterList, int i, char letter) {
        characterList.remove(i);
        characterList.add(i, letter);
    }
}
