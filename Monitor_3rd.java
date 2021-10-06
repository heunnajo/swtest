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
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
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
		go(0);//0번째 cctv부터 방향선택
		System.out.println(ans);//최소크기 사각지대 출력
		
		
	}
	static void go(int index) {
		//종료 조건
		if(index == ccList.size()) {
			//사각지대 갯수 카운팅? : 각 cctv 방향선택할 때마다 감시영역 갯수 계산해서 다음으로 넘기는 방법?
			
			return;
		}
		//현재 cctv 방향 선택, 다음 경우 호출, 원복
		CC cur = ccList.get(index);
		int id = cur.id; int cx = cur.x; int cy = cur.y;
		
		if(id == 1) {//4가지
			for(int dir = 0;dir<4;dir++) {
				monitor(cx,cy,dir);
				go(index+1);
				rewind();
			}
		}else if(id == 2) {//2가지
			
		} else if(id == 3) {//4가지
			
		} else if(id == 4) {//4가지
			
		} else if(id == 5) {//상하좌우 한가지.
			
		}
	}
	//현재 cctv 선택한 방향에 따라 감시영역 처리, 감시영역 갯수 증가
	//감시하는 카메라의 위치와 선택한 방향을 알아야함.
	static void monitor(int x,int y,int dir) { 
		if(dir == 0) {//상
			
		} else if(dir == 1) {//하
			
		} else if(dir == 2) {//좌
			
		} else {//우
			
		}
	}
	static void rewind() {
		
	}
}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Office[i][j]+" ");
//	}
//	System.out.println();
//}