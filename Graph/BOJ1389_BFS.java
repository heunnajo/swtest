package ss;
import java.util.*;
import java.io.*;
//케빈베이컨의 6단계 법칙 
public class BOJ1389_BFS {
	static int[][] arr;
	static boolean[] visit;
	
	static int N,M;
	static int result;
	static int min_count = Integer.MAX_VALUE;
	
	static class Node {
		int end,weight;
		
		public Node(int end,int weight) {
			this.end = end;
			this.weight = weight;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[y][x] = 1;
			arr[x][y] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			visit = new boolean[N+1];
			BFS(i);//모든 정점에 대해 다 수행해봐야함.
		}
		System.out.println(result);
	}
	
	static void BFS(int start) {
		Queue<Node> q = new LinkedList<>();
		int count = 0;
		
		visit[start] = true;
		q.offer(new Node(start,0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			count += cur.weight;
			
			for(int i=1;i<=N;i++) {
				int end = arr[cur.end][i];
				
				if(end == 1 && visit[i] == false) {
					visit[i] = true;
					q.offer(new Node(i,cur.weight+1));
				}
			}
		}
		
		if(min_count > count) {
			min_count = count;//최소 갯수를 갱신
			result = start;//그 때의 시작 정점 번호를 결과값으로 저장!
		}
	}
	
}

//			System.out.println("u: "+u+"v: "+v);
//System.out.print("st: "+st);