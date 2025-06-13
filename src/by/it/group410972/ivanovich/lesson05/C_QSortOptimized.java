package by.it.group410972.ivanovich.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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


        quickSort3Way(segments, 0, n - 1);


        for (int i = 0; i < m; i++) {
            int point = points[i];
            int count = 0;


            int right = binarySearchFirstGreater(segments, point);


            for (int j = 0; j < right; j++) {
                if (segments[j].stop >= point) {
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }


    private void quickSort3Way(Segment[] arr, int lo, int hi) {
        while (hi > lo) {
            int lt = lo, gt = hi;
            Segment pivot = arr[lo];
            int i = lo + 1;


            while (i <= gt) {
                int cmp = arr[i].compareTo(pivot);
                if (cmp < 0) {
                    swap(arr, lt++, i++);
                } else if (cmp > 0) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }




            if (lt - lo < hi - gt) {
                quickSort3Way(arr, lo, lt - 1);
                lo = gt + 1;
            } else {
                quickSort3Way(arr, gt + 1, hi);
                hi = lt - 1;
            }
        }
    }


    private int binarySearchFirstGreater(Segment[] segments, int target) {
        int left = 0, right = segments.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (segments[mid].start <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            Segment other = (Segment) o;
            return Integer.compare(this.start, other.start);
        }
    }
}
