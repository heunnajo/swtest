package ss;
import java.util.*;
public class Baaduk2_Easy {
	static int ans,N,M,Map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		//바둑돌 2개 위치 선택
		ans = -1;
		for(int x1=0;x1<N;x1++) {
			for(int y1=0;y1<M;y1++) {
				if(Map[x1][y1] != 0) continue;//빈칸아니면 컨티뉴
				for(int x2=0;x2<N;x2++) {
					for(int y2=0;y2<M;y2++) {
						if(Map[x2][y2] != 0) continue;//빈칸아니면 컨티뉴
						if(x1 == x2 && y1 == y2) continue;
						Map[x1][y1] = 5;
						Map[x2][y2] = 5;
						//BFS로 죽은 그룹 탐색~~!
						int tmp = bfs();
						ans = ans < tmp? tmp:ans;
						//원복
						Map[x1][y1] = 0;
						Map[x2][y2] = 0;
					}
				}
			}
		}
		System.out.println(ans);
	}
	static int bfs() {//NM 판에서 상대돌 2인칸을 시작으로 탐색한다!
		int sum = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 2 && !visited[i][j]) {
					//현위치(i,j) 기준으로 탐색!
					q.add(i); q.add(j);
					visited[i][j] = true;
					int cnt = 1;
					boolean dead = true;
					while(!q.isEmpty()) {
						int x = q.remove();
						int y = q.remove();
						
						for(int d=0;d<4;d++) {
							int nx = x+dx[d]; int ny = y+dy[d];
							if(isOut(nx,ny) ||visited[nx][ny]) continue;
							
							if(Map[nx][ny] == 0) dead = false;
							else {
								cnt++;
								q.add(nx); q.add(ny);
								visited[nx][ny] = true;
							}
						}
					}
					if(dead) sum = sum<cnt? cnt:sum;
				}
			}
		}
		return sum;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}
