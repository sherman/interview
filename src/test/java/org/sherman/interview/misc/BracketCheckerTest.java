package org.sherman.interview.misc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 17/02/2016
 */
public class BracketCheckerTest {
    @Test(dataProvider = "cases")
    public void isCorrect(String sequence, boolean expectedResult) {
        assertEquals(BracketChecker.isCorrect(sequence), expectedResult);
    }

    @DataProvider
    public static Object[][] cases() {
        return new Object[][]{
                {"{}", true},
                {"{", false},
                {"{}}", false},
                {"{{{{{}}}}}", true}
        };
    }
}
