package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//특정 거리의 도시 찾기
public class BOJ18352_Dijkstra {
	static int N,M,K,X;
	static int[] dp;
	static boolean[] v;
	static ArrayList<Node>[] adj;
	
	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int next,dist;
		
		Node(int next,int dist){
			this.next = next;
			this.dist = dist;
		}
		
		public int compareTo(Node n) {
			return this.dist - n.dist;//a-b 오름차순 정렬!
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//도시 갯수
		M = Integer.parseInt(st.nextToken());//도로 갯수
		K = Integer.parseInt(st.nextToken());//특정 거리
		X = Integer.parseInt(st.nextToken());//시작 정점 번호
		dp = new int[N+1];//dp[i] : X->i 거리
		v = new boolean[N+1];//방문 여부
		
		Arrays.fill(dp, INF);
		
		adj = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		int u,v;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Node(v,1));
			
		}
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			if(dp[i] == K) sb.append(i).append("\n");
		}
		
		if(sb.length() == 0) System.out.println(-1);
		else System.out.println(sb.toString());
		
	}
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X,0));
		dp[X] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(v[cur.next]) continue;
			v[cur.next] = true;
			
			for(Node node:adj[cur.next]) {
				if(dp[node.next] > dp[cur.next]+node.dist) {
					dp[node.next] = dp[cur.next]+node.dist;
					pq.offer(new Node(node.next,dp[node.next]));
				}
			}
		}
	}

}

//		//연결 정보 확인
//		for(int i=1;i<=N;i++) {
//			System.out.print(i+" :");
//			for(Node n : adj[i]) {
//				System.out.print(n.next);
//			}
//			System.out.println();
//		}