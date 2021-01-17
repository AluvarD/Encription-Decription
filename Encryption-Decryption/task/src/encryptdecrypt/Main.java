package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        encryptionDecryption(args);
    }

    static void encryptionDecryption(String[] params) {
        //Scanner scanner = new Scanner(System.in);
        String mode = "enc";
        String input = " ";
        String in = "";
        String out = "";
        int key = 0;
        String output;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("-mode")) {
                mode = params[i + 1];
            } else if (params[i].equals("-key")) {
                key = Integer.parseInt(params[i + 1]);
            } else if (params[i].equals("-data")) {
                input = params[i + 1];
            } else if (params[i].equals("-out")) {
                out = params[i + 1];
            } else if (params[i].equals("-in") && input.length() <= 2) {
                in = params[i + 1];
            }
        }
        switch (mode) {
            case "enc":
                if (out.length() > 0 && in.length() > 0) {
                    output = encrypt(readFile(in), key);
                    writeFile(output, out);
                } else if (out.length() > 0) {
                    output = encrypt(input, key);
                    writeFile(output, out);
                } else  if (in.length() > 0) {
                    output = encrypt(readFile(in), key);
                    System.out.println(output);
                } else {
                    output = encrypt(input, key);
                    System.out.println(output);
                }
                break;
            case "dec":
                if (out.length() > 0 && in.length() > 0) {
                    output = decrypt(readFile(in), key);
                    writeFile(output, out);
                } else if (out.length() > 0) {
                    output = decrypt(input, key);
                    writeFile(output, out);
                } else  if (in.length() > 0) {
                    output = decrypt(readFile(in), key);
                    System.out.println(output);
                } else {
                    output = decrypt(input, key);
                    System.out.println(output);
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    static String readFile (String fileName) {
        String path = "./" + fileName;
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            return "Error";
        }
    }

    static void writeFile (String data, String fileName) {
        String path = "./" + fileName;
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error");
        }
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
