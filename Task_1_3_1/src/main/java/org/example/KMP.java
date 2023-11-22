package org.example;

import javax.xml.crypto.URIReferenceException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Some text.
 */
public class KMP {
    private KMP() {

    }
    /**
     * Some text.
     *
     * @param fileName  - Some text.
     * @param pattern   - Some text.
     * @return          - Some text.
     */
    public static ArrayList<Integer> KMPSearch(String fileName, String pattern) {
        int lengthOfPattern = pattern.length();
        int[] lps = new int[lengthOfPattern];
        computeLPSArray(pattern, lps);

        int lengthOfBuffer = lengthOfPattern * 10;
        char[] buffer = new char[lengthOfBuffer];

        int allCharactersProcessed = 0;


        int offsetInFile = 0;

        ArrayList<Integer> indexesOfEntry = new ArrayList<>();

        KMP test = new KMP();
        ClassLoader classLoader = test.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }


        File fileDirectory = null;
        try {
            fileDirectory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDirectory), StandardCharsets.UTF_8));){

            while (true) {
                int numberOfCharactersRead =
                        br.read(buffer, offsetInFile, lengthOfBuffer - offsetInFile);

                int i = 0; // индекс для buffer
                int j = 0; // индекс для pattern
                offsetInFile = lengthOfPattern - 1;
                if (numberOfCharactersRead != -1) {
                    while ((lengthOfBuffer - i) >= (lengthOfPattern - j)
                            && i <= numberOfCharactersRead + offsetInFile - 1) {
                        if (pattern.charAt(j) == buffer[i]) {
                            i++;
                            j++;
                        }
                        if (j == lengthOfPattern) {
                            indexesOfEntry.add(i - j + allCharactersProcessed);
                            j = lps[j - 1];
                        } else if (i < lengthOfBuffer && pattern.charAt(j) != buffer[i]) {
                            if (j != 0) {
                                j = lps[j - 1];
                            } else {
                                i++;
                            }
                        }
                    }

                    for (int k = 0; k < offsetInFile; k++) {
                        int bufferIndex = lengthOfBuffer - offsetInFile + k;
                        buffer[k] = buffer[bufferIndex];
                    }
                    allCharactersProcessed += lengthOfBuffer - offsetInFile;
                } else {
                    return indexesOfEntry;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void computeLPSArray(String pattern, int[] lps) {
        int lengthPreviousLongestPrefix = 0;
        int index = 1;
        //lps - the longest proper prefix which is also a suffix
        //lps[0] всегда 0
        lps[0] = 0;

        int subStringLength = pattern.length();
        // цикл вычисляет lps от 1 до
        while (index < subStringLength) {
            //случай, когда префикс совпадает
            if (pattern.charAt(index) == pattern.charAt(lengthPreviousLongestPrefix)) {
                lengthPreviousLongestPrefix++;
                lps[index] = lengthPreviousLongestPrefix;
                index++;
            } else {
                // (pattern[i] != pattern[len])

                // обработка подобных случаев
                // AAACAAAA i = 7
                if (lengthPreviousLongestPrefix != 0) {
                    lengthPreviousLongestPrefix = lps[lengthPreviousLongestPrefix - 1];
                } else {
                    // lengthPreviousLongestPrefix == 0
                    lps[index] = lengthPreviousLongestPrefix;
                    index++;
                }
            }
        }
    }


    /**
     * Some text.
     *
     * @param args                          - Some text.
     * @throws UnsupportedEncodingException - Some text.
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String fileName = "test.txt";
        String pattern = new String("бра".getBytes(), StandardCharsets.UTF_8);
        String myString = "бра";
        System.out.println(KMPSearch(fileName, pattern));
    }
}