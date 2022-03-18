//BOJ #9084 동전
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	StringTokenizer st = null;
    	StringBuilder sb = new StringBuilder();
    	
        for (int tc = 1; tc <= TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            
            int[] coin = new int[N];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
            	coin[i] = Integer.parseInt(st.nextToken());
            }
            
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = coin[i]; j <= M; j++) {
                    dp[j] += dp[j - coin[i]];
                }
            }
            sb.append(dp[M]+"\n");
        }
        System.out.print(sb);
    }
}