package ir.aut.homework4.q2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class CharDictionary {
    /**
     * this method opens a text file first
     * then read its content and put them all in a string
     *
     * @throws Exception checks if those files are openable or not
     * @returnthe string of file's content
     */
    public String readFromFile() throws Exception {
        /**
         * the primary string that is going to be returned
         */
        String input = null;
        try {
            FileReader fileReader = new FileReader("input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                input = line.toLowerCase();
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return input;
    }

    /**
     * determines how many times had each letter repeated in input string
     *
     * @param input is the source string
     * @return a hash map with character keys and repeating time values
     */
    public HashMap displayFrequency(String input) {
        /**
         * a variable to keep input string's length
         */
        int len = input.length();
        /**
         * a Hash map to keep each letter and its repeating number as keys and values
         */
        HashMap<Character, Integer> stringLetters = new HashMap<>(Math.min(len, 26));

        for (int i = 0; i < len; ++i) {
            /**
             * the letter that have been read
             */
            char letter = input.charAt(i);
            if (!stringLetters.containsKey(letter)) {
                stringLetters.put(letter, 1);
            } else {
                stringLetters.put(letter, stringLetters.get(letter) + 1);
            }
        }
        return stringLetters;
    }

    /**
     * prints hash map content
     * @param map the hash map that we want to print its content
     */
    public void showHashMap(HashMap map) {
        System.out.println(map);
    }

    /**
     * save a hash map's content to a file
     * @param map the hash map that we want to save its content in a file
     * @throws Exception checks if those files are openable or not
     */
    public void saveFrequency(HashMap map) throws Exception {
        FileWriter writer = new FileWriter("output_2.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(String.valueOf(map));
        bufferedWriter.close();
    }
}
