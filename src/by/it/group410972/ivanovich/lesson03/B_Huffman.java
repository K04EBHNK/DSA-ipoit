package by.it.group410972.ivanovich.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;



public class B_Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine();


        Map<String, Character> codeMap = new HashMap<>();


        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(": ");
            char letter = parts[0].charAt(0);
            String code = parts[1];
            codeMap.put(code, letter);
        }


        String encodedString = scanner.nextLine();


        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < encodedString.length(); i++) {
            currentCode.append(encodedString.charAt(i));
            if (codeMap.containsKey(currentCode.toString())) {
                result.append(codeMap.get(currentCode.toString()));
                currentCode = new StringBuilder();
            }
        }

        return result.toString();
    }
}
