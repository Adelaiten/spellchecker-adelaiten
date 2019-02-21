import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordChecker
{

	private WordList wordList;
	public WordChecker(WordList wordList) {
		this.wordList = wordList;
	}
	

	public boolean wordExists(String word) throws StringEmptyException{
		checkIfValueNull(word);
		checkIfStringIsEmpty(word);
		return wordList.lookup(word);
	}


	public ArrayList getSuggestions(String word) throws StringEmptyException{
		checkIfValueNull(word);
		checkIfStringIsEmpty(word);

		ArrayList<String> suggestions = new ArrayList<>();
		swapCharacters(suggestions, word);
		insertLetter(suggestions, word);
		deleteCharacter(suggestions, word);
		replaceLetter(suggestions, word);
		splitWords(suggestions, word);

		return suggestions;
	}


	private void swapCharacters(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		int wordLength = wordArray.length;
		for(int i = 0; i < wordLength -1; i++) {
			char tempChar = wordArray[i];
			wordArray[i] = wordArray[i+1];
			wordArray[i+1] = tempChar;
			String swapWord = String.valueOf(wordArray);
			addWordIfCorrect(suggestions, swapWord);
			wordArray[i+1] = wordArray[i];
			wordArray[i] = tempChar;
		}
	}


	private void insertLetter(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		List<Character> characterList = createCharacterList(wordArray);
		int loopLength = wordArray.length;


		for(int i= 0; i <= loopLength; i++) {
			for(char letter = 'a'; letter < 'z'; letter++) {
				characterList.add(i, letter);
				String newWord = buildStringFromList(characterList);
				addWordIfCorrect(suggestions, newWord);
				characterList.remove(i);
			}

		}
	}


	private void deleteCharacter(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		List<Character> characterList = createCharacterList(wordArray);
		int loopLength = wordArray.length;

		for(int i = 0; i < loopLength; i++) {
			char tempLetter = characterList.get(i);
			characterList.remove(i);
			String newWord = buildStringFromList(characterList);
			addWordIfCorrect(suggestions, newWord);

			characterList.add(i, tempLetter);
		}
	}


	private void replaceLetter(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		List<Character> characterList = createCharacterList(wordArray);
		int loopLength = wordArray.length;
		for(int i = 0; i < loopLength; i++) {
			for(char letter = 'a'; letter < 'z'; letter++) {
				char tempLetter = characterList.get(i);
				changeLetters(characterList, i, letter);
				String newWord = buildStringFromList(characterList);
				addWordIfCorrect(suggestions, newWord);

				changeLetters(characterList, i, tempLetter);
			}
		}
	}


	private void changeLetters(List<Character> characterList, int i, char letter) {
		characterList.remove(i);
		characterList.add(i, letter);
	}


	private void addWordIfCorrect(List<String> suggestions, String newWord) {
		if (wordList.lookup(newWord) && !suggestions.contains(newWord)) {
			suggestions.add(newWord);
		}
	}


	private void splitWords(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		int loopLength = wordArray.length;
		for(int i = 1; i <= loopLength; i++) {
			String firstSplittedWord = word.substring(0, i);
			String secondSplittedWord = word.substring(i, loopLength);
			addSplittedWhenWordsCorrect(suggestions, firstSplittedWord, secondSplittedWord);
		}
	}


	private void addSplittedWhenWordsCorrect(List<String> suggestions, String firstSplittedWord, String secondSplittedWord) {
		if(wordList.lookup(firstSplittedWord) && wordList.lookup(secondSplittedWord)) {
			suggestions.add(firstSplittedWord);
			suggestions.add(secondSplittedWord);
		}
	}


	private String buildStringFromList(List<Character> list) {
	    StringBuilder sb = new StringBuilder();
	    for(char s : list) {
	        sb.append(s);
        }
        return sb.toString();
    }


	private List<Character> createCharacterList(char[] wordArray) {
        List<Character> characterList = new ArrayList<>();
		for(char c : wordArray) {
			characterList.add(c);
		}
		return characterList;
	}


	private void checkIfStringIsEmpty(String word) throws StringEmptyException {
		if(word.equals("")) {
			throw new StringEmptyException("String is Empty!");
		}
	}


	private void checkIfValueNull(String word) {
		if(word == null) {
			throw new NullPointerException();
		}
	}


	private void sortListAlphabetically(List<String> words) {
		Collections.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
	}
}
