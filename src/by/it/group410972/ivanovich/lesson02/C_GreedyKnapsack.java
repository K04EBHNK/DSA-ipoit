package by.it.group410972.ivanovich.lesson02;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d ms)\n", costFinal, finishTime - startTime);
    }

    double calc(InputStream inputStream) {
        Scanner input = new Scanner(inputStream);
        int n = input.nextInt();      // количество предметов
        int W = input.nextInt();      // объем рюкзака

        System.out.println("Количество предметов: " + n);
        System.out.println("Объем рюкзака: " + W);

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            int cost = input.nextInt();
            int weight = input.nextInt();
            items[i] = new Item(cost, weight);
        }


        Arrays.sort(items);

        double result = 0;
        int weightLeft = W;

        for (Item item : items) {
            if (weightLeft == 0) break;

            if (item.weight <= weightLeft) {

                result += item.cost;
                weightLeft -= item.weight;
            } else {

                double fraction = (double) weightLeft / item.weight;
                result += item.cost * fraction;
                weightLeft = 0;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        double valuePerWeight() {
            return (double) cost / weight;
        }

        @Override
        public int compareTo(Item o) {

            return Double.compare(o.valuePerWeight(), this.valuePerWeight());
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }
    }
}
