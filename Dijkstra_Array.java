package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dijkstra_Array {
	static int V,E,start,Dist[][];
	static int[] D;
	static int v;
	static final int INF = Integer.MAX_VALUE;
	static int ShortestVertex(int s) {
		v = 0;
		int minDist = INF;
		for(int i=0;i<V;i++) {//start노드의 인접리스트 크기 만큼!
			for(int j=0;j<V;j++) {
				if(Dist[i][j]< minDist) {//비교대상 둘다 최댓값 저장하고 있는데 어떻게 비교하징 
					minDist = Dist[i][j];
					v = j;
				}
			}
		}
		if(minDist == Integer.MAX_VALUE) minDist = -1;
//		System.out.println("최단 거리의 정점 : "+v);
//		System.out.println("최단 거리 : "+minDist);
		return v;
	}
	static void Dijkstra(int s) {
		for(int i=0;i<V;i++) {
			//for(int j=0;j<V;j++) {
				if(D[v]+Dist[v][i] < Dist[s][i]) {
					D[i] = D[v]+Dist[v][i];
				}
			//}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		//초기화
		V = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		start = Integer.parseInt(br.readLine());
		Dist = new int[V][V];//n개의 정점마다 간선정보를 저장해야한다.
		D = new int[V];
		
		//Dist 배열을 큰값으로 초기화.
		for(int i=0;i<V;i++) {
			for(int j=0;j<V;j++) {
				Dist[i][j] = INF;
			}
		}
		//간선 정보 저장!
		for(int i=0;i<E;i++) {
			input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[0])-1;
			int v2 = Integer.parseInt(input[1])-1;
			int w = Integer.parseInt(input[2]);
			
			Dist[v1][v2] = w;//v1과 연결된 정점 v2,가중치 저장.
			Dist[v2][v1] = w;
		}
		//Dijkstra 실행.
		ShortestVertex(start);
		Dijkstra(start);
		for(int i=0;i<V;i++) {
			if(i == start) {
				System.out.println(0);
			}
			else if(D[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(D[i]);
			}
		}
		br.close();

	}

}
//for(int i=0;i<V;i++) {
//	for(int j=0;j<V;j++) {
//		System.out.printf("%2d",Dist[i][j]);
//	}
//	System.out.println();
//}
