package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StringSearch {

    public static void main(String[] args) {
        String subString = "abcabd";
        int[] jump = calculationJumpTable(subString);
        for (int i = 0; i < jump.length - 1; i++) {
            System.out.printf("%c - %d\n", subString.charAt(i), jump[i]);
        }
        stringSearch("test1.txt", "asd");

    }

    public static List<Integer> stringSearch(String inputFileName,String str) {
        String inputString;
        try {
            File input = new File(inputFileName);
            FileReader reader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(reader);
            inputString = bufferedReader.readLine();
            System.out.println(inputString);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }

        int[] jumpTable;
        int j = 0;

        int strLength = str.length();
        int inputLength = inputString.length();











        return null;
    }

    private static int[] calculationJumpTable (String str) {
        int[] jumpTable = new int[str.length() + 1];
        int k = 0;
        int j = 1;

        while(j < str.length()) {
            while (k > 0 && str.charAt(k) != str.charAt(j)) {
                k = jumpTable[k];
            }

            if (str.charAt(k) == str.charAt(j)) {
                k++;
            }

            jumpTable[j + 1] = k;
            j++;
        }
        return jumpTable;
    }


}


