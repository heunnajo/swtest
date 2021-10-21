package Dijkstra;
import java.io.*;
import java.util.*;
public class DijkstraShortestPath_AdjacentList {
	static final int INF = 500000;
	static int V,E,Start,D[];
	static List<Pair> list[];
	static class Pair implements Comparable<Pair>{
		int to,w;
		Pair(int to,int w){
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Pair p) {
			return this.w - p.w;
		}
	}
	//부분 해를 이용해서 특정 정점 start에서 모든 각 노드까지의 최단 거리를 찾는다!
	static void Dijkstra() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();//우선순위 큐를 이용해서 최단거리를 저장한다!
		boolean[] check = new boolean[V];//방문체크용 boolean 배열
		pq.add(new Pair(Start,0));
		D[Start] = 0;
		
		while(!pq.isEmpty()) {
			Pair curNode = pq.remove();
			int cur = curNode.to;
			
			if(check[cur] == true) continue;//방문한 노드면 continue처리해서 다음 노드로~
			
			check[cur] = true;
			for(Pair p:list[cur]) {//인접리스트 순회하면서 Start의 인접노드cur의 인접노드를 찾는다!
				if(D[p.to] > D[cur]+p.w) {//D[p.to]=Start에서 p.to최단거리. D[cur]+p.w= Start->cur->p를 비교!    S->cur->p 와 S->p를 비교하는 건가?
					D[p.to] = D[cur]+p.w;//최솟값 갱신
					pq.add(new Pair(p.to,D[p.to]));//(cur의 인접노드,거리)를 pq에 추가!
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		V = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		Start = Integer.parseInt(br.readLine())-1;
		list = new ArrayList[V];//정점깻수만큼 배열 생성
		
		//초기화
		D = new int[V];//Start에서 각 정점까지 이르는 최단 거리를 저장!
		Arrays.fill(D, INF);//최댓값으로 저장!
		D[Start] = 0;
		for(int i=0;i<V;i++)//인접리스트 ArrayList 각 인덱스마다 참조변수 초기화 필수!
			list[i] = new ArrayList<Pair>();
		
		//간선 정보 저장
		for(int i=0;i<E;i++) {
			input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[0])-1;
			int v2 = Integer.parseInt(input[1])-1;
			int w = Integer.parseInt(input[2]);
			
			list[v1].add(new Pair(v2,w));//v1과 연결된 v2와 가중치 저장 
		}
		StringBuilder sb = new StringBuilder();
		Dijkstra();//Start에서 시작하여 모든 정잠에서 모든 정점에 이르는 것.
		for(int i=0;i<V;i++) {
			if(D[i] == INF) sb.append("INF\n");
			else sb.append(D[i]+"\n");
		}
		System.out.print(sb);
	}

}
