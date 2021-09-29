package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class ProtectiveFilm {
	static int ans,T,D,W,K,Film[][];//D:행,W:열,K:합격 기준
	static boolean test(int[][] film) {
		int sum = 0;
		for(int j=0;j<W;j++) {
			for(int i=0;i<D-1;i++) {
				if(film[i][j] == film[i+1][j]) sum++;
			}
		}
		if(sum>=K) return true;
		return false;
	}
	static void solve(int idx,int chemi,int cnt,int[][] film) {
		//정답 찾은 조건. D개 모두 안해도 정답 찾는 경우.
		if(test(film)) {//현재 보호필름이 조건 만족할 때만 ans 최솟값 갱신하는데...
			ans = Math.min(ans, cnt);
			return;
		}
		//1.재귀 종료
		if(idx>=D) {
			if(test(film)) {
				ans = Math.min(ans, cnt);
			}
			return;//테스트에 통과할 때만 정답으로 갱신시킨다.
		}
		
		//초기 실행:현재 idx행에 약X/약품A/약품B 선택하고 재귀호출해야함.
		if(chemi==10) {
			solve(idx+1,2,cnt,changeTo(film,idx,2));
			solve(idx+1,0,cnt+1,changeTo(film,idx,0));
			solve(idx+1,1,cnt+1,changeTo(film,idx,1));
		}
		//현재 idx행 선택 : changeTo 메서드로 처리한 후 변형된 배열을 전달!
		else {
			solve(idx+1,2,cnt,changeTo(film,idx,2));
			solve(idx+1,0,cnt+1,changeTo(film,idx,0));
			solve(idx+1,1,cnt+1,changeTo(film,idx,1));
		}
	}
	//현재의 보호필름 curFilm의 idx행만 chemi로 바꾼다.
	//chemi=2:바꾸지 않는다.curFilm 그대로
	//chemi!=2 : chemi로 바꾼다.
	static int[][] changeTo(int[][] curFilm,int idx,int chemi){
		//tmp배열을 현재의 map으로 모두 복사
		//idx행만 chemi에 따라 구현.
		int tmp[][] = new int[D][W];
		for(int i=0;i<D;i++) {
			System.arraycopy(curFilm, 0, tmp, 0, W);
		}
		for(int j=0;j<W;j++) {
			tmp[idx][j] = chemi==2? Film[idx][j]:chemi;
		}
		return tmp;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());//두께=행
			W = Integer.parseInt(st.nextToken());//가로=열
			K = Integer.parseInt(st.nextToken());//K=합격 기준.연속 특성 갯수
			
			Film = new int[D][W];
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			solve(0,10,0,new int[D][W]);//초기 호출&실행 시 chemi는? 초기실행 따로처리해야하나!
			//2번재 인자 chemi는 이전 함수에서 선택한 약품번호 의미하는데 초기에는 없다.
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}
//for(int i=0;i<D;i++) {
//	for(int j=0;j<W;j++) {
//		System.out.print(Film[i][j]+" ");
//	}
//	System.out.println();
//}