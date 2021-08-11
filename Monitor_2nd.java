package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Monitor_2nd {
	static int ans,sum,ccCnt,N,M,Map[][];
	static class CCTV{
		int x,y;
		CCTV(int x,int y){
			this.x = x;this.y = y;;
		}
	}
	static CCTV[] ccList = new CCTV[8];
	static int[] dx = {-1,0,1,0};//상 우 하 좌.
	static int[] dy = {0,1,0,-1};
	static void make_dir(int index,int sum) {//index번째 CCTV 방향 설정 : CCTV 번호별로 선택하는 방향 갯수 달라진다!!
		//정답 찾은 경우.
		if(index == ccCnt) {
			//사각지대 갯수 구하고, 최솟값을 선택한다!
			ans = Math.min(ans, N*M-sum);
			return;
		}
		int tSum = 0;//현재 index번째 CCTV 선택할 때 감시영역 갯수.재귀함수 호출할 때 넘겨줌으로써 CCTV 방향 다 설정했을 때 이 값을 N*M에서 빼줌으로써 사각지대 크기 구할 수 있다!
		CCTV cur = ccList[index];

		//CCTV #1 : 4가지 방향 설정.
		if(Map[cur.x][cur.y]==1) {
			for(int i=0;i<4;i++) {
				tSum = cctv(cur.x,cur.y,i);//1.선택
				make_dir(index+1,sum+tSum);//2.재귀호출
				rollback(cur.x,cur.y,i);//3.원복
			}
		//CCTV #2 : 2가지 방향 설정.
		} else if(Map[cur.x][cur.y]==2) {
			for(int i=0;i<2;i++) {
				tSum = cctv(cur.x,cur.y,i);//1.선택
				//tSum += cctv(cur.x,cur.y,i+2>=4?i-2:i+2);어차피 i는 0,1까지 2번만 반복하기 때문에. 4를절대초과하지 않는다.
				tSum += cctv(cur.x,cur.y,i+2);
				make_dir(index+1,sum+tSum);//2.재귀
				rollback(cur.x,cur.y,i);//3.원복
				//rollback(cur.x,cur.y,i+2>=4?i-2:i+2);
				rollback(cur.x,cur.y,i+2);
			}
		//CCTV #3 : 2가지 방향 설정.
		}else if(Map[cur.x][cur.y]==3) {
			for(int i=0;i<4;i++) {
				tSum = cctv(cur.x,cur.y,i);//1.선택
				tSum += cctv(cur.x,cur.y,i+1>=4?i-3:i+1);
				make_dir(index+1,sum+tSum);//2.재귀
				rollback(cur.x,cur.y,i);//3.원복
				rollback(cur.x,cur.y,i+1>=4?i-3:i+1);
			}
		//CCTV #4 :전체 4가지 방향 중 3가지 방향 설정.가각 4개의 경우의 수가 있으므로.
		} else if(Map[cur.x][cur.y]==4) {
			for(int i=0;i<4;i++) {
				tSum = cctv(cur.x,cur.y,i);
				tSum += cctv(cur.x,cur.y,i+1>=4?i-3:i+1);
				tSum += cctv(cur.x,cur.y,i+2>=4?i-2:i+2);
				make_dir(index+1,sum+tSum);
				rollback(cur.x,cur.y,i);
				rollback(cur.x,cur.y,i+1>=4?i-3:i+1);
				rollback(cur.x,cur.y,i+2>=4?i-2:i+2);
			}
		//CCTV #5 :4가지 방향 모두 설정.
		} else if(Map[cur.x][cur.y]==5) {
			for(int i=0;i<4;i++) {
				if(i==0) {//1.선택
					tSum = cctv(cur.x,cur.y,i);
				} else {
					tSum += cctv(cur.x,cur.y,i);
				}
			}
			make_dir(index+1,sum+tSum);
			for(int i=0;i<4;i++) {
				rollback(cur.x,cur.y,i);
			}
		}
	}
	static int cctv(int nx,int ny,int dir) {//감시영역 마킹&카운팅, 리턴.
		int sum=0;
		//nx = nx + dx[dir];ny = ny+dy[dir];
		while(true) {//nx값이 범위내라면 while문 내에서 바뀌지가 않으니까 무한루프에 빠진다!!
			nx = nx + dx[dir];ny = ny+dy[dir];
			if(nx<0 || ny<0|| nx>=N || ny>=M || Map[nx][ny]==6) break;
			else {
				if(Map[nx][ny]>=8) {Map[nx][ny]++;}
				else if(Map[nx][ny]==0) {
					Map[nx][ny] = 8;
					sum++;
				}
			}
		}
		return sum;
	}
	static void rollback(int nx,int ny,int dir) {//원상복구:dir 방향에 대해 감시 영역 마킹 된 칸들 다 원상복구.
		//nx = nx + dx[dir];ny = ny+dy[dir];
		while(true) {
			nx = nx + dx[dir];ny = ny+dy[dir];
			if(nx<0 || ny<0|| nx>=N || ny>=M || Map[nx][ny]==6) break;
			else {
				if(Map[nx][ny] > 8) {Map[nx][ny]--;}
				else if(Map[nx][ny] == 8) {Map[nx][ny] = 0;}
			}
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" "); 
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][M];
		ans = Integer.MAX_VALUE;ccCnt=0;
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" "); 
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j] != 0 && Map[i][j]<6) {//1,2,3,4,5 CCTV 객체 생성 후 배열에 저장, sum갯수 증가.
					ccList[ccCnt++] = new CCTV(i,j);
					sum++;
				} else if(Map[i][j]==6) {//6인 경우 sum 갯수 증가.
					sum++;
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		init();//전역 변수, 객체 초기화.
		
		//System.out.println("init()종료 후 make_dir 들어갑니다.");
		make_dir(0,sum);
		System.out.println(ans);
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
//System.out.println();