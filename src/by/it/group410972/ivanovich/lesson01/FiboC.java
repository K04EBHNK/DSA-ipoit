package by.it.group410972.ivanovich.lesson01;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d, %d)=%d \n\t time=%d ms\n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }


    private int pisanoPeriod(int m) {
        int prev = 0;
        int curr = 1;
        for (int i = 0; i < m * 6; i++) {
            int temp = (prev + curr) % m;
            prev = curr;
            curr = temp;

            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }
        return 0;
    }

    long fasterC(long n, int m) {
        if (n <= 1) return n % m;

        int pisano = pisanoPeriod(m);
        long n_mod = n % pisano;

        if (n_mod <= 1) return n_mod;

        long prev = 0;
        long curr = 1;
        for (long i = 2; i <= n_mod; i++) {
            long temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}
