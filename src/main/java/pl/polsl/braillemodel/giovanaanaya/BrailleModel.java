/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.braillemodel.giovanaanaya;

import java.util.Map;
import java.util.Optional;

/**
 * The BrailleModel class represents the model in the Braille Converter
 * application. It handles the conversion of a word to Braille Code.
 *
 * @author Giovana anaya
 * @version 1.0
 */
public class BrailleModel {

    /**
     * Gets the Braille representation of a given letter.
     *
     * @param text will contain the user input
     * @return The Braille representation of the input letter.
     * @throws ModelException If an error occurs during the conversion.
     */
    public String convertTextToBraille(String text) throws ModelException {
        if (text.isEmpty()) {
            throw new ModelException("Input can't be empty.");
        }

        String brailleRepresentation = "";
        Map<Integer, Character> brailleMap = BrailleMap.getBrailleMap();
        var counter = 0;
        for (char c : text.toCharArray()) {
            for (Map.Entry<Integer, Character> entry : brailleMap.entrySet()) {
                if (entry.getValue().equals(text.charAt(counter))) {
                    brailleRepresentation = brailleRepresentation + String.valueOf(entry.getKey());
                    break;
                }
            }
            counter++;
        }
        if (brailleRepresentation.isEmpty()) {
            throw new ModelException("Invalid character.");
        }
        return brailleRepresentation;
    }

    /**
     * Converts Braille code to text.
     *
     * @param brailleCode The Braille code that the user input to be converted.
     * @return The corresponding text representation, which would be the output.
     * @throws ModelException If an error occurs during the conversion process.
     */
    public String convertToText(String brailleCode) throws ModelException {
        StringBuilder result = new StringBuilder();
        Map<Integer, Character> brailleMap = BrailleMap.getBrailleMap();

        if (brailleCode.length() % 2 == 0) {
            for (int i = 0; i < brailleCode.length(); i += 2) {
                String currentBraillePair = brailleCode.substring(i, i + 2);

                Optional<Map.Entry<Integer, Character>> brailleEntry = brailleMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().equals(Integer.parseInt(currentBraillePair)))
                        .findFirst();

                if (brailleEntry.isPresent()) {
                    result.append(brailleEntry.get().getValue());
                } else {
                    throw new ModelException("Invalid Braille code: " + currentBraillePair);
                }
            }
        } else {
            throw new ModelException("The input must have an even lenght.");
        }
        return result.toString();
    }

    /**
     * Custom exception class for handling model-related exceptions. It is used
     * to indicate errors or exceptional conditions in the model.
     */
    public class ModelException extends Exception {

        /**
         * Constructs a new ModelException with the specified detail message.
         *
         * @param message The message that will be shown to the user.
         */
        public ModelException(String message) {
            super(message);
        }
    }

}
