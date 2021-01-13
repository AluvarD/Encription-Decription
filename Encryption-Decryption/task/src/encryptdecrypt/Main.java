package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        encryptionDecryption();
    }

    static void encryptionDecryption() {
        Scanner scanner = new Scanner(System.in);
        String mode = scanner.nextLine();
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        String output;

        switch (mode) {
            case "enc":
                output = encrypt(input, key);
                break;
            case "dec":
                output = decrypt(input, key);
                break;
            default:
                output = "error";
                break;
        }

        System.out.println(output);
    }

    static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        int temp;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            temp += key;
            encrypted.append((char) temp);
        }

        return String.valueOf(encrypted);
    }

    static String decrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        int temp;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            temp -= key;
            encrypted.append((char) temp);
        }

        return String.valueOf(encrypted);
    }

}
