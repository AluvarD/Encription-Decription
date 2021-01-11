package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        //String input = "we found a treasure!";
        String output = encrypt(input, key);
        System.out.println(output);
    }

    static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char temp;
        int alphabetIndex;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            alphabetIndex = alphabet.indexOf(temp);

            if (alphabetIndex >= 0 && alphabetIndex + key < 25) {
                encrypted.append(alphabet.charAt(alphabetIndex + key));
            } else if (alphabetIndex + key > 25) {
                alphabetIndex = alphabetIndex - 26;
                encrypted.append(alphabet.charAt(alphabetIndex + key));
            } else {
                encrypted.append(input.charAt(j));
            }
        }

        return String.valueOf(encrypted);
    }

}
