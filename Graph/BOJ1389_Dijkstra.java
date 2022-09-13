package ss;
import java.util.*;
import java.io.*;
//케빈베이컨의 6단계 법칙 
public class BOJ1389_Dijkstra {
	static int[][] arr;
	static boolean[] visit;
	
	static int N,M;
	static int[][] dp;
	static int[][] map;
	static Queue<Integer> q = new LinkedList<>();
	static int min;
	static int min_count = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N+1][N+1];
		map = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		for(int i=N;i>=1;i--) {
			dijkstra(i);//모든 정점에 대해 다 수행해봐야함.
		}
		System.out.println(min);
	}
	
	static void dijkstra(int start) {
		dp[start][start] = 0;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i=1;i<=N;i++) {
				if(map[cur][i] == 0 || dp[start][i] != -1) continue;
				
				dp[start][i] = dp[start][cur]+1;
				q.add(i);
			}
		}
		int sum = 0;
		for(int i=1;i<=N;i++)
			sum += dp[start][i];
		
		if(min_count >= sum) {
			min_count = sum;
			min = start;
		}
	}
	
}

//			System.out.println("u: "+u+"v: "+v);
//System.out.print("st: "+st);