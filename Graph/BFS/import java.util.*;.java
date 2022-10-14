import java.util.*;
import java.io.*;
//안전 영역
public class BOJ2468 {
	static int N,Map[][],group[][];//group : 방문 체크 + 단지 번호
	static boolean height[];
	static int min,Max;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pos{
		int x,y;
		Pos(int x,int y){
			this.x = x; this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		group = new int[N][N];
		height = new boolean[101];
		
		StringTokenizer st;
		Max = -1; min = 101;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {		
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(!height[Map[i][j]]) height[Map[i][j]] = true;
				if(Map[i][j] > Max) Max = Map[i][j];
				if(Map[i][j] < min) min = Map[i][j];
			}
		}
		height[0] = true;//0인 높이에 대해서도 BFS를 수행. 장마철 비 양이 0인 경우가 있을 수 있고, 이 경우를 처리하기 위해.
		int cnt;//그룹 단지 번호! = 안전 영역 갯수
		int ans = 0;//아무지역도 물에 잠기지 않을 수도 있다!
		
		//for(int k=min;k<=Max;k++) {//k=2,3
		for(int k=0;k<=Max;k++) {//k=2,3
			if(!height[k]) continue;
			group = new int[N][N];
			cnt = 1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(Map[i][j] > k && group[i][j] == 0) {
						bfs(i,j,cnt++,k);
					}
				}
			}
			cnt = cnt-1;
			//System.out.println("k: "+k+" cnt: "+cnt);
			if(ans < cnt) ans = cnt;
		}
		System.out.println(ans);
	}
	static void bfs(int sx,int sy,int cnt,int k) {
		//그러면 초기 cnt값을 1로 하면 되나? 과연, 내가 생각한 로직이 맞긴 한건가?
		//일단 들어온 cnt 값은 0임.
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sx,sy));
		group[sx][sy] = cnt;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || group[nx][ny] != 0) continue;
				
				if(Map[nx][ny] > k) {//k보다 클 때만 다음으로 이동!
					q.offer(new Pos(nx,ny));
					group[nx][ny] = cnt;
				}
 			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}