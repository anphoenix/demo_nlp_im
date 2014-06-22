package nlp.common.util.chinese;

import junit.framework.TestCase;
import org.junit.Test;

public class ChineseStopWordsTest extends TestCase {

    @Test
    public void testIsStopWord() throws Exception {
        String word = null;
        if(ChineseStopWords.INSTANCE.isStopWord(word))
            System.out.println(word+" is in stoplist");
        else
            System.out.println(word+" is not in stoplist");
    }
}