import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WordCheckerTest {


    @Test
    public void testIfWordExistsNullStringThrowsNullPointerException() {
        WordList wordList = Mockito.mock(WordList.class);
        WordChecker wordChecker = new WordChecker(wordList);
        Assertions.assertThrows(NullPointerException.class, () -> wordChecker.wordExists(null));
    }


    @Test
    public void testIfWordExistsReturnTrue() throws StringEmptyException{
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("mock")).thenReturn(true);
        WordChecker wordChecker = new WordChecker(wordList);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertTrue(isWord);
    }


    @Test
    public void testIfWordExistsReturnFalse() throws StringEmptyException{
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("mock")).thenReturn(false);
        WordChecker wordChecker = new WordChecker(wordList);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertFalse(isWord);
    }


    @Test
    public void testIfWordExistsThrowsStringEmptyException() {
        WordList wordList = Mockito.mock(WordList.class);
        WordChecker wordChecker = new WordChecker(wordList);
        Assertions.assertThrows(StringEmptyException.class, () -> wordChecker.wordExists(""));
    }

    @Test
    public void testIfGetSuggestionsInsertingReturnsGoodWords() throws StringEmptyException{
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("bake")).thenReturn(true);
        Mockito.when(wordList.lookup("fake")).thenReturn(true);
        Mockito.when(wordList.lookup("cake")).thenReturn(true);
        Mockito.when(wordList.lookup("vade")).thenReturn(true);
        WordChecker wordChecker= new WordChecker(wordList);
        List<String> expected = new ArrayList<>();
        expected.add("bake");
        expected.add("cake");
        expected.add("fake");
        expected.add("vade");
        Assertions.assertIterableEquals(expected, wordChecker.getSuggestions("vake"));

    }





}