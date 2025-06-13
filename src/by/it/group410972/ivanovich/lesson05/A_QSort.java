package by.it.group410972.ivanovich.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_QSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_QSort.class.getResourceAsStream("dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];


        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }


        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }


        quickSort(segments, 0, n - 1);


        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop) {
                    count++;
                }
            }
            result[i] = count;
        }

        return result;
    }


    private void quickSort(Segment[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Segment[] arr, int low, int high) {
        Segment pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                Segment temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }


        Segment temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {

            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {

            int startComparison = Integer.compare(this.start, o.start);
            if (startComparison != 0) {
                return startComparison;
            }
            return Integer.compare(this.stop, o.stop);
        }
    }
}
