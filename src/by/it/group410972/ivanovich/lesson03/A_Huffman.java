package by.it.group410972.ivanovich.lesson03;

import java.io.InputStream;
import java.util.*;

public class A_Huffman {

    static private final Map<Character, String> codes = new TreeMap<>();

    public static void main(String[] args) {
        InputStream inputStream = A_Huffman.class.getResourceAsStream("dataA.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

    String encode(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        String s = scanner.next();

        // 1. Считаем частоты символов
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // 2. Помещаем все символы в приоритетную очередь как листья
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
        }

        // 3. Строим дерево Хаффмана
        if (priorityQueue.size() == 1) {
            // Спец. случай: только один символ
            priorityQueue.add(new LeafNode(0, '\0'));
        }
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }
        Node root = priorityQueue.poll();

        // 4. Генерируем коды
        if (root != null) {
            root.fillCodes("");
        }

        // 5. Кодируем строку
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(codes.get(c));
        }
        return sb.toString();
    }

    abstract class Node implements Comparable<Node> {
        private final int frequence;
        private Node(int frequence) { this.frequence = frequence; }
        abstract void fillCodes(String code);
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    private class InternalNode extends Node {
        Node left, right;
        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }
        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }
    }

    private class LeafNode extends Node {
        char symbol;
        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }
        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code.length() > 0 ? code : "0");
        }
    }
}
