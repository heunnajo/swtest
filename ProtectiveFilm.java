package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class ProtectiveFilm {
	static int ans,T,D,W,K,Film[][],temp[][];//D:행,W:열,K:합격 기준
	static boolean test() {
		for(int j=0;j<W;j++) {
			int sum = 1;int type = temp[0][j];boolean flag = false;
			for(int i=1;i<D;i++) {
				if(type == temp[i][j]) sum++;
				else {type = temp[i][j];sum=1;}
				
				if(sum==K) {flag = true;break;}//더이상 다음행으로 진행X
			}
			if(!flag) return false;
		}
		return true;//디폴트로 그냥 true 리턴하는 듯.
	}
	static void solve(int idx,int cnt) {
		//정답 찾은 경우
		if(idx==D) {
			if(test()) {
				ans = ans > cnt?cnt:ans;
			}
			return;
		}
		
		//1.선택X
		solve(idx+1,cnt);
		//2.A(0) 선택
		for(int j=0;j<W;j++) temp[idx][j] = 0;
		solve(idx+1,cnt+1);
		//3.B(1) 선택
		for(int j=0;j<W;j++) temp[idx][j] = 1;
		solve(idx+1,cnt+1);
		//원복!
		for(int j=0;j<W;j++) temp[idx][j] = Film[idx][j];
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
			temp = new int[D][W];
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Film[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			
			if(test()) {ans = 0;}
			else {solve(0,0);}
			
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}