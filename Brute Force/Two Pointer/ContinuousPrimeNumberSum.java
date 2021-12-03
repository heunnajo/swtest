import java.util.*;
import java.io.*;

public class ContinuousPrimeNumberSum {
    
    static int MAX = 4000000;
    static boolean[] array = new boolean[MAX + 1];
    static int[] prime = new int[283146 + 1];
    static int cnt;
    public static void main(String args[]) throws Exception{
        solve();
    }

private static void solve() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int ans = 0;
    int sum = 0;
 
    calPrime();
    setPrime();
 
    int s = 0, e = 0;
    while (e <= cnt) {
        if (sum < n) {
            sum += prime[e++];
            continue;
        }
 
        if (sum == n) {
            ++ans;
        }
 
        sum -= prime[s++];
    }
 
    System.out.println(ans);
}
 
public static void calPrime() {
    int sqrt = (int) Math.sqrt(MAX);
    for (int i = 2; i <= sqrt; i++) {
        if (array[i]) {
            continue;
        }
 
        for (int j = i + i; j <= MAX; j += i) {
            array[j] = true;
        }
    }
}
 
public static void setPrime() {
    for (int i = 2; i <= MAX; i++) {
        if (!array[i]) {
            prime[cnt++] = i;
        }
    }
}
}