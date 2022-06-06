//내리막 길
package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1520 {
	static int m,n,ans,map[][];//m : 행,n : 열 
	static class Pair{
		int x,y;//default access modifier : within same class, same package only
		
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		bfs();//map을 bfs하는 함수
		System.out.println(ans);
	}
	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] v = new boolean[m][n];
		q.add(new Pair(0,0));
		v[0][0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			if(cur.x == m-1 && cur.y == n-1) ans++;
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || v[nx][ny]) continue;
				//이동 가능 조건 : 현재칸의 값보다 더 작을 때만 이동 가능!(현재칸보다 크거나 같으면 컨티뉴)
				if(map[nx][ny] >= map[cur.x][cur.y]) continue;
				
				q.add(new Pair(nx,ny));
				v[nx][ny] = true;
			}
		}
		
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=m || y<0 || y>=n;
	}
}

//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}