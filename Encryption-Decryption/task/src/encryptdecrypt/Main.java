package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();
        String input = "we found a treasure!";
        String output = encrypt(input);
        System.out.println(output);
    }

    //first version
    /*static String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetRevers = "zyxwvutsrqponmlkjihgfedcba";
        String[] inputArray = input.split(" ");
        char temp;
        int alphabetIndex;

        for (String s : inputArray) {
            for (int j = 0; j < s.length(); j++) {
                temp = s.charAt(j);
                alphabetIndex = alphabet.indexOf(temp);
                if (alphabetIndex >= 0) {
                    encrypted.append(alphabetRevers.charAt(alphabetIndex));
                } else {
                    encrypted.append(s.charAt(j));
                }
            }
            encrypted.append(" ");
        }

        return String.valueOf(encrypted);
    }*/

    static String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetRevers = "zyxwvutsrqponmlkjihgfedcba";
        char temp;
        int alphabetIndex;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            alphabetIndex = alphabet.indexOf(temp);
            if (alphabetIndex >= 0) {
                encrypted.append(alphabetRevers.charAt(alphabetIndex));
            } else {
                encrypted.append(input.charAt(j));
            }
        }

        return String.valueOf(encrypted);
    }
}
