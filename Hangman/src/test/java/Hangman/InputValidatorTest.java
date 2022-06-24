package Hangman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    public InputValidator validator;
    public Scanner numberInput;
    public Scanner textInput;
    public Scanner charInput;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();

        String fakeNumberInputs = "10" + System.getProperty("line.separator") + "0";
        ByteArrayInputStream numIn = new ByteArrayInputStream(fakeNumberInputs.getBytes());
        System.setIn(numIn);
        numberInput = new Scanner(System.in);

        String fakeStringInputs =
                "Y" + System.getProperty("line.separator")
                        + "y"+ System.getProperty("line.separator")
                        + "n" + System.getProperty("line.separator")
                        + "N";

        ByteArrayInputStream stringIn = new ByteArrayInputStream(fakeStringInputs.getBytes());
        System.setIn(stringIn);
        textInput = new Scanner(System.in);

        String fakeCharInputs =
                "1" + System.getProperty("line.separator")
                        + "y"+ System.getProperty("line.separator")
                        + "n" + System.getProperty("line.separator")
                        + "N";

        ByteArrayInputStream charIn = new ByteArrayInputStream(fakeStringInputs.getBytes());
        System.setIn(charIn);
        charInput = new Scanner(System.in);
    }

    @AfterEach
    void tearDown() {
        numberInput.close();
        textInput.close();
        validator = null;
    }

    //Number Validator
    @DisplayName("Correct Number Test")
    @Test
    void GetValidNumberCorrectTest() {
        int num = validator.GetValidNumberInput(numberInput, 5, 15);
        assertEquals(10, num, "Number validation has failed!");
    }

    @DisplayName("Out of range Number Test")
    @Test
    void GetValidNumberOutOfRangeTest() {
        int num = validator.GetValidNumberInput(numberInput, 0, 0);
        assertEquals(0, num, "Out of range validation has failed.");
    }

    @DisplayName("Scanner closed Number Test")
    @Test
    void GetValidNumberScannerClosedTest() {
        numberInput.close();
        int num = validator.GetValidNumberInput(numberInput, 0, 0);
        assertEquals(0, num, "Scanner shut down validation has failed");
    }


    //Text Validator
    @DisplayName("Correct String Test")
    @Test
    void GetValidStringCorrectTest() {
        String str = validator.GetValidStringInput (textInput,new String[]{"N"});
        assertEquals("N", str, "String validation has failed!");
    }

    @DisplayName("Scanner closed String Test")
    @Test
    void GetValidStringScannerClosedTest() {
        textInput.close();
        String str = validator.GetValidStringInput (textInput,new String[]{"N"});
        assertEquals("N", str, "String validation has failed!");
    }


    //Character Validator
    @DisplayName("Correct Char Test")
    @Test
    void GetValidCharCorrectTest()
    {
        Character chr = validator.GetValidCharacterInput (charInput);
        assertEquals('Y', chr, "String validation has failed!");
    }


    @DisplayName("Scanner Closed Char Test")
    @Test
    void GetValidCharScannerClosedTest()
    {
        charInput.close();
        Character chr = validator.GetValidCharacterInput (charInput);
        assertEquals('N', chr, "String validation has failed!");
    }


}