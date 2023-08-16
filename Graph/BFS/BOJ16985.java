package swea;
import java.io.*;
import java.util.*;
//Maaaaaaaaaze : debugging
public class BOJ16985 {
	static int[][][] Maze;
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dz = {-1,1,0,0,-1,1};
	static int Ans;
	static int[] Order;
	static boolean[] Visited;
	static class Pos{
		int x,y,z,dist;
		
		Pos(int x,int y,int z,int dist){
			this.x = x;
			this.y = y;
			this.z = z;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Maze = new int[5][5][5];
		Order = new int[5];
		Visited = new boolean[5];
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<5;k++) {
					Maze[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		Ans = Integer.MAX_VALUE;//최악의 경우 각 칸마다 모든 칸 다 방문하는 경우:25*5=125
		for(int i=0;i<5;i++) {
			for(int d=0;d<4;d++) {
				rotate(i);
				setOrder(0);
				Visited[0] = true;
			}
		}
		if(Ans == Integer.MAX_VALUE) Ans = -1;
		System.out.println(Ans);
	}
	static void rotate(int idx) {
		//idx번째 판을 시계방향 90도 회전시킨다.
		int[][] newArr = new int[5][5];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				newArr[i][j] = Maze[idx][4-j][i];
			}
		}
		Maze[idx] = newArr;
	}
	static void setOrder(int idx) {
		//1.종료
		if(idx == 5) {
			System.out.print("현재 순서:");
			for(int i=0;i<5;i++) {
				System.out.print(Order[i]+" ");
			}
			System.out.println();
			makeMaze();
			bfs();
			System.out.println("Ans: "+Ans);
			return;
		}
		//2.현재 경우 선택 & 다음 경우 호출
		for(int i=0;i<5;i++) {
			if(Visited[i]) continue;
			Visited[i] = true;
			Order[idx] = i;
			setOrder(idx+1);
			Visited[i] = false;
		}
	}
	static void makeMaze() {
		//serOrder의 결과 Order 순서대로 Maze구성.
		int[][][] newMaze = new int[5][5][5];
		for(int i=0;i<5;i++) {
			newMaze[i] = Maze[Order[i]];
		}
		Maze = newMaze;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				for(int k=0;k<5;k++) {
					System.out.print(Maze[i][j][k]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("============================");
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] v = new boolean[5][5][5];
		q.offer(new Pos(0,0,0,0));
		v[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.x == 4 && cur.y == 4 && cur.z == 4) {
				if(Ans > cur.dist) {
					Ans = cur.dist;
				}
				return;
			}
			for(int d=0;d<6;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				int nz = cur.z+dz[d];
				if(isOut(nx,ny,nz) || v[nx][ny][nz] || Maze[nx][ny][nz]==0) continue;
				
				q.offer(new Pos(nx,ny,nz,cur.dist+1));
				v[nx][ny][nz] = true;
			}
		}
	}
	static boolean isOut(int x,int y,int z) {
		return x<0 || x>4 || y<0 || y>4 || z<0 || z>4;
	}
}

//		for(int i=0;i<5;i++) {
//			for(int j=0;j<5;j++) {
//				for(int k=0;k<5;k++) {
//					System.out.print(Maze[i][j][k]+" ");
//				}
//				System.out.println();
//			}
//		}

//for(int i=0;i<5;i++) {
//System.out.print(Order[i]+" ");
//}
//System.out.println();