package ss;
//문제집(위상정렬+우선순위 큐 )
import java.io.*;
import java.util.*;

public class BOJ1766 {
	static int N;//정점의 갯수
	static ArrayList<Integer>[] adj;
	static int[] inDegree,result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		inDegree = new int[N+1];
		result = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u].add(v);
			inDegree[v]++;
		}
		
		topologySort();
		//result 순서대로 출력
		for(int i=1;i<=N;i++) {
			System.out.print(result[i]+" ");
		}
	}
	static void topologySort() {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		//1.진입 차수 0인 노드 큐에 삽입
		for(int i=1;i<=N;i++) {
			if(inDegree[i] == 0) q.add(i);
		}
		
		//정렬 : N개의 노드에 대해 이루어짐!
		int cur;
		for(int i=1;i<=N;i++) {
			if(q.isEmpty()) {
				System.out.println("사이클 발생, 위상 정렬 불가능한 경우!");
				return;
			}
			cur = q.poll();
			result[i] = cur;//큐에서 나온 순서 = 위상 정렬 결과 저장
			
			for(int j=0;j<adj[cur].size();j++) {
				int y = adj[cur].get(j);
				
				inDegree[y]--;
				if(inDegree[y] == 0) q.add(y);
			}
		}
	}
}