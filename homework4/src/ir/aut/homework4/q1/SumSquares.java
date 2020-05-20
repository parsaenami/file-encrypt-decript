package ir.aut.homework4.q1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SumSquares {
    /**
     * an array list to keep input numbers
     */
    private ArrayList<Integer> numList = new ArrayList<>();
    /**
     * number of initialized array list blocks
     */
    private int numCounter = 0;

    public void setNumList(int add) {
        this.numList.add(add);
        numCounter++;
    }

    public ArrayList<Integer> getNumList() {
        return numList;
    }

    /**
     * first of all this method squares each number
     * then write them into the file
     *
     * @param numList is the source array list
     */
    public void writeOutSquare(ArrayList<Integer> numList) {
        /**
         * keeps square of a number
         */
        int square;
        /**
         * keeps summation of all squared numbers
         */
        int sum = 0;
        try {
            FileWriter writer = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int i = 0; i < numCounter; i++) {
                square = numList.get(i) * numList.get(i);
                if (i == 0)
                    bufferedWriter.write("squared numbers : ");
                bufferedWriter.write(square + " ");
                sum += square;
            }
            bufferedWriter.write("\n");
            bufferedWriter.write("summation of them : " + sum);
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
