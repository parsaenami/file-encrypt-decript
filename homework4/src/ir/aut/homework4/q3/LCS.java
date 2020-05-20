package ir.aut.homework4.q3;

import java.io.*;

public class LCS {
    /**
     * this method gives 2 files and
     * reads their content
     * compares them
     * finds their common largest substring
     * @param filename1 is first string
     * @param filename2 is second string
     * @return string of the largest common substring of those strings
     * @throws Exception checks if those files are openable or not
     */
    public String longestCommonSubsequence(String filename1, String filename2) throws Exception {
        /**
         * input1 is the string of first file's content
         * input2 is the string of second file's content
         */
        String input1 = getString(filename1);
        String input2 = getString(filename2);
        return findLcs(input1, input2);
    }

    /**
     * this method opens a text file first
     * then read its content and put them all in a string
     *
     * @param filename is the source file
     * @return the string of file's content
     */
    public String getString(String filename) {
        /**
         * the primary string that is going to be returned
         */
        String input = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                input = line;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return input;
    }

    /**
     * this method compares 2 strings and find largest common substring of them
     * @param s1 is first string
     * @param s2 is second string
     * @return string of the largest common substring of s1 & s2
     */
    public String findLcs(String s1, String s2) {
        /**
         * the primary string that is going to be returned
         */
        String output = "1";
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = i; j <= s1.length(); j++) {
                if (s2.contains(s1.substring(i, j))) {
                    if (s1.substring(i, j).length() > output.length()){
                        output = s1.substring(i, j);
                    }
                }
            }
        }
        return output;
    }

    /**
     * this method writes a string to a file
     * i.e. saves a string as a file
     * @param str is the source string
     * @throws IOException checks if file is openable or not
     */
    public void saveToFile(String str) throws IOException {
        FileWriter writer = new FileWriter("file_3.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(str);
        bufferedWriter.close();
    }
}
