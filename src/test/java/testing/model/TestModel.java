package testing.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import pl.polsl.braillemodel.giovanaanaya.BrailleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

/**
 * The TestModel class contains all the test that will be executed for the
 * converter application.
 *
 * @author Giovana Anaya
 * @version 1.0
 */
public class TestModel {

    private BrailleModel braille;

      /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUpClass() {
        braille = new BrailleModel();

    }

    /**
     * Test the convert to Braille method.
     *
     * @throws BrailleModel.ModelException if there is an issue with the Braille
     * model.
     */
    @Test
    public void testGetBrailleRepresentation() throws BrailleModel.ModelException {
        //Test the method
        //String resultA = BrailleModel.convertTextToBraille("");
        //assertEquals("001000", resultA);
    }

    /**
     * Test method to verify the conversion of a character to Braille
     * representation.
     *
     * @param input the character to be converted
     * @param expected the expected Braille representation
     */
    @ParameterizedTest
    @CsvSource({"h,18"})
    void shouldConvertBraille(String input, String expected) {
        try {
            String actualValue = braille.convertTextToBraille(input);
            assertEquals(expected, actualValue, "");
        } catch (BrailleModel.ModelException e) {

        }
    }

    /**
     * Test method to verify the conversion of Braille representation to text.
     *
     * @param input the Braille representation to be converted
     * @param expected the expected text representation
     */
    @ParameterizedTest
    @CsvSource({"18,h"})
    void shouldConvertText(String input, String expected) {
        try {
            String actualValue = braille.convertToText(input);
            assertEquals(expected, actualValue, "");
        } catch (BrailleModel.ModelException e) {

        }

    }
}
