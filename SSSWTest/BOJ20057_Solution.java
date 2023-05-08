package boj;
//마법사 상어와 토네이도
import java.io.*;
import java.util.*;
public class BOJ20057_Solution {
	static int N,OutSand,Map[][];
	static int[] dx = {0,1,0,-1};//좌하우상.
	static int[] dy = {-1,0,1,0};
	static int[] moveCount = {1,1,2,2};
	static int[][] spreadX = {
			{-1,1,-2,-1,1,2,-1,1,0},	
			{-1,-1,0,0,0,0,1,1,2},
			{1,-1,2,1,-1,-2,1,-1,0},
			{1,1,0,0,0,0,-1,-1,-2}
	};
	static int[][] spreadY = {
			{1,1,0,0,0,0,-1,-1,-2},
			{-1,1,-2,-1,1,2,-1,1,0},
			{-1,-1,0,0,0,0,1,1,2},
			{1,-1,2,1,-1,-2,1,-1,0}
	};
		static int[] spreadRatio = {1,1,2,7,7,2,10,10,5};
		static void solve(int x,int y) {//토네이도 시작 위치.
		
			while(true) {//범위를 벗어나기 전까지 이동을 계속해서 반복한다.
				for(int d=0;d<4;d++) {//방향 : 좌하우상 순서.
					for(int mc = 0;mc<moveCount[d];mc++) {//현재 방향에 따라 이동 칸수 달라짐!
						//1칸씩 다음 칸으로 이동한다!
						//1.이동할 다음 위치 구한다.
						int nx = x+dx[d],ny = y+dy[d];
						if(nx<0 || nx>N-1 || ny<0 || ny>N-1) return;
						
						//2.이동한 위치 y에서 모래양 기억! y칸 모래가 확산되면 0 처리 빼먹음!! 
						int sand = Map[nx][ny];
						Map[nx][ny] = 0;
						int totalSpread = 0;
						//3.현재 방향에 따라 9개의 확산 좌표 구하고, 확산양 구하기
						for(int s=0;s<9;s++) {
							int sx = nx + spreadX[d][s];
							int sy = ny + spreadY[d][s];
							int spreadAmnt = (sand*spreadRatio[s])/100;
							//범위 밖이면 OutSand에 합산.
							if(sx<0 || sx>N-1 || sy<0 || sy>N-1) {
								OutSand += spreadAmnt;
							}
							else {//범위 안이면 해당 좌표 모래양에 합산.
								Map[sx][sy] += spreadAmnt;
							}
							totalSpread += spreadAmnt;
						}
						//4.알파칸 = sand - spreadTotal!!
						//좌방향 : x=x,y=y-1  상방향:x=x-1,y=y
						//우방향 : x=x,y=y+1  하방향:x=x+1,y=y
						int ax = nx+dx[d],ay = ny+dy[d];
						int alpha = sand - totalSpread;
						if(ax<0 || ax>N-1 || ay<0 || ay>N-1) {
							OutSand += alpha;
						}else {
							Map[ax][ay] += alpha;//합산해줘야하는데 값을 넣어줘서 틀림!
						}
						x = nx; y = ny;//nx,ny로 현재위치 값 갱신 안해서 틀림!
					}
				}
				for(int i=0;i<4;i++) {
					moveCount[i] += 2;
				}
			}
		}
		public static void main(String[] args) throws Exception{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				String[] input = br.readLine().split(" ");
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(input[j]);
				}
			}
			OutSand = 0;
			solve(N/2,N/2);
			System.out.println(OutSand);//밖으로 나간 모래양을 출력.
			br.close();
		}

	}
