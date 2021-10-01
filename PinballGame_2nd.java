package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
public class PinballGame_2nd {
	static int ans,T,N,Map[][];
	static int startX,startY,blackX,blackY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] wB;
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
	static int game(Pnt start) {
		int score = 0;
		int nx = start.x,ny = start.y,dir = start.dir;
		
		while(true) {
			nx += dx[dir]; ny += dy[dir];
			//범위 밖
			if(isOut(nx,ny)) {
				score++;dir=(dir+2)%4;
				continue;
			}
			//시작위치
			if(nx == start.x && ny == start.y) return score;
			//블랙홀
			if(Map[nx][ny] == -1) return score;
			
			int type = Map[nx][ny];
			
			//블록 번호 1~5번 별로 방향전환,블랙홀일 때 종료
			switch(type) {
				case 0:
					break;
				case 1:
					if(dir==1) {dir=0;}
					else if(dir == 2) {dir=3;}
					else {dir=(dir+2)%4;}
					score++;break;
				case 2:
					if(dir==0) {dir=3;}
					else if(dir==1) {dir=2;}
					else {dir=(dir+2)%4;}
					score++;break;
				case 3:
					if(dir==3) {dir=2;}
					else if(dir==0) {dir=1;}
					else {dir=(dir+2)%4;}
					score++;break;
				case 4:
					if(dir==3) {dir=0;}
					else if(dir==2) {dir=1;}
					else {dir=(dir+2)%4;}
					score++;break;
				case 5:
					dir=(dir+2)%4;
					score++;break;
				default:
					if(wB[type][0]==nx && wB[type][1] == ny) {
						wB[type][2] = nx; wB[type][3] = ny;
					} else {
						nx = wB[type][0]; ny = wB[type][1];
					}
					break;
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
			wB = new int[11][4];
			
			for(int i=0;i<11;i++) {
				for(int j=0;j<4;j++) {wB[i][j] = -1;}
			}
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
//					if(Map[i][j]>=6 && Map[i][j]<=10) {//웜볼인 경우 위치 저장!
					if(Map[i][j]>5) {//웜볼인 경우 위치 저장!
						if(wB[Map[i][j]][0] == -1) {
							wB[Map[i][j]][0] = i;wB[Map[i][j]][1] = j;
						} else {
							wB[Map[i][j]][2] = i;wB[Map[i][j]][3] = j;
						}
					}
				}
			}
			ans = -1;//최댓값 갱신을 위해 일부러 작은 값을 할당!
			int score = -1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int d=0;d<4;d++) {
						Pnt start = new Pnt(i,j,d);
						score = game(start);
						ans = ans < score? score:ans;
					}
				}
			}
			
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