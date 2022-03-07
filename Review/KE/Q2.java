//2.일반적인 2차원 배열 BFS
import java.io.*;
import java.util.*;
class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Point{
		int x,y,dist;
		Point(int x,int y,int dist){
			this.x = x; this.y = y; this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[10][10];
		for(int i=0;i<10;i++)
			map[i] = br.readLine().toCharArray();
		
		int ans = 0;
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++) {
				if(map[i][j] == 'H') ans = bfs(i,j,map);//(i,j) 위치에서 bfs
			}
		}
		System.out.println(ans);
	}
	static int bfs(int sx,int sy,char[][] map){
		Queue<Point> q = new LinkedList<>();
		boolean[][] v = new boolean[10][10];
		q.add(new Point(sx,sy,-1));
		v[sx][sy] = true;
		
		while(!q.isEmpty()){
			Point cur = q.poll();
			if(map[cur.x][cur.y] == 'M') return cur.dist;
			for(int d=0;d<4;d++){
				int nx = cur.x+dx[d],ny = cur.y+dy[d];
				if(isOut(nx,ny) || map[nx][ny]=='R') continue;
				if(v[nx][ny]) continue;
				
				q.add(new Point(nx,ny,cur.dist+1));
				v[nx][ny] = true;
			}
		}
		return 987654321;//unreachable
	}
	static boolean isOut(int x,int y){
		return x<0 || x>9 || y<0 || y>9;
	}
}
// for(int i=0;i<10;i++){
// 			for(int j=0;j<10;j++) {
// 				System.out.print(map[i][j]+" ");
// 			}
// 			System.out.println();
// 		}
// 		System.out.println();