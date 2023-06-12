import java.io.*;
import java.util.*;
//감시
public class BOJ15683_Solution {
	static int ans,N,M,Map[][],tmpMap[][];//N = 행, M = 열
	static int ccCnt,moniterArea;
	static class CCTV{
		int x,y,id;
		CCTV(int x,int y,int id){
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}
	static CCTV[] CCTVs;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	
	static void go(int index,int sum) {
		if(index == ccCnt) {
			ans = Math.min(ans, N*M-sum);
			return;
		}
		//index번째 CCTV를 조회하여 감시방향 선택한다!
		CCTV cur = CCTVs[index];int x = cur.x, y = cur.y, id = cur.id;
		int tSum = 0;//index 번째 cctv 방향 선택함으로써 감시영역 갯수를 저장한다.
		if(id == 1) {//4가지 경우의 수 
			for(int dir=0;dir<4;dir++) {
				tSum = fill(x,y,dir);
				go(index+1,sum+tSum);
				rewind(x,y,dir);
			}
		} else if(id == 2) {//2가지 경우의 수 
			for(int dir=0;dir<2;dir++) {
				tSum = fill(x,y,dir);
				tSum += fill(x,y,(dir+2)%4);
				go(index+1,sum+tSum);
				rewind(x,y,dir);
				rewind(x,y,(dir+2)%4);
			}
		} else if(id == 3) {//4가지 경우의 수 
			for(int dir=0;dir<4;dir++) {
				tSum = fill(x,y,dir);
				tSum += fill(x,y,(dir+1)%4);
				go(index+1,sum+tSum);
				rewind(x,y,dir);
				rewind(x,y,(dir+1)%4);
			}
		} else if(id == 4) {//4가지 경우의 수 
			for(int dir=0;dir<4;dir++) {
				tSum = fill(x,y,dir);
				tSum += fill(x,y,(dir+1)%4);
				tSum += fill(x,y,(dir+2)%4);
				go(index+1,sum+tSum);
				rewind(x,y,dir);
				rewind(x,y,(dir+1)%4);
				rewind(x,y,(dir+2)%4);
			}
		} else if(id == 5) {//1가지 경우의 수 :모든 방향을 다 선택한다.
			for(int dir=0;dir<4;dir++) {
				if(dir==0) tSum = fill(x,y,dir);
				else tSum += fill(x,y,dir);
			}
			go(index+1,sum+tSum);
			for(int dir=0;dir<4;dir++) {
				rewind(x,y,dir);
			}
		}
	}
	static void rewind(int x,int y,int dir) {
		int nx = x, ny = y;
		
		while(true) {
			nx += dx[dir]; ny += dy[dir];
			if(isOut(nx,ny) || Map[nx][ny] == 6) return;
			
			if(Map[nx][ny]>8) Map[nx][ny]--;
			else if(Map[nx][ny]==8){//8인 경우 0으로 초기화 
				Map[nx][ny] = 0;
			}
		}
	}
	static int fill(int x,int y,int dir) {
		//벽6을 만날 때까지 감시영역으로 마킹하고, 갯수 세서 리턴한다!
		int sum = 0;
		int nx = x, ny = y;
		
		while(true) {
			nx += dx[dir]; ny += dy[dir];
			if(isOut(nx,ny) || Map[nx][ny] == 6) return sum;
			else {
				if(Map[nx][ny]>=8) Map[nx][ny]++;
				else if(Map[nx][ny]==0) {
					Map[nx][ny] = 8;
					sum++;//초기에 마킹할 때만 갯수 센다!!!!
				}
				
			}
		}
//		return sum;
	}
	static boolean isIn(int x,int y) {
		return x>= 0 && x<N && y>= 0 && y<M;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		CCTVs = new CCTV[8];
		ccCnt = moniterArea = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j]>0) {
					moniterArea++;
					if(Map[i][j]<6) {
						CCTVs[ccCnt++] = new CCTV(i,j,Map[i][j]);
					}
				}
			}
		}
		ans = Integer.MAX_VALUE;
		go(0,moniterArea);
		System.out.println(ans);
	}
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}

}