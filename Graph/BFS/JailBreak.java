package ss;
import java.util.*;
import java.io.*;
public class JailBreak {
	static char[][] Jail;
	static int ans,h,w;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Point{
		int x,y,d;
		Point(int x,int y,int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		for(int t=0;t<tc;t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			Jail = new char[h][w];
			ans = 987654321;
			for(int i=0;i<h;i++) {
				Jail[i] = br.readLine().toCharArray();
			}
			//bfs();//Jail을 BFS 탐색!
		}
	}
	static void MakeFenceBFS(int x,int y) {
		Queue<Point> q = new LinkedList<>();
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(Jail[i][j]=='$') {//bfs한다.
					q.add(new Point(i,j,0));
					while(!q.isEmpty()) {
						Point cur = q.remove();
						for(int d=0;d<4;d++) {
							int nx = i+dx[d], ny = j+dy[d];
							if(isOut(nx,ny)) {
								ans = Math.min(ans, cur.d);
							} 
							if(Jail[nx][ny] =='.') {//다음으로 이동
								q.add(new Point(nx,ny,cur.d));
								//visited[nx][ny] = true;
							} else if(Jail[nx][ny] == '#') {
								q.add(new Point(nx,ny,cur.d+1));
							}
						}
						
					}
				}
			}
		}
		
		
//		while(!q.isEmpty()) {
//				if(isOut(nx,ny)||visited[nx][ny]) continue;
//				
//				if(Map[nx][ny]=='W') {
//					Map[curX][curY] = 'D'; visited[nx][ny] = true;
//				}
//				if(Map[nx][ny]=='.') {
//					q.add(nx);q.add(ny); visited[nx][ny] = true;
//				}
//				
//			}
//		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>h-1 || y<0 || y>w-1;
	}
}

//			for(int i=0;i<r;i++) {
//				for(int j=0;j<c;j++) {
//					System.out.print(Jail[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();