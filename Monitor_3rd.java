package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
public class Monitor_3rd {
	static int ans,monitor,N,M,Office[][];//N행
	static class CC{
		int x,y,id;
		CC(int x,int y,int id){
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}
	static LinkedList<CC> ccList;
	static int[] dx = {-1,0,1,0};//시계방향으로 구현!
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Office = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Office[i][j] = Integer.parseInt(st.nextToken());
				if(Office[i][j]>0) monitor++;//감시영역
				if(Office[i][j]>0 && Office[i][j]<6) {
					ccList.add(new CC(i,j,Office[i][j]));
				}
			}
		}
		go(0,0);//0번째 cctv부터 방향선택
		System.out.println(ans);//최소크기 사각지대 출력
		
	}
	static void go(int index,int sum) {//index:현재 cctv id,sum: 지금까지 선택한 방향으로 감시영역 갯수 
		//종료 조건
		if(index == ccList.size()) {
			ans = Math.min(ans, N*M-sum);
			return;
		}
		//현재 cctv 방향 선택, 다음 경우 호출, 원복
		int tSum = 0;//각 경우마다 감시영역 갯수를 저장하는 변수
		CC cur = ccList.get(index);
		int id = cur.id; int cx = cur.x; int cy = cur.y;
		
		if(id == 1) {//4가지
			for(int dir = 0;dir<4;dir++) {
				tSum = monitor(cx,cy,dir);
				go(index+1,sum+tSum);
				tSum -= rewind(cx,cy,dir);
			}
		}else if(id == 2) {//2가지
			for(int dir=0;dir<2;dir++) {
				tSum = monitor(cx,cy,dir);
				tSum += monitor(cx,cy,(dir+2)%4);//+2로 반대 방향 전환쉽게 하기 위해 방향 델타어레이 바꿔야하려나
				go(index+1,sum+tSum);
				tSum -= rewind(cx,cy,dir);
			}
		} else if(id == 3) {//4가지
			for(int dir=0;dir<4;dir++) {
				tSum = monitor(cx,cy,dir);
				tSum += monitor(cx,cy,(dir+1)%4);
				go(index+1,sum+tSum);
				tSum -= rewind(cx,cy,dir);
			}
		} else if(id == 4) {//4가지
			for(int dir=0;dir<4;dir++) {
				tSum = monitor(cx,cy,dir);
				tSum += monitor(cx,cy,(dir+1)%4);
				tSum += monitor(cx,cy,(dir+2)%4);
				go(index+1,sum+tSum);
				tSum -= rewind(cx,cy,dir);
			}
		} else if(id == 5) {//상하좌우 모든 방향으로 한가지.
			for(int dir = 0;dir<4;dir++) {
				tSum += monitor(cx,cy,dir);
			}
			go(index+1,tSum);
			for(int dir = 0;dir<4;dir++) {
				tSum -= rewind(cx,cy,dir);
			}
		}
	}
	//현재 cctv 선택한 방향에 따라 감시영역 처리, 감시영역 갯수 증가
	//감시하는 카메라의 위치와 선택한 방향을 알아야함.
	//dir 방향으로 계속해서 감시 마킹한다. 벽이거나 범위를 벗어나면 감시 마킹 종료한다!
	static int monitor(int x,int y,int dir) { 
		int cnt=0;
		while(true) {
			x += dx[dir]; y += dy[dir];
			if(isOut(x,y) || Office[x][y] == 6) break;
			else {
				
				if(Office[x][y] >= 7) Office[x][y]++;
				else if(Office[x][y] == 0) {Office[x][y] = 7;cnt++;}
			}
		}
		return cnt;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	static int rewind(int x,int y,int dir) {//monitor와 마찬가지로 x,y위치 시작으로 dir 방향으로 원복 하면 된다.
		int remove=0;
		while(true) {
			x += dx[dir]; y += dy[dir];
			if(isOut(x,y) || Office[x][y] == 6) break;//멈추는 게 아니라 다음 반복문 실행?!
			else {
				if(Office[x][y] > 7) Office[x][y]--;
				else if(Office[x][y] == 7) {Office[x][y] = 0;remove++;}
			}
		}
		return remove;//원복시킨 칸 갯수 카운팅
	}
}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Office[i][j]+" ");
//	}
//	System.out.println();
//}