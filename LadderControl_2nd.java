package ss;
import java.io.*;
import java.util.*;
public class LadderControl_2nd {
	static int ans,N,M,H,Map[][];
	static final int INF = Integer.MAX_VALUE;
	static int START = 1;
	static int END = 2;
	static void go(int cnt,int pos) {
//	static int go(int cnt,int pos) {
		//1.재귀 종료
		//1-1.정답 찾은 경우:현재의 cnt를 ans에 저장하고 바로 리턴.
		if(check()) {
			ans = cnt;
			return;
		}
		//1-2.불가능한 경우
//		if(pos>N*H-2) return;
		if(pos>=N*H) return;
		if(cnt >= 3) {
			if(check()) {
				ans = cnt;
				return;
			}
			else return;//-1?0?
		}
		//2.현재 위치에서 사다리 위치 선택
		int curX = pos/N; int curY = pos%N;
		if(Map[curX][curY] == 0 && Map[curX][curY] == 0 && curY<=N-2) {
			Map[curX][curY] = START; Map[curX][curY+1] = END;
			go(cnt+1,pos+2);
			Map[curX][curY] = Map[curX][curY] = 0;//원복처리
		}
		go(cnt,pos+1);
	}
	//사다리 추가한 후 결과 체크
	static boolean check() {
		int start,row;
		for(int j=0;j<N;j++) {
			start = j; row = 0;
			while(row<H) {
				if(Map[row][j] == START) j++;
				if(Map[row][j] == END) j--;
				row++;
			}
			if(j==start) continue;
			else if(j!= start) return false;
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		Map = new int[H][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			Map[s][e] = START; Map[s][e+1] = END;
		}
		ans = INF;
		go(0,0);
		if(ans == INF) ans = -1;
		System.out.println(ans);
		
	}

}
//for(int i=0;i<H;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
