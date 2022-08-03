import java.util.*;
import java.io.*;
public class Main {
	static int n,m;//n:도시 수 m : 여행 계획에 속한 도시 수 
	static boolean[][] adj;
	static int[] plan;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new boolean[n+1][n+1];
		plan = new int[m];
		
		StringTokenizer st;
		
		for(int i=1;i<=n;i++) {//연결 정보 저장
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x == 1) {
					adj[i][j] = true;
					adj[j][i] = true;
				}
				//추가한 부분
				if(i == j) adj[i][j] = true;//길이 있다고 저장
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		floydWarshall();
		
		//print();//플로이드와샬 수행한 이후 연결 정보 확인
		String ans = "YES";
		for(int i=0;i<m-1;i++) {
			if(!adj[plan[i]][plan[i+1]]) ans = "NO";
		}
		System.out.println(ans);
		
	}
	static void floydWarshall() {
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(adj[i][j] == false) {
						if(adj[i][k] && adj[k][j]) adj[i][j] = true;
					}
				}
			}
		}
	}
	static void print() {
		for(int i=1;i<=n;i++) {//연결 정보 저장
			for(int j=1;j<=n;j++) {
				System.out.print(adj[i][j]+" ");
			}
			System.out.println();
		}
	}
}


//		for(int i=0;i<m;i++) {
//			System.out.println(plan[i]+" ");
//		}