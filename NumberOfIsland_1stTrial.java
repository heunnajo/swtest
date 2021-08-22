package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class NumberOfIsland_1stTrial {
	static int w,h,Map[][];
	static boolean[][] visited;
	static StringBuilder sb;
	static int[] dx = {-1,1,0,0,-1,1,-1,1};//상하좌우, 좌상좌하 우상우하
	static int[] dy = {0,0,-1,1,-1,-1,1,1};
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static void CountIsland() {
		int sum=0;
		visited = new boolean[w][h];
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				if(Map[i][j] == 0 || visited[i][j]) continue;
				bfs(i,j,sum+1);
			}
		}
		
	}
	static void bfs(int x,int y,int sum) {
		//BFS로 완전 탐색!
		Queue<Point> q = new LinkedList<>();
		for(int i=0;i<w;i++) {//BFS 돌 때마다 방문체크 배열 초기화!
			for(int j=0;j<h;j++) {
				visited[i][j] = false;
			}
		}
		q.add(new Point(0,0));
		visited[0][0] = true;
		
		//sum++;//BFS 탐색 대상이 된다는 뜻!
		while(!q.isEmpty()) {
			Point cur = q.remove();//(0,0)시작으로 노드 탐색.
			for(int d=0;d<8;d++) {
				int nx = cur.x+dx[d],ny = cur.y+dy[d];
				
				if(check(nx,ny,w,h)||visited[nx][ny]||Map[nx][ny]==0) continue;
				q.add(new Point(nx,ny));
				visited[nx][ny] = true;
			}
		}
		sb.append(sum+"\n");
	}
	static boolean check(int x,int y,int w,int h) {
		return x<0 || x>w-1 || y<0 || y>h-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while(true) {
			String[] input = br.readLine().split(" ");
			w = Integer.parseInt(input[1]);
			h = Integer.parseInt(input[0]);
			Map = new int[w][h];
			for(int i=0;i<w;i++) {
				input = br.readLine().split(" ");
				for(int j=0;j<h;j++) {
					Map[i][j] = Integer.parseInt(input[j]);
				}
			}
			if(w == 0 && h == 0) break;
			CountIsland();//BFS로 섬의 갯수 카운팅해서 sb에 추가한다!
		}
		System.out.print(sb);
	}

}
//System.out.println("w: "+w);
//System.out.println("h: "+h);
//for(int i=0;i<w;i++) {
//	for(int j=0;j<h;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
