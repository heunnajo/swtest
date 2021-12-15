package ss;
import java.util.*;
import java.io.*;
public class BabyShark2 {
	static int N,M,Map[][];//N:행 M:열
	final static int[] dx = {0,0,1,-1,1,1,-1,-1};
    final static int[] dy = {1,-1,0,0,1,-1,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j=0;j<M;j++) {
				 Map[i][j] = Integer.parseInt(st.nextToken());
			 }
		}
		
		//1.0인 위치마다 안전 거리구하기:2차원 배열 순회,BFS
		int ans = 0,dist = 0;;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j]==0) {
					dist = bfs(i,j);
					//2.안전거리 최댓값 도출
					if(ans<dist) ans = dist;
				}
			}
		}
		System.out.println(ans);
	}
	static int bfs(int sx,int sy) {
		//거리 기록, 방문 체크 역할 
		int[][] dist = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dist[i][j] = -1;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		q.add(sx);q.add(sy);
		dist[sx][sy] = 0;
		
		while(!q.isEmpty()) {
			int curX = q.remove(), curY = q.remove();
			for(int d=0;d<8;d++) {
				int nx = curX+dx[d], ny = curY+dy[d];
				if(isOut(nx,ny) || dist[nx][ny] != -1) continue;//범위 벗어나거나 -1이 아니면 방문한 적 잇으므로 컨티뉴 처리!
				if(Map[nx][ny] == 1) {
					return dist[curX][curY]+1;
				} else {
					q.add(nx);q.add(ny);
					dist[nx][ny] = dist[curX][curY]+1;
				}
			}
		}
		return 50000;//이까지 올 일이 있을까?있다면 어떤 값을 리턴해야하나? 상어의 위치를 못찾았다는 뜻인디!그럼 불가능한 값을 리턴!
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}
