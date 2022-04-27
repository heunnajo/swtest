//BOJ#1325 효율적인 해킹
package ss;
import java.util.*;
import java.io.*;
public class EfficientHacking {
	static int n,m,term;
	static boolean[][] adj;
	static boolean[] v;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adj = new boolean[n+1][n+1];
		v = new boolean[n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u][v] = true;
			adj[v][u] = true;
		}
		
		int ans = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=1;i<=n;i++) {
			int dist = 0;
			dfs(1,dist);//1에서 시작하는 게 맞을까?탐색 시작점이 중요한가?시작점이 중요하진 않지만 각 노드 연결상태는 떨어져있을 수 있다.
			if(ans<=dist) list.add(term);//경로 길이가 오름차순으로 나온다면 k개의 모든 경로 길이가 다 저장됨!그래서 이 로직은 적절치않음. 추가적인 최댓값 비교 및 판단 필요!
		}
		
		Collections.sort(list);//단말 노드 번호 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		
		for(int a:list) {
			sb.append(sb).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}
	static void dfs(int idx,int dist) {
		for(int i=1;i<=n;i++) {
			if(idx == i) continue;
			if(v[i]) continue;
			
			if(adj[idx][i]) {//간선이 존재한다
				v[i] = true; term = i;//방문하기 전에 방문체크,단말노드번호 갱신
				dfs(i,dist+1);
			}
		}
	}
}
//내일도 일찍 하루를 시작하려면 no matter what, I should go home 10pm
////간선 정보 저장 확인O
//for(int i=1;i<=n;i++) {
//	for(int j=1;j<=n;j++) {
//		if(adj[i][j]) System.out.print(" 1 ");
//		else System.out.print(" 0 ");
//	}
//	System.out.println();
//}