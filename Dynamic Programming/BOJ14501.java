//퇴사
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1);
        int n = Integer.valueOf(br.readLine());
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];
        int[] d = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            t[i] = Integer.valueOf(line[0]);
            if (n + 1 < t[i] + i) {
                p[i] = 0;
            } else {
                p[i] = Integer.valueOf(line[1]);
            }
        }

        //역순으로 dp한다.
        d[n] = p[n];
        for (int i = n - 1; 1 <= i; i--) {
            if (t[i] + i - 1 <= n) {
                //d[]는 1부터 시작하고 t[i]+i가 n+1범위까지 가므로 d[n+2]만큼 정의한다.
                d[i] = Math.max(d[t[i] + i] + p[i], d[i + 1]);
            } else {
                d[i] = d[i + 1];
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(d[i], res);
        }

        System.out.println(res);
    }

}
