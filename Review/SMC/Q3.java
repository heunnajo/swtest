package boj;
import java.io.*;
import java.util.*;
public class SCM_Q3 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int[][] W = new int[C][C];
        for(int i=0;i<C;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<C;j++) {
        		W[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[] personaA = new int[N];//2
        int[] personaB = new int[M];//3
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
        	personaA[i] = Integer.parseInt(st.nextToken())-1;
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
        	personaB[i] = Integer.parseInt(st.nextToken())-1;
        }
        
        int[][] dp = new int[1000][1000];
        int limit = N < M ? N : M;//2
        for(int i=0;i<limit;i++) {//2까지
        	dp[i][i] = W[personaA[i]][personaB[i]];
        	dp[i][0] = W[personaA[i]][0];
        }
        
        for(int i=2;i<1000;i++) {
        	for(int j=1;j<1000;j++) {
        		dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
        	}
        }
        
        System.out.println(dp[N][M]);
	}

}
