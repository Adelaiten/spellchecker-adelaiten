import exceptions.StringEmptyException;
import helpers.CheckerHelper;
import wordCheckers.*;

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
	

	public boolean wordExists(String word) throws StringEmptyException {
		checkIfValueNull(word);
		checkIfStringIsEmpty(word);
		return wordList.lookup(word);
	}


	public ArrayList getSuggestions(String word) throws StringEmptyException{
		CheckerHelper helper = new CheckerHelper();
		CharacterSwapper swapper = new CharacterSwapper(helper);
		CharacterDeleter deleter = new CharacterDeleter(helper);
		CharacterInserter inserter = new CharacterInserter(helper);
		CharacterReplacer replacer = new CharacterReplacer(helper);
		WordSplitter splitter = new WordSplitter();
		checkIfValueNull(word);
		checkIfStringIsEmpty(word);

		ArrayList<String> suggestions = new ArrayList<>();
		suggestions.addAll(swapper.swapCharacters(wordList, word));
		suggestions.addAll(deleter.deleteCharacter(wordList, word));
		suggestions.addAll(inserter.insertCharacter(wordList, word));
		suggestions.addAll(replacer.replaceCharacter(wordList, word));
		suggestions.addAll(splitter.splitWords(wordList, word));

		sortListAlphabetically(suggestions);
		return suggestions;
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
