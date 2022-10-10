import java.util.*;
import java.io.*;
//움직이는 미로 탈출
public class BOJ16954 {
	static char[][] Map;
	static int[] dx = {-1,1,0,0,-1,-1,1,1,0};
	static int[] dy = {0,0,-1,1,-1,1,1,-1,0};
	static class Pos{
		int x,y;
		
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map = new char[8][8];
		
		for(int i=0;i<8;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		//1.캐릭터 이동 : bfs()
		//2.벽 이동 : moveWall()
		//3.1과 2를 반복
		bfs();
		
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] v;
		q.offer(new Pos(7,0));
		
		while(!q.isEmpty()) {
			v = new boolean[8][8];
			int size = q.size();
//			for(int i=0;i<q.size();i++) {
			for(int i=0;i<size;i++) {
				Pos cur = q.poll();
				if (Map[cur.x][cur.y] == '#') continue;
				
				if(cur.x == 0 && cur.y == 7) {
					System.out.println(1);
					return;
				}
				
				for(int d=0;d<9;d++) {
					int nx = cur.x+dx[d], ny = cur.y+dy[d];
					
					if(isOut(nx,ny) ||v[nx][ny]) continue;
					if(Map[nx][ny] == '#') continue;
					//if(!isOut(nx-1,ny) && Map[nx-1][ny] == '#') continue;
					
					q.offer(new Pos(nx,ny));
					v[nx][ny] = true;
				}
			}
			moveWall();
		}
		System.out.println(0);
	}
	static void moveWall() {
		for(int i=7;i>=0;i--) {
			for(int j=0;j<8;j++) {
				if(Map[i][j] == '#') {
					Map[i][j] = '.';
					//if(i < 7) {
					if(i != 7) {
						Map[i+1][j] = '#';
					} //i==7이면 수행X
				}
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>7 || y<0 || y>7;
	}
}

//for(int i=0;i<8;i++) {
//	for(int j=0;j<8;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}