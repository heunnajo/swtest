package ss;

import java.util.*;
import java.io.*;

public class BOJ1260 {
	static int N,M,Start;
	static ArrayList<Integer>[] adj;
	static LinkedList<Integer> orderDFS;
	static LinkedList<Integer> orderBFS;
	static boolean[] visitedDFS;
	static boolean[] visitedBFS;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Start = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		
		//N개의 정점 방문하는 순서 저장
		orderDFS = new LinkedList<>();
		orderBFS = new LinkedList<>();
		
		visitedDFS = new boolean[N+1];//방문 체크 : 인덱스 = 정점번호 : 1부터 시작하므로 N+1 크기로.
		visitedBFS = new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {//M개의 간선 정보 저장
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u].add(v); adj[v].add(u);
		}
		
		//정점번호 작은 것부터 방문하기 위해 오름차순 정렬~!
		for(int i=1;i<=N;i++) {
			Collections.sort(adj[i]);
		}
		
		dfs(Start);
		bfs();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<orderDFS.size();i++) {
			sb.append(orderDFS.get(i)).append(" ");
		}
		sb.append("\n");
		
		for(int i=0;i<orderBFS.size();i++) {
			sb.append(orderBFS.get(i)).append(" ");
		}
		sb.append("\n");
		
		System.out.println(sb.toString());
		
	}
	static void dfs(int cur) {
		visitedDFS[cur] = true;
		orderDFS.add(cur);
		
		for(int next:adj[cur]) {
			if(visitedDFS[next]) continue;
			dfs(next);
		}
	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		//시작 정점 방문 체크 처리!
		q.offer(Start);
		visitedBFS[Start] = true;
		orderBFS.add(Start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next:adj[cur]) {
				if(visitedBFS[next]) continue;
				
				q.offer(next);
				visitedBFS[next] = true;
				orderBFS.add(next);
			}
		}
	}
}
