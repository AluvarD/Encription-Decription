package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //args = new String[]{"-mode", "enc", "-key", "5", "-data", "Welcome to hyperskill!", "-alg", "shift"};
        //args = new String[]{"-mode", "dec", "-key", "5", "-data", "Bjqhtrj yt mdujwxpnqq!", "-alg", "shift"};
        //args = new String[]{"-mode", "enc", "-key", "5", "-data", "Welcome to hyperskill!", "-alg", "unicode"};
        //args = new String[]{"-mode", "dec", "-key", "5", "-data", "\\jqhtrj%yt%m~ujwxpnqq&", "-alg", "unicode"};
        encryptionDecryption(args);
    }

    static void encryptionDecryption(String[] params) {
        //Scanner scanner = new Scanner(System.in);
        String mode = "enc";
        String input = " ";
        String in = "";
        String out = "";
        String alg = "shift";
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
            } else if (params[i].equals("-alg")) {
                alg = params[i + 1];
            }
        }
        switch (mode) {
            case "enc":
                if (out.length() > 0 && in.length() > 0) {
                    output = encrypt(readFile(in), key, alg);
                    writeFile(output, out);
                } else if (out.length() > 0) {
                    output = encrypt(input, key, alg);
                    writeFile(output, out);
                } else if (in.length() > 0) {
                    output = encrypt(readFile(in), key, alg);
                    System.out.println(output);
                } else {
                    output = encrypt(input, key, alg);
                    System.out.println(output);
                }
                break;
            case "dec":
                if (out.length() > 0 && in.length() > 0) {
                    output = decrypt(readFile(in), key, alg);
                    writeFile(output, out);
                } else if (out.length() > 0) {
                    output = decrypt(input, key, alg);
                    writeFile(output, out);
                } else if (in.length() > 0) {
                    output = decrypt(readFile(in), key, alg);
                    System.out.println(output);
                } else {
                    output = decrypt(input, key, alg);
                    System.out.println(output);
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    static String encrypt(String input, int key, String alg) {
        switch (alg) {
            case "unicode":
                return unicodeEnc(input, key);
            case "shift":
                return shiftEnc(input, key);
            default:
                return "Error";
        }
    }

    static String unicodeEnc (String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        int temp;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            temp += key;
            encrypted.append((char) temp);
        }
        return String.valueOf(encrypted);
    }

    static String shiftEnc (String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char temp;
        int alphabetIndex;
        int alphabetIndexBig;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            alphabetIndex = alphabet.indexOf(temp);
            alphabetIndexBig = alphabetBig.indexOf(temp);
            if (alphabetIndex >= 0 && alphabetIndex + key < 25) {
                encrypted.append(alphabet.charAt(alphabetIndex + key));
            } else if (alphabetIndex + key > 25) {
                alphabetIndex = alphabetIndex - 26;
                encrypted.append(alphabet.charAt(alphabetIndex + key));
            } else if (alphabetIndexBig >= 0 && alphabetIndexBig + key < 25) {
                encrypted.append(alphabetBig.charAt(alphabetIndexBig + key));
            } else if (alphabetIndexBig + key > 25) {
                alphabetIndexBig = alphabetIndexBig - 26;
                encrypted.append(alphabetBig.charAt(alphabetIndexBig + key));
            } else {
                encrypted.append(input.charAt(j));
            }
        }
        return String.valueOf(encrypted);
    }

    static String decrypt(String input, int key, String alg) {
        switch (alg) {
            case "unicode":
                return unicodeDec(input, key);
            case "shift":
                return shiftDec(input, key);
            default:
                return "Error";
        }
    }

    static String unicodeDec (String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        int temp;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            temp -= key;
            encrypted.append((char) temp);
        }
        return String.valueOf(encrypted);
    }

    static String shiftDec (String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char temp;
        int alphabetIndex;
        int alphabetIndexBig;
        for (int j = 0; j < input.length(); j++) {
            temp = input.charAt(j);
            alphabetIndex = alphabet.indexOf(temp);
            alphabetIndexBig = alphabetBig.indexOf(temp);
            if (alphabetIndex - key >= 0 && alphabetIndex < 25) {
                encrypted.append(alphabet.charAt(alphabetIndex - key));
            } else if (alphabetIndex - key < 0) {
                alphabetIndex = alphabetIndex + 26;
                encrypted.append(alphabet.charAt(alphabetIndex - key));
            } else if (alphabetIndexBig - key >= 0 && alphabetIndexBig < 25) {
                encrypted.append(alphabetBig.charAt(alphabetIndexBig - key));
            } else if (alphabetIndexBig - key < 0) {
                alphabetIndexBig = alphabetIndexBig + 26;
                encrypted.append(alphabetBig.charAt(alphabetIndexBig - key));
            } else {
                encrypted.append(input.charAt(j));
            }
        }
        return String.valueOf(encrypted);
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

}
