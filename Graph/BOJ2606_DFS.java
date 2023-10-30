//바이러스
import java.util.*;
import java.io.*;
public class BOJ2606_DFS {
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u].add(v); adj[v].add(u);
		}
		//1번에서 시작해서 탐색, 인접 정점 갯수 카운팅!
		cnt = 0;
		visited = new boolean[N+1];
		visited[1] = true;
		for(int x:adj[1]) {
			if(!visited[x]) {
				dfs(x);
			}
		}
		System.out.println(cnt);
		
	}
	static void dfs(int x) {
		if(visited[x]) return;
		visited[x] = true;
		cnt++;
		
		//x의 인접 정점 탐색!
		for(int nxt:adj[x]) {
			if(!visited[nxt]) {
				dfs(nxt);
			}
		}
	}

}
