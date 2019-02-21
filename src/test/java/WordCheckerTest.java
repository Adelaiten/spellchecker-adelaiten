import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class WordCheckerTest {


    @Test
    public void testIfWordExistsReturnTrue() {
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("mock")).thenReturn(true);
        WordChecker wordChecker = new WordChecker(wordList);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertTrue(isWord);
    }

    @Test
    public void testIfWordExistsReturnFalse() {
        WordList wordList = Mockito.mock(WordList.class);
        Mockito.when(wordList.lookup("mock")).thenReturn(false);
        WordChecker wordChecker = new WordChecker(wordList);
        boolean isWord = wordChecker.wordExists("mock");
        Assertions.assertFalse(isWord);
    }

 
}