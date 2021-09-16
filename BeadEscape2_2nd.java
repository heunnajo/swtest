package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class BeadEscape2_2nd {
	static int ans,N,M;//N:행, M:열
	static final int INF = 987654321;
	static int Rx,Ry,Bx,By,Hx,Hy;//R:빨간구슬,B:파란구슬,H:구멍 
	static String[][] Map;
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
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void bfs() {//기울인 횟수 = BFS 거리 개념 
		Queue<Point> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		q.add(new Point(Rx,Ry,Bx,By,0));
		visited[Rx][Ry][Bx][By] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(cur.cnt >= 10) {
				ans=INF;
				return;
			}
			for(int d=0;d<4;d++) {
				//1.구슬 이동 :다음이동좌표값이 #이기 전까지 계속해서 이동!
				//파란구슬 먼저 이동
//				int nBx = cur.Bx+dx[d];
//				int nBy = cur.By+dy[d];//배열범위체크하지 않고 바로 값을 조회해서 그런가?
//				while(!Map[nBx][nBy].equals("#")) {
				int nBx = cur.Bx;
				int nBy = cur.By;//배열범위체크하지 않고 바로 값을 조회해서 그런가?
				while(!Map[nBx+dx[d]][nBy+dy[d]].equals("#")) {
					nBx += dx[d];
					nBy += dy[d];
					if(Map[nBx][nBy].equals("O")) break;//일단 반복 종료.
				}
				//빨간 구슬 이동
//				int nRx = cur.Rx+dx[d];
//				int nRy = cur.Ry+dy[d];
//				while(!Map[nRx][nRy].equals("#")) {
				int nRx = cur.Rx;
				int nRy = cur.Ry;
				while(!Map[nRx+dx[d]][nRy+dy[d]].equals("#")) {
					nRx += dx[d];
					nRy += dy[d];
					if(Map[nRx][nRy].equals("O")) break;//일단 반복 종료.
				}
				//2.이동한 위치가 O일 때
				//파란구슬이 o일 때:반복을 종료.
//				if(Map[nBx][nBy].equals("O")) return;//위에서 바로 리턴해도 되는 거 아닌가?!
				if(Map[nBx][nBy].equals("O")) continue;//continue?다음 방향으로 기울인다!
				//파란구슬이 구멍에 도달했다고 바로 종료하는 것이 아님을 보영준다.
				
				//빨간구슬이 o일 때:정답 찾은 경우:그때의 객체 인스턴스 cnt속성값이 정답. 정답변수에 저장하고 반복문 종료하고 리턴. 
				if(Map[nRx][nRy].equals("O")) {
					//if(cur.cnt<=10) {
						ans = cur.cnt+1;//cur.cnt가 맞나??!
					//}
				}
				//다음 이동을 반복하는 경우
				//빨간 구슬,파란 구슬 동일한 위치에 있을 때
				if(nRx == nBx && nRy == nBy) {
					if(d == 0) {//상 
						if(!Map[nRx+1][nRy].equals("#")) {
							if(Rx<Bx){nBx = nRx+1;}
							else {nRx = nRx+1;}
						}
					} else if(d==1) {//하 
						if(!Map[nRx-1][nRy].equals("#")) {
							if(Bx<Rx){nBx = nRx-1;}
							else {nRx = nRx-1;}
						}
					} else if(d==2) {//좌 
						if(!Map[nRx][nRy+1].equals("#")) {
							if(By<Ry) {nRy = nRy+1;}
							else {nBy = nRy+1;}
						}
					} else if(d==3) {//우 
						if(!Map[nRx][nRy-1].equals("#")) {
							if(Ry<By) {nRy = nRy-1;}
							else {nBy = nRy-1;}
						}
					}
				}
				if(!visited[nRx][nRy][nBx][nBy]) {
					visited[nRx][nRy][nBx][nBy] = true;
					q.add(new Point(nRx,nRy,nBx,nBy,cur.cnt+1));
					
				}
			}
		}	
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		Map = new String[N][M];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split("");
			for(int j=0;j<M;j++) {
				Map[i][j] = input[j];
				if(Map[i][j].equals("R")) {
					Rx = i;Ry=j;
					//System.out.println("Rx: "+Rx+"Ry: "+Ry);
				} 
				else if(Map[i][j].equals("B")) {
					Bx = i;By=j;
					//System.out.println("Bx: "+Bx+"By: "+By);
				} 
			}
		}
		ans = INF;
		bfs();
		if(ans == INF) {
			System.out.println(-1);
		}
		else{
			System.out.println(ans);
		}
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
