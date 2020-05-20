package ir.aut.homework4;

import ir.aut.homework4.q1.SumSquares;
import ir.aut.homework4.q2.CharDictionary;
import ir.aut.homework4.q3.LCS;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        menu();
    }

    private static void menu() throws Exception{
        int choice;
        System.out.println("please select a homework to check out:");
        Scanner choose = new Scanner(System.in);
        choice = choose.nextInt();
        switch (choice) {
            case 1:
                q1();
                faultAndContinue(choose);
                break;
            case 2:
                q2();
                faultAndContinue(choose);
                break;
            case 3:
                q3();
                faultAndContinue(choose);
                break;
            default:
                System.out.println("wrong choice!\nenter a number again:\n");
                menu();
                break;
        }
    }

    private static void faultAndContinue(Scanner choose) throws Exception{
        System.out.println("do you want to choose another homework? (Y/N)");
        char choice2 = choose.next(".").charAt(0);
        if (choice2 == 'Y')
            menu();
        else if (choice2 == 'N') ;
        else {
            System.out.println("wrong choice!\nenter again:");
            faultAndContinue(choose);
        }
    }

    public static void q1() {
        SumSquares squares = new SumSquares();
        Scanner s = new Scanner(System.in);
        System.out.println("how many numbers do you want to enter?");
        int m = s.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println("enter a number:");
            squares.setNumList(s.nextInt());
        }
        squares.writeOutSquare(squares.getNumList());
    }

    public static void q2() throws Exception{
        CharDictionary dictionary = new CharDictionary();
        dictionary.showHashMap(dictionary.displayFrequency(dictionary.readFromFile()));
        dictionary.saveFrequency(dictionary.displayFrequency(dictionary.readFromFile()));
    }

    public static void q3() throws Exception {
        LCS lcs = new LCS();
        System.out.println(lcs.longestCommonSubsequence("file_1.txt", "file_2.txt"));
        lcs.saveToFile(lcs.longestCommonSubsequence("file_1.txt", "file_2.txt"));
    }
}
