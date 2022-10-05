package swea;
import java.util.*;
import java.io.*;
//최장 경로

public class Q2814_Solution {
	static int TC,N,M,ans;
	static int[][] arr;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		ans = 1;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=TC;t++) {
			st = new StringTokenizer(br.readLine());
			ans = Integer.MIN_VALUE;
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N+1][N+1];
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				arr[u][v] = arr[v][u] = 1;
			}
			
			for(int i=1;i<=N;i++) {
				visited = new boolean[N+1];
				visited[i] = true;
				dfs(i,1);//i번 정점에서 경로 길이 1로 시작
			}
			//그냥 애초에 ans값을 1로 하면 하나의 조건문으로 정답 도출 가능하지 않나.
			if(ans == Integer.MIN_VALUE) sb.append("#").append(t).append(" ").append(1).append("\n");
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	
	static void dfs(int st,int cnt) {
		
		for(int i=1;i<=N;i++) {
			if(st == i) continue;//
			if(visited[i]) continue;
			if(arr[st][i] == 1) {
				visited[i] = true;
				dfs(i,cnt+1);
				visited[i] = false;//
			}
		}
		ans = ans < cnt ? cnt :ans;
	}
	/*
	static void dfs(int st,int cnt) {
		visited[st] = true;
		
		for(int i=1;i<=N;i++) {
			if(st == i) continue;//
			if(arr[st][i] == 1 && !visited[i]) {
				dfs(i,cnt+1);
				visited[i] = false;//
			}
		}
		ans = ans < cnt ? cnt :ans;
	}
	 */
}
