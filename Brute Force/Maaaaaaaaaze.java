package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class Maaaaaaaaaze {
	static final int INF = 987654321;
	static int ans,Maze[][][],cMaze[][][],order[];
	static boolean[] visited;
	static int[] xdir = {0,0,0,0,-1,1};
	static int[] ydir = {0,0,-1,1,0,0};
	static int[] zdir = {-1,1,0,0,0,0};
	static class Point{
		int x,y,z,d;
		Point(int x,int y,int z,int d){
			this.x = x;
			this.y = y;
			this.z = z;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Maze = new int[5][5][5];
		cMaze = new int[5][5][5];
		order = new int[5];
		visited = new boolean[5];
		
		for(int i=0;i<25;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				Maze[i/5][i%5][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = INF;//최솟값 갱신을 위해!
		perm(0);//판의 순서를 정한다!
		if(ans == INF) ans = -1;
		System.out.println(ans);
	}
	static void perm(int idx) {//idx번째 놓을 판의 순서를 정한다!
		if(idx==5) {
//			for(int z=0;z<5;z++) System.arraycopy(Maze[order[z]], cMaze[z], 0, cMaze[z].length);
			for(int z=0;z<5;z++) System.arraycopy(Maze[order[z]], 0, cMaze[z], 0, cMaze[z].length);
			//판 5개 회전 로직 구현, 그 다음 BFS.(조건판단:시작점,도착점 불가능한 경우 필터링)
			for(int a=0;a<4;a++) {
				rotate(0);
				for(int b=0;b<4;b++) {
					rotate(1);
					for(int c=0;c<4;c++) {
						rotate(2);
						for(int d=0;d<4;d++) {
							rotate(3);
							for(int e=0;e<4;e++) {
								rotate(4);
								if(cMaze[0][0][0] == 1 && cMaze[4][4][4] == 1) {
									//System.out.println("bfs는 실행하니?");
									bfs();//출발점은 정해져있으니까 굳이 매개변수로 안 넘겨줘도 될 것 같음!
								}
							}
						}
					}
				}
			}
//			for(int i=0;i<order.length;i++)
//				System.out.print(order[i]+" ");
//			System.out.println();
//			System.out.println();
			return;
		}
		//idx번째 순서 정하고, 다음 경우 호출!
		for(int i=0;i<5;i++) {
			if(!visited[i]) {
				order[idx] = i;visited[i] = true;
				perm(idx+1);
				order[idx] = -1;visited[i] = false;
			}
		}
	}
	static void rotate(int x) {
		int tmp;
//		for(int a=0;a<5;a++) {//0,1,2,3,4번째 판을 
			for(int s=0,e=4;s<e;s++,e--) {
				for(int i=s,j=e;i<e;i++,j--) {
					tmp = cMaze[x][s][i];
					cMaze[x][s][i] = cMaze[x][i][e];
					cMaze[x][i][e] = cMaze[x][e][j];
					cMaze[x][e][j] = cMaze[x][j][s];
					cMaze[x][j][s] = tmp;
				}
			}
//		}
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		visited[0][0][0] = true;
		q.add(new Point(0,0,0,0));
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(cur.x == 4 && cur.y == 4 && cur.z == 4) {
				//System.out.println("도착점 도달했니?");
				ans = Math.min(ans, cur.d);
				continue;//최솟값 갱신을 하고 컨티뉴처리!?
				//break;
				//System.exit(0);
			}
			for(int d=0;d<6;d++) {
				int nx = cur.x+xdir[d], ny = cur.y+ydir[d], nz = cur.z+zdir[d];
				if(isOut(nx,ny,nz) || visited[nx][ny][nz]) continue;
				if(cMaze[nx][ny][nz] == 1) {
					visited[nx][ny][nz] = true;
					q.add(new Point(nx,ny,nz,cur.d+1));
				}
			}
		}
	}
	static boolean isOut(int x,int y,int z) {
		return x<0 || x>4 || y<0 || y>4 || z<0 || z>4;
	}
}
//		for(int i=0;i<25;i++) {
//			for(int j=0;j<5;j++) {
//				System.out.print(Maze[i/5][i%5][j]+" ");
//			}
//			System.out.println();
//		}
