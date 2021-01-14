package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        encryptionDecryption(args);
    }

    static void encryptionDecryption(String[] params) {
        //Scanner scanner = new Scanner(System.in);
        String mode = "enc";
        String input = " ";
        int key = 0;
        String output;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("-mode")) {
                mode = params[i + 1];
            } else if (params[i].equals("-key")) {
                key = Integer.parseInt(params[i + 1]);
            } else if (params[i].equals("-data")) {
                input = params[i + 1];
            }
        }
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
