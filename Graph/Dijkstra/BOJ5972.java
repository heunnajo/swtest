//택배 배송
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ5972 {
	static int N,M,Ans,Dist[];
	static ArrayList<Node>[] Adj;
	
	static class Node implements Comparable<Node>{
		int node,cost;
		Node(int node,int cost){
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Adj = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			Adj[i] = new ArrayList<>();
		}
		Dist = new int[N+1];
		Arrays.fill(Dist,Integer.MAX_VALUE);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			Adj[u].add(new Node(v,cost));
			Adj[v].add(new Node(u,cost));
		}
		
		dijkstra();
		System.out.println(Dist[N]);
	}
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1,0));
		Dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(cur.node == N) {
				return;
			}
			for(Node next:Adj[cur.node]) {
//				i,ii 둘다 맞음.
//				i
//				if(Dist[next.node] > Dist[cur.node] + next.cost) {
//					Dist[next.node] = Dist[cur.node] + next.cost;
//					pq.offer(new Node(next.node,Dist[next.node]));
//				}
//				ii
				if(Dist[next.node] > cur.cost + next.cost) {
					Dist[next.node] = cur.cost + next.cost;
					pq.offer(new Node(next.node,Dist[next.node]));
				}
			}
		}
	}
}
