import java.io.*;
import java.util.*;
public class LadderControl_2nd {
	static int ans,N,M,H,Map[][];
	static final int INF = Integer.MAX_VALUE;
	static int START = 1;
	static int END = 2;
	static int go(int cnt,int pos) {
		//1.재귀 종료 :정답 찾은 경우 + 불가능한 경우
		//1-1.정답 찾은 경우:현재의 cnt를 ans에 저장하고 바로 리턴.
		if(cnt == 3 || pos >= H*N) {
			if(check()) {
				return cnt;
			}
			return INF;
		}
		//2.현재 위치에서 사다리 위치 선택
		int ret = INF;
		int curX = pos/N; int curY = pos%N;
		if(curY<=N-2 && Map[curX][curY] == 0 && Map[curX][curY+1] == 0) {
			Map[curX][curY] = START; Map[curX][curY+1] = END;
			ret = Math.min(go(cnt+1,pos+2),ret);
			Map[curX][curY] = Map[curX][curY+1] = 0;//원복처리
		}
		ret = Math.min(go(cnt,pos+1),ret);
		return ret;
	}
	static boolean check() {
		int col,row;//난 처음 시작좌표를 start에 저장하고 반복변수인 j로 +- 했는데 정답은 그 반대였다.
		for(int j=0;j<N;j++) {
			col = j; row = 0;
			while(row<H) {
				if(Map[row][col] == START) col++;
				else if(Map[row][col] == END) col--;
				row++;
			}
			if(j!=col) return false;
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
		ans = go(0,0);//최솟값을 리턴
		if(ans == INF) System.out.println(-1);
		else System.out.println(ans);
	}

}