package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
public class PinballGame {
	static int ans,T,N,Map[][];
	static int startX,startY,blackX,blackY;
	static Pnt ball;
	static HashMap<Integer,LinkedList<Pnt>> warmB;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pnt{
		int x,y,dir;
		Pnt(int x,int y){
			this.x = x;
			this.y = y;
		}
		Pnt(int x,int y,int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	static void game() {
		int cx = ball.x,cy = ball.y,dir = ball.dir;
		int nx,ny;
		nx = ball.x+dx[ball.dir];
		ny = ball.y+dy[ball.dir];
		//블록 번호 1~5번 별로 방향전환,블랙홀일 때 종료
		switch(Map[nx][ny]) {
			case -1:return;
			case 0:nx+=dx[dir];ny+=dy[dir];
					ans++;break;
			case 1:if(dir==1) {dir=3;}
				else if(dir == 2) {dir=0;}
				else {dir=(dir+2)%4;}
				ans++;break;
			case 2:if(dir==0) {dir=3;}
				else if(dir==1) {dir=2;}
				else {dir=(dir+2)%4;}
				ans++;break;
			case 3:if(dir==3) {dir=2;}
				else if(dir==0) {dir=1;}
				else {dir=(dir+2)%4;}
				ans++;break;
			case 4:if(dir==3) {dir=0;}
				else if(dir==2) {dir=1;}
				else {dir=(dir+2)%4;}
				ans++;break;
			case 5:dir=(dir+2)%4;
				ans++;break;
		}
		//벽에 부딪혔을 때
		if(isOut(nx,ny)) {
			dir=(dir+2)%4;
			ans++;
		}
		//방향 전환 후 그 방향으로 1칸 이동
		ball.x += dx[dir];ball.y += dy[dir];
		
		//원홀일 때:이동할 방향(nx,ny)에 대해서 조건 판단 후 위치갱신하는 것이므로 마지막에 구현.
		//만약 이 로직이 방향 전환 후 위치 갱신하는 로직 앞에 있게 되면, 이미 다른 웜볼위치로 이동시켜줬는데
		//바뀐 방향으로 다시 이동해버리기 때문.
		if(Map[nx][ny]>=6 && Map[nx][ny]<=10) {
			LinkedList<Pnt> list = warmB.get(Map[nx][ny]);
			for(Pnt p:list) {
				if(p.x != nx) ball.x = nx = p.x;ball.y = ny = p.y;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			warmB = new HashMap<>();
			startX = startY = 0;
			//초기 위치 (0,0) 초기 방향 0에서 시작.
			ball = new Pnt(startX,startY,0);
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if(Map[i][j] == -1) {blackX = i;blackY = j;}
					else if(Map[i][j]>=6 && Map[i][j]<=10) {//웜볼인 경우 맵에 추가!
						Pnt p = new Pnt(i,j);//map에 없으면 추가.있으면 vale 리스트에 추가
						if(warmB.get(Map[i][j]) == null) {
							LinkedList<Pnt> list = new LinkedList<>();
							list.add(p);
							warmB.put(Map[i][j], list);
						} else {warmB.get(Map[i][j]).add(p);}
					}
				}
			}
			ans = 0;
			game();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}

//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}