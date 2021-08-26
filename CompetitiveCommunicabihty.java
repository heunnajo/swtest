package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class CompetitiveCommunicabihty {
	static int N,K,S,X,Y,Map[][];
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int solve() {
		
		while(S-- >0) {//S초 동안 BFS한다.
			Queue<Point> q = new LinkedList<>();
			q.add(new Point(0,0));
			boolean[][] visited = new boolean[N][N];
			
			while(!q.isEmpty()) {
				Point cur = q.remove();
				
				for(int d=0;d<4;d++) {
					int nx = cur.x+dx[d];
					int ny = cur.y+dy[d];
					
					if(nx<0 || nx>N-1 || ny<0 || ny>N-1) continue;
					if(visited[nx][ny]) continue;
					
					//완전탐색하면서 낮은 번호가 우선권 갖는다.
					//이렇게 조건 걸어줘도 되나? 완전탐색이 안되지는 않을까...
					if(Map[cur.x][cur.y] < Map[nx][ny]) {
						q.add(new Point(nx,ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		return Map[X][Y];
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);//바이러스 갯수.
		
		Map = new int [N][N];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		input = br.readLine().split(" ");
		S = Integer.parseInt(input[0]);
		X = Integer.parseInt(input[1])-1;
		Y = Integer.parseInt(input[2])-1;
		
		System.out.println(solve());
		br.close();
	}

}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}