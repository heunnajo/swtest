import java.io.*;
import java.util.*;

//해킹
public class BOJ10282 {
	static int n,d,c,a,b,s;
	static ArrayList<Node>[] adj;
	static boolean[] visited;
	static int count,time;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int node, time;
		
		public Node(int node,int time) {
			this.node = node;
			this.time = time;
		}
		
		public int compareTo(Node n) {
			return this.time - n.time;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(tc-- >0) {
			
			st = new StringTokenizer(br.readLine());
			
			//n d c 
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			//초기화
			adj = new ArrayList[n+1];
			for(int j=1;j<=n;j++) {
				adj[j] = new ArrayList<>();
			}
			visited = new boolean[n+1];
			count = 0;
			time = 0;
			dist = new int[n+1];
			
			Arrays.fill(dist, INF);
			
			//d 개의 의존성 입력 받고
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				
				//a b s : a <- b
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				adj[b].add(new Node(a,s)); //a가b를 의존 : b가 감염되면 s초 후 a 감염.즉, b->a 는 b에서 a로 가는 간선이 있다고 할 수 있다.
				
			}
			//현 tc solve
			dijkstra();
			
			//정답 도출, 저장
			for(int i=1;i<=n;i++) {
				if(dist[i] != INF) {
					time = Math.max(time, dist[i]);
				}
			}
			sb.append(count).append(" ").append(time).append("\n");
		}
		
		System.out.print(sb.toString());
		
	}
	static void dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(c,0));
		dist[c] = 0;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();//처음 : 시작점 c, cur.node = c, cur.time = 0
			
			if(visited[cur.node]) continue;
			visited[cur.node] = true;
			count++;
			
			//cur의 인접 노드 방문, 최단 경로 도출!
			for(int i=0;i<adj[cur.node].size();i++) {
				Node next = adj[cur.node].get(i);//next = 정점 c의 인접노드들
				
				//대소 비교 통해 더 작은 값 도출!
				if(dist[next.node] > dist[cur.node] + next.time) {
					dist[next.node] = dist[cur.node] + next.time;
					q.offer(new Node(next.node,dist[next.node]));//정점 next까지 가는 연결 정보 = (next.node, next.time)
				}
			}
		}
	}
	
}