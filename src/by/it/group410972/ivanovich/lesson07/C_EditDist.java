package by.it.group410972.ivanovich.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();
        int[][] dp = new int[n + 1][m + 1];

        char[][] op = new char[n + 1][m + 1];


        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
            op[i][0] = '-';
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
            op[0][j] = '+';
        }
        op[0][0] = '#';

        // Основной DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    op[i][j] = '#';
                } else {
                    int del = dp[i - 1][j] + 1;
                    int ins = dp[i][j - 1] + 1;
                    int rep = dp[i - 1][j - 1] + 1;
                    int min = Math.min(Math.min(del, ins), rep);
                    dp[i][j] = min;
                    if (min == rep) op[i][j] = '~';
                    else if (min == del) op[i][j] = '-';
                    else op[i][j] = '+';
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        int i = n, j = m;
        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && op[i][j] == '#') {
                sb.insert(0, "#,");
                i--; j--;
            } else if (i > 0 && j > 0 && op[i][j] == '~') {
                sb.insert(0, "~" + two.charAt(j - 1) + ",");
                i--; j--;
            } else if (j > 0 && op[i][j] == '+') {
                sb.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else if (i > 0 && op[i][j] == '-') {
                sb.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
