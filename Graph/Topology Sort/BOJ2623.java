package ss;
//음악 프로그램
import java.io.*;
import java.util.*;
public class BOJ2623 {
	static int N,M;
	static ArrayList<Integer>[] adj;
	static int[] inDegree,result;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//zero-index를 사용!
		inDegree = new int[N];
		result = new int[N];
		adj = new ArrayList[N];
		flag = true;
		
		for(int i=0;i<N;i++) adj[i] = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int[] arr = new int[cnt];
			
			for(int j=0;j<cnt;j++) {
				arr[j] = Integer.parseInt(st.nextToken())-1;
			}
			
			for(int j=0;j<cnt-1;j++) {
				adj[arr[j]].add(arr[j+1]);
				inDegree[arr[j+1]]++;
			}
		}
		
		topologySort();
		
		if(flag) {//위상 정렬 가능한 경우만 값을 출력
			for(int i=0;i<N;i++) System.out.println(result[i]+1);
		}
		
	}
	static void topologySort() {
		Queue<Integer> q = new LinkedList<>();
		
		//1.진입 차수 0인 노드 큐에 삽입
		for(int i=0;i<N;i++) {
			if(inDegree[i] == 0) q.add(i);
		}
		
		//정렬 : N개의 노드에 대해 이루어짐!
		int cur;
		for(int i=0;i<N;i++) {
			if(q.isEmpty()) {
//				System.out.println("사이클 발생, 위상 정렬 불가능한 경우!");
				flag = false;
				System.out.println("0");
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
