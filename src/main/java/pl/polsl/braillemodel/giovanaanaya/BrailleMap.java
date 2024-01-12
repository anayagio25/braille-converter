/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.braillemodel.giovanaanaya;
import java.util.HashMap;
import java.util.Map; 
/**
 *The BrailleMap class is the conversion table in the Braille Converter application.
 * It has the necessary values to convert the user's input, and will be referenced from the model.
 * 
 * @author Giovana Anaya
 * @version 1.0
 */
public class BrailleMap {
    /**
     * It creates a HashMap that contains the values to do the conversion of each letter.
     * 
     * @return the conversion of each character 
     */
    public static Map<Integer, Character> getBrailleMap() {
        
       Map<Integer, Character> brailleMap = new HashMap<>();
        brailleMap.put(11, 'a');// To test the program use a as an example
        brailleMap.put(12, 'b');
        brailleMap.put(13, 'c');
        brailleMap.put(14, 'd');
        brailleMap.put(15, 'e');
        brailleMap.put(16, 'f');
        brailleMap.put(17, 'g');
        brailleMap.put(18, 'h');
        brailleMap.put(19, 'i');
        brailleMap.put(20, 'j');
        brailleMap.put(21, 'k');
        brailleMap.put(22, 'l');
        brailleMap.put(23, 'm');
        brailleMap.put(24, 'n');
        brailleMap.put(25, 'o');
        brailleMap.put(26, 'p');
        brailleMap.put(27, 'q');
        brailleMap.put(28, 'r');
        brailleMap.put(29, 's');
        brailleMap.put(30, 't');
        brailleMap.put(31, 'u');
        brailleMap.put(32, 'v');
        brailleMap.put(33, 'w');
        brailleMap.put(34, 'x');
        brailleMap.put(35, 'y');
        brailleMap.put(36, 'z');
        brailleMap.put(37,' ');
        return brailleMap;
    }
}
