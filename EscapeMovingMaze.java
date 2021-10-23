package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.*;
public class EscapeMovingMaze {
	static char[][] Map;
	static Point[] Walls;
	static int K;//벽 갯수
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int solve() {
		Queue<Point> q = new LinkedList<>();
		boolean visited[][] = new boolean[8][8];
		q.add(new Point(7,0));
		//visited[7][0] = true;
		//1.욱제 이동
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(Map[cur.x][cur.y] == '#') continue;
			if(cur.x == 0 && cur.y == 7) return 1;
			for(int dir=0;dir<4;dir++) {
				int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				q.add(new Point(cur.x,cur.y));
				if(Map[nx][ny] == '.') {
					q.add(new Point(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
		//2.벽 이동
		for(int k=0;k<K;k++) {
			Point curWall = Walls[k];
			Map[curWall.x][curWall.y] = '.';
			if(!isOut(curWall.x+1,curWall.y)) Map[curWall.x+1][curWall.y] = '#';
		}
		return 0;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>7 || y<0 || y>7;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map = new char[8][8];
		Walls = new Point[64];
		K=0;
		for(int i=0;i<64;i++) Walls[i] = new Point();//객체 배열 초기화 필수? 그냥 바로값 저장하면 안되남.
		for(int i=0;i<8;i++) {
			Map[i] = br.readLine().toCharArray();
			for(int j=0;j<8;j++) {
				if(Map[i][j] == '#') Walls[K++] = new Point(i,j);
			}
		}
		System.out.println(solve());
	}
	
}
//		for(int k=0;k<K;k++) {
//			Point curWall = Walls[k];
//			System.out.print(curWall.x+" "+curWall.y+" ");
//		}

//		for(int i=0;i<8;i++) {
//			for(int j=0;j<8;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}