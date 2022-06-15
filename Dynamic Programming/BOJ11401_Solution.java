//BOJ#11401 이항 게수3
class BOJ11401_Solution {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int N = read();
        int K = read();
        int c = N - K;
        int offsetA = c > K ? c : K;
        int offsetB = c < K ? c : K;
        long numerator = fact(offsetA, N);
        long denominator = fact(0, offsetB);
        System.out.print(numerator * pow(denominator, MOD - 2) % MOD);
    }

    static long fact(int s, int e) {
        long result = 1L;
        for (int i = s + 1; i <= e; i++) {
            result *= i;
            result %= MOD;
        }
        return result;
    }

    static long pow(long num, int exp) {
        long result = 1L;
        do {
            if ((exp & 1) == 1) {
                result *= num;
                result %= MOD;
            }
            num *= num;
            num %= MOD;
        } while ((exp >>= 1) > 0);
        return result;
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}