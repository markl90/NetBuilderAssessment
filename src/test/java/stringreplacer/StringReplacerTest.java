package stringreplacer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringReplacerTest {

    private StringReplacer stringReplacer;


    @BeforeEach
    void setUp() {
        stringReplacer = new StringReplacer();
    }


    @Test
    void assignmentDocumentMessageTest() {
        assertEquals("20 8 9 19 14 5 20 2 21 9 12 4 5 18 1 19 19 5 19 19 13 5 14 20 9 19 23 1 25 20 15 15 5 1 19 25" ,stringReplacer.replaceLetterWithPosition("This NETbuilder assessment is way too easy."));
    }

    @Test
    public void lowerCase(){
        assertEquals("1 2 3" ,stringReplacer.replaceLetterWithPosition("abc"));
    }

    @Test
    public void upperLowerMixed(){
        assertEquals("1 2 3 4 5 6" ,stringReplacer.replaceLetterWithPosition("aBcDeF"));
    }

    @Test
    public void boundary(){
        assertEquals("1 2 25 26" ,stringReplacer.replaceLetterWithPosition("abyz"));
    }

    @Test
    public void specialCharactersOmitted(){
        assertEquals("" ,stringReplacer.replaceLetterWithPosition("@~$£!"));
    }
}