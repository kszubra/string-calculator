import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.string.calculator.DefaultTestCalculator;
import com.example.string.calculator.NegativeNumberException;
import com.example.string.calculator.StringCalculator;

public class StringCalculatorTestSuite {

    private final StringCalculator calculator = new DefaultTestCalculator();

    @DisplayName("Returns 0 for blank string")
    @Test
    public void testBlankString() {
        //given
        String input = "";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(0, result);
    }

    @DisplayName("Returns 0 for null input")
    @Test
    public void testNullInput() {
        //given
        String input = null;

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(0, result);
    }

    @DisplayName("Calculates for 1 element")
    @Test
    public void testCountingOneElement() {
        //given
        String input = "3";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(3, result);
    }

    @DisplayName("Calculates for 2 element with whitespace")
    @Test
    public void testCountingTwoElementsWhitespace() {
        //given
        String input = "3, 6";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(9, result);
    }

    @DisplayName("Calculates for 2 element without whitespace")
    @Test
    public void testCountingTwoElementsNoWhitespace() {
        //given
        String input = "3,6";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(9, result);
    }

    @DisplayName("Calculates with additional comma")
    @Test
    public void testCountingWithAdditionalComma() {
        //given
        String input = "3,6,";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(9, result);
    }

    @DisplayName("Calculates many elements")
    @Test
    public void testCountingManyElements() {
        //given
        String input = "3,6, 10, 2, 3, 12,45";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(81, result);
    }

    @DisplayName("Skips values > 1000")
    @Test
    public void testSkippingOver1000() {
        //given
        String input = "3,6, 10, 2, 3, 12,45, 1001";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(81, result);
    }

    @DisplayName("Negative numbers result with NegativeNumberException")
    @Test
    public void testNegativeNumbers() {
        //given
        String input = "3,6, -1, 23, -17";

        //Then
        assertThrows(NegativeNumberException.class, () -> calculator.add(input));
    }

    @DisplayName("NegativeNumberException provides proper message")
    @Test
    public void testNegativeNumberExceptionMessage() {
        //given
        String input = "3,6, -1, 23, -17";
        Exception exception = assertThrows(NegativeNumberException.class, () -> calculator.add(input));
        String expectedMessage = "Negatives not allowed: [-1, -17]";

        //When
        String message = exception.getMessage();

        //Then
        assertEquals(expectedMessage, message);
    }

    @DisplayName("Using new line and comma delimiter")
    @Test
    public void testNewLineAndCommaDelimiter() {
        //given
        String input = "3\n6,10";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(19, result);
    }

    @DisplayName("Using custom delimiter")
    @Test
    public void testUsingCustomDelimiter() {
        //given
        String input = "//[***]\n1***2***3";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(6, result);
    }

    @DisplayName("Using multiple custom delimiters")
    @Test
    public void testUsingMultipleCustomDelimiters() {
        //given
        String input = "//[***][^][??]\n1***2^3??10";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(16, result);
    }

    @DisplayName("Using multiple custom alphabet delimiters")
    @Test
    public void testUsingMultipleCustomAlphabetDelimiters() {
        //given
        String input = "//[***][^][a^a]\n1***2^3a^a10";

        //When
        int result = calculator.add(input);

        //Then
        assertEquals(16, result);
    }

    @DisplayName("Using multiple custom delimiters with negative numbers")
    @Test
    public void testUsingMultipleCustomDelimitersWithNegativeNumbers() {
        //given
        String input = "//[***][^][a^a]\n1***2^3aa-10";

        //Then
        assertThrows(NegativeNumberException.class, () -> calculator.add(input));
    }

    @DisplayName("Using multiple custom delimiters with negative numbers - error message")
    @Test
    public void testErrorMessageUsingMultipleCustomDelimitersWithNegativeNumbers() {
        //given
        String input = "//[***][^][a^a]\n1***2^3aa-10^-17";
        Exception exception = assertThrows(NegativeNumberException.class, () -> calculator.add(input));
        String expectedMessage = "Negatives not allowed: [-10, -17]";

        //When
        String message = exception.getMessage();

        //Then
        assertEquals(expectedMessage, message);
    }


}
