package ss;
import java.io.*;
import java.util.*;

//해킹
public class BOJ10282 {
	static int n,d,c,a,b,s;
	static int count,time;
	static ArrayList<Node>[] adj;
	static boolean[] visited;
	static int[] dist;
	
	static class Node implements Comparable<Node> {
		int node, time;
		
		public Node(int node,int time) {
			this.node = node;
			this.time = time;
		}
		
		@Override
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
			
			count = 0;
			time = 0;
			dist = new int[n+1];
			visited = new boolean[n+1];
			Arrays.fill(dist,Integer.MAX_VALUE);
			dist[c] = 0;
			
			//d 개의 의존성 입력 받고
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				
				//a b s : a <- b
				a = Integer.parseInt(st.nextToken());//2
				b = Integer.parseInt(st.nextToken());//1 
				s = Integer.parseInt(st.nextToken());
				
				adj[b].add(new Node(a,s)); //a가b를 의존 : b가 감염되면 s초 후 a 감염.즉, b->a 는 b에서 a로 가는 간선이 있다고 할 수 있다.
				
			}
			
			//현 tc solve
			dijkstra();
			
			for(int j=1;j<=n;j++) {
				if(dist[j] != Integer.MAX_VALUE) {
					time = Math.max(time, dist[j]);
				}
			}
			
			//정답 저장 : 일단 count만 먼저 확인..!
			sb.append(count).append(" ").append(time).append("\n");
		}
		
		System.out.print(sb.toString());
		
	}
	
	static void dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(c,0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(visited[cur.node]) continue;
			visited[cur.node] = true;
			count++;
			
			for(int i=0;i<adj[cur.node].size();i++) {
				Node next = adj[cur.node].get(i);
				
				if(dist[next.node] > dist[cur.node] + next.time) {
					dist[next.node] = dist[cur.node] + next.time;
					q.offer(new Node(next.node,dist[next.node]));
				}
			}
		}
	}
	
}
