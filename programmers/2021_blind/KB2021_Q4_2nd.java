import java.util.*;
import java.io.*;
//합승 택시 요금
public class KB2021_Q4_2nd {
	static int N,S,A,B;
	static int[] distS,distA,distB;
	static ArrayList<Node>[] adj;
	static final int INF = Integer.MAX_VALUE;
	static class Node implements Comparable<Node>{
		int to,cost;
		Node(int to,int cost){
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node node) {
			return this.cost - node.cost;
		}
	}
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String new_id = br.readLine();
		int n = 6;
		int s = 4;
		int a = 6;
		int b =2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		solution(n,s,a,b,fares);
	}
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		N = n; S = s; A = a; B = b;
        int answer = INF;
        
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++) {
        	adj[i] = new ArrayList<Node>();
        }
        //fares로 인접 연결 정보 저장!
        for(int i=0;i<fares.length;i++) {
        	int from = fares[i][0];
        	int to = fares[i][1];
        	int cost = fares[i][2];
        	adj[from].add(new Node(to,cost));
        	adj[to].add(new Node(from,cost));
        }
        distS = dijkstra(s);//다익스트라:s에서 출발, 각 정점까지 최소 비용 구한다.
        distA = dijkstra(a);//다익스트라:a에서 출발, 각 정점까지 최소 비용 구한다.
        distB = dijkstra(b);//다익스트라:b에서 출발, 각 정점까지 최소 비용 구한다.
        
        for(int i=1;i<=N;i++) {
        	int sum = distS[i]+distA[i]+distB[i];
        	if(answer > sum) answer = sum;
        }
        
        //System.out.println("answer: "+answer);
        return answer;
    }
	static int[] dijkstra(int st) {
		int[] result = new int[N+1];
		Arrays.fill(result, INF);
		result[st] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(st,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			//cur의 인접 정점들을 방문한다!
			for(Node next : adj[cur.to]) {
				if(result[next.to] > result[cur.to] + next.cost) {
					result[next.to] = result[cur.to] + next.cost;
					pq.offer(new Node(next.to,result[next.to]));
				}
			}
		}
//		System.out.println("출발점 s에서 최소 비용 출력");
//		for(int i=1;i<=N;i++) {
//			System.out.println(i+"까지 거리: "+result[i]);
//		}
		return result;
	}

}
