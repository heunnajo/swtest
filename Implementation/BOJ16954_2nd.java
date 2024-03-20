//움직이는 미로 탈출
import java.util.*;
import java.io.*;
public class BOJ16954 {
	static int[] dx = {-1,-1,-1,0,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,0,1,-1,0,1};
	static char[][] Map;
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
		
		boolean ans = solve();
		if(ans) System.out.println(1);
		else System.out.println(0);
	}
	static boolean solve() {
		//욱제의 이동을 2차원 배열 bfs로 구현한다.
		Queue<Pos> q = new LinkedList<>();
		boolean[][] v;//매 턴마다 방문정보 초기화되어야하는데, 어느 시점,위치에서 해야할까? 
		q.offer(new Pos(7,0));
		//코드 구현 순서로는 욱제의 이동을 먼저 구현했지만, moveWall()로 벽을 이동하고, 그 다음의 while문이 돌면서 비로소 욱제 이동이 실행된다...
		while(!q.isEmpty()) {
			v = new boolean[8][8];
			int size = q.size();
			for(int i=0;i<size;i++) {
				Pos cur = q.poll();
				if(Map[cur.x][cur.y] == '#') continue;
				
				if(cur.x == 0 && cur.y == 7) {
					return true;
				}
				
				for(int d=0;d<9;d++) {
					int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
					if(isOut(nx,ny) || v[nx][ny]) continue;
					if(Map[nx][ny] == '#') continue;//벽인칸은 이동불가능
					
					q.offer(new Pos(nx,ny));
					v[nx][ny] = true;
				}
			}
			moveWall();
		}
		return false;
	}
	static boolean moveWall() {
		//0번행은 .이 된다 
		for(int i=7;i>=0;i--) {
			for(int j=0;j<8;j++) {
				if(i != 0) {
					Map[i][j] = Map[i-1][j];
				} else {
					Map[i][j] = '.';
				}
			}
		}
		return true;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>7 || y<0 || y>7;
	}

}

//		for(int i=0;i<8;i++) {
//			for(int j=0;j<8;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}