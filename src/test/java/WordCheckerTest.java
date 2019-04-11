import exceptions.StringEmptyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import wordCheckers.WordList;

import java.util.ArrayList;
import java.util.List;

public class WordCheckerTest {
    WordList wordList;
    WordChecker wordChecker = new WordChecker(wordList);

    @Before
    public void init() {
        wordList = Mockito.mock(WordList.class);
        wordChecker = new WordChecker(wordList);
    }

    @Test
    public void testIfWordExistsNullStringThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> wordChecker.wordExists(null));
    }


    @Test
    public void testIfWordExistsReturnTrue() throws StringEmptyException {
        Mockito.when(wordList.lookup("mock")).thenReturn(true);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertTrue(isWord);
    }


    @Test
    public void testIfWordExistsReturnFalse() throws StringEmptyException{
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("mock")).thenReturn(false);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertFalse(isWord);
    }


    @Test
    public void testIfWordExistsThrowsStringEmptyException() {
        Assertions.assertThrows(StringEmptyException.class, () -> wordChecker.wordExists(""));
    }

    @Test
    public void testIfGetSuggestionsInsertingReturnsGoodWords() throws StringEmptyException{
        Mockito.when(wordList.lookup("bake")).thenReturn(true);
        Mockito.when(wordList.lookup("fake")).thenReturn(true);
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        Mockito.when(wordList.lookup("vade")).thenReturn(true);
        List<String> expected = new ArrayList<>();
        expected.add("bake");
        expected.add("cake");
        expected.add("fake");
        expected.add("vade");
        Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("vake"));

    }

    @Test
    public void testIfGetSuggestionsSwapReturnsGoodWords() throws StringEmptyException {
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        Mockito.when(wordList.lookup("acek")).thenReturn(true);
        WordChecker wordChecker= new WordChecker(wordList);
        List<String> expected = new ArrayList<>();
        expected.add("acek");
        expected.add("cake");

        Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("acke"));

    }


    @Test
    public void testIfGetSuggestionsDeletingReturnsGoodWords() {
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        List<String> expected = new ArrayList<>();
        expected.add("cake");
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("acake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("caake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("ccake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("cakke")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("cakee"))
                );
    }


    @Test
    public void testIfGetSuggestionsReplacingReturnsGoodWords() {
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        List<String> expected = new ArrayList<>();
        expected.add("cake");
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("rake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("dake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("fake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("lake")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("cike")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("caoe")),
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("caki"))
        );
    }

    @Test
    public void testIfGetSuggestionsSplittingWordsReturnsGoodWords() {
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        Mockito.when(wordList.lookup("vase")).thenReturn(true);
        List<String> expected = new ArrayList<>();
        expected.add("cake");
        expected.add("vase");
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("cakevase"))
        );
    }
}