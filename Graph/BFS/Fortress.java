package ss;
import java.util.*;
import java.io.*;
public class Fortress {
	static int N,M,d[][],a[][],roomSize[];//d:방 번호 a:벽 정보 roomSize[i] :i번 방 크기
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());//열
		M = Integer.parseInt(st.nextToken());//행
		
		a = new int[M][N];//벽 정보 저장(입력 데이터)
		d = new int[M][N];//(i,j) 방 번호 부여, 저장
		roomSize = new int[M*N+1];//방의 크기를 저장!roomSize[i] = i번 방 크기 
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//1.BFS 탐색으로 방 갯수, 각 방의 크기 기록 => 가장 큰 방 크기 도출:roomSize를 완성!
		int idx = 0;//방 인덱스를 의미
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(d[i][j]==0) {//방문하지 않았다면!
					idx++;
					roomSize[idx] = bfs(i,j,idx);
					//d[i][j] = idx;
				}
			}
		}
		sb.append(idx+"\n");//첫번째 정답을 저장!
		int ans=0;
		for(int i=1;i<=idx;i++) {
			if(ans<roomSize[i]) ans = roomSize[i];
		}
		sb.append(ans+"\n");//두번째 정답을 저장!
		
		//2.각 칸마다 인접한 (다른)방을 조사해서 합쳤을 때 최대 크기 도출!
		ans = 0;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				int x = i, y = j;
				for(int dir=0;dir<4;dir++) {
					int nx = x+dx[dir],ny = y+dy[dir];
					if(isOut(nx,ny)) continue;
					if(d[x][y] == d[nx][ny]) continue;
					if((a[x][y] & (1<<dir)) > 0) {
						if(ans<roomSize[d[x][y]] + roomSize[d[nx][ny]]) {
							ans = roomSize[d[x][y]] + roomSize[d[nx][ny]];
						}
					}
				}
			}
		}
		sb.append(ans+"\n");//두번째 정답을 저장!
		System.out.print(sb);
	}
	static int bfs(int sx,int sy,int idx) {
		int sum = 1;//현재 탐색하는 방의 칸 갯수 카운팅!
		Queue<Integer> q = new LinkedList<>();
		q.add(sx);q.add(sy); 
		d[sx][sy] = idx;
		while(!q.isEmpty()) {
			int x = q.remove(), y = q.remove();
			for(int dir=0;dir<4;dir++) {
				int nx = x+dx[dir],ny = y+dy[dir];
				if(isOut(nx,ny) || d[nx][ny] != 0) continue;
				if((a[x][y] & (1<<dir)) > 0) continue;
				
				q.add(nx); q.add(ny); d[nx][ny] = idx;
				sum++;//유효성 검사 통과한 다음 칸에 방문할 ㄸ대 sum을 증가!
			}
		}
		return sum;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>M-1 || y<0 || y>N-1;
	}
}
