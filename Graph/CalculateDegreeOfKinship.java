import java.util.*;

public class CalculateDegreeOfKinship {
	static int ans,N,M,a,b;//a->b
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		M = sc.nextInt();
		for(int i=1;i<=M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}
		ans = -1;
		dfs(a,0);//a에서 탐색을 시작,0번에서 싲작.b에 도달했다면 ans는 그 촌수로 갱신되어있다.
		System.out.println(ans);
	}
	static void dfs(int node,int cnt) {
		visited[node] = true;
		if(node == b) {
			ans = cnt;
			return;
		}
		for(int i:list[node]) {
			if(!visited[i]) {
				dfs(i,cnt+1);
			}
		}
	}
}