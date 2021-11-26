import java.util.*;
public class Virus {
	static int ans,N,M;//N:노드 갯수, M:연결 정보
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
//		list = (ArrayList<Integer>) new [N+1];
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=1;i<=M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}
		ans = 0;
		dfs(1);
		System.out.println(ans);
	}
	static void dfs(int node) {
		visited[node] = true;
		for(int i:list[node]) {
			if(!visited[i]) {
				dfs(i);ans++;
			}
		}
	}
}