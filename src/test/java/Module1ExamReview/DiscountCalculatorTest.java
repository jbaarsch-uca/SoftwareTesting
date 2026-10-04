package Module1ExamReview;

import BVA.Wizard;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountCalculatorTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/DiscountCalculatorTestCasesValid.csv", numLinesToSkip = 1)
    public void testGetDiscountPercentValid(String testCaseName, int age, boolean isMember, double expectedResult) {
       double result = DiscountCalculator.getDiscountPercent(age, isMember);

       assertEquals(expectedResult, result, "Test case " + testCaseName + " failed.");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DiscountCalculatorTestCasesInvalid.csv", numLinesToSkip = 1)
    public void testGetDiscountPercentInvalid(String testCaseName, int age, boolean isMember, String expectedResult) {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> { DiscountCalculator.getDiscountPercent(age, isMember);
                });
        assertEquals(expectedResult, exception.getMessage(), "Test case " + testCaseName + " failed.");

    }




}
