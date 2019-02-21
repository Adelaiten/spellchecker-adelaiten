import java.util.ArrayList;
import java.util.List;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker
{
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
	private WordList wordList;
	public WordChecker(WordList wordList) {
		this.wordList = wordList;
	}
	

	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise. 
   *
   * @param word Word to chack against the internal word list
   * @return bollean indicating if the word was found or not.
   */
	public boolean wordExists(String word) {
		return wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList getSuggestions(String word) {
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
			if(wordList.lookup(swapWord) && !suggestions.contains(swapWord)) {
				suggestions.add(swapWord);
			}
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
				if(wordList.lookup(newWord) && !suggestions.contains(newWord)) {
					suggestions.add(newWord);
				}
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
			if(wordList.lookup(newWord) && !suggestions.contains(newWord)) {
				suggestions.add(newWord);
			}

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
				characterList.remove(i);
				characterList.add(i, letter);
				String newWord = buildStringFromList(characterList);
				if(wordList.lookup(newWord) && !suggestions.contains(newWord)) {
					suggestions.add(newWord);
				}
				characterList.remove(i);
				characterList.add(i, tempLetter);
			}
		}
	}



	private void splitWords(List<String> suggestions, String word) {
		char[] wordArray = word.toCharArray();
		int loopLength = wordArray.length;
		for(int i = 1; i <= loopLength; i++) {
			String firstSplittedWord = word.substring(0, i);
			String secondSplittedWord = word.substring(i, loopLength);

			addSplittedWhenWordsExist(suggestions, firstSplittedWord, secondSplittedWord);
		}
	}

	private void addSplittedWhenWordsExist(List<String> suggestions, String firstSplittedWord, String secondSplittedWord) {
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

}
