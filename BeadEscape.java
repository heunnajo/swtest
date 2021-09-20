import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class BeadEscape {
	static int N,M,Rx,Ry,Bx,By;//N:행,M:열 ,RxRy BxBy 구슬 초기위치 저장 
	static String[][] Board;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Point{
		int Rx,Ry,Bx,By,cnt;
		Point(int Rx,int Ry,int Bx,int By,int cnt){
			this.Rx = Rx;
			this.Ry = Ry;
			this.Bx = Bx;
			this.By = By;
			this.cnt = cnt;
		}
	}
	static int bfs() {//R구슬, B구슬 BFS 탐색해서 구멍으로 최단 거리 이동!
		Queue<Point> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];//빨간구슬(RxRy), 파란구슬(BxBy) 순서!
		q.add(new Point(Rx,Ry,Bx,By,0));
		visited[Rx][Ry][Bx][By] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			//백트랙킹.cur의 cnt가 10보다 크면 그 때까지 빨간 구슬이 구멍 도달 못했다는 의미이므로  -1을 출력.
			if(cur.cnt >= 10) return 0;
			for(int dir=0;dir<4;dir++) {
				//1.구슬 이동
				//1-1.파란구슬
				int nBx = cur.Bx;
				int nBy = cur.By;
				//#만나기전까지 계속 이동
				while(!Board[nBx+dx[dir]][nBy+dy[dir]].equals("#")) {
					nBx += dx[dir];
					nBy += dy[dir];
					if(Board[nBx][nBy].equals("O")) {//이동중 구멍만나면 이동 반복 종료
						break;
					}
				}
				
				//1-2.빨간구슬
				int nRx = cur.Rx;
				int nRy = cur.Ry;
				//#만나기전까지 계속 이동
				while(!Board[nRx+dx[dir]][nRy+dy[dir]].equals("#")) {
					nRx += dx[dir];
					nRy += dy[dir];
					if(Board[nRx][nRy].equals("O")) {//이동중 구멍만나면 이동 반복 종료
						break;
					}
				}
				//2.이동 후 조건판단:구멍(O)에 도착했는지
				//2-1.파란 구슬:컨티뉴 처리
				if(Board[nBx][nBy].equals("O")) continue;
				//2-2.빨간 구슬:정답 찾은 경우 
				if(Board[nRx][nRy].equals("O")) {
						return 1;//이동시키기 전에 조건 비교를 하는 것이기 때문에.
//					}
				}
				//3.이동 후 2개 구슬 동일한 위치에 있을 때
				if(nRx == nBx && nRy == nBy) {
					if(dir == 0) {//상 
						if(cur.Rx<cur.Bx) {nBx = nRx+1;}
						else {nRx=nRx+1;}//Rx>Bx
					} else if(dir == 1) {//하 
						if(cur.Rx<cur.Bx) {nRx=nRx-1;}
						else {nBx = nRx-1;}
//						else {nBx = nBx-1;}//Rx>Bx
					} else if(dir == 2) {//좌 
						if(cur.Ry<cur.By) {nBy =nRy+1;}
						else {nRy = nRy+1;}//Ry>By
					} else {//우 
						if(cur.Ry<cur.By) {nRy=nRy-1;}
						else {nBy = nRy-1;}
//						else {nBy = nBy-1;}//Ry>By
					}
				}
				//4.다음 위치 큐에 삽입&방문체크
				if(!visited[nRx][nRy][nBx][nBy]) {
					q.add(new Point(nRx,nRy,nBx,nBy,cur.cnt+1));
					visited[nRx][nRy][nBx][nBy] = true;
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		Board = new String[N][M];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split("");
			for(int j=0;j<M;j++) {
				Board[i][j] = input[j];
				if(Board[i][j].equals("R")) {
					Rx = i;Ry = j;
				} else if(Board[i][j].equals("B")) {
					Bx = i;By = j;
				}
			}
		}
		
		System.out.println(bfs());
	}

}