package ss;
import java.util.*;
import java.io.*;
public class Eb_4_RGB {//greedy
	//스트링 값을 정수로 표현,구현할 때의 테크닉!원래 문자값<=>정수
	static char ch(char x,int cnt) {
		//x문자를 몇번 눌러서 변형시키는가?
		//3개중 반복되기 때문에 현재 문자를 정수로 먼저 변환한 다음,
		//누르는 횟수만큼 더해주고, 3으로 나눈 나머지가 RGB 값이 된다!!
		//새로운 하노이탑 구현 스킬과 굉장히 매우 유사하다.
		char[] arr = {'R','G','B'};
		int y = 0;
		if(x == 'R') y = 0;
		if(x == 'G') y = 1;
		if(x == 'B') y = 2;
		return arr[(y+cnt)%3];
	}
	static int solution(int n,int k,String bulbs_s) {
		int ans = 0;
		int[] cnt = new int[n+1];//인덱스 1부터 사용.
		char[] bulbs = bulbs_s.toCharArray();
		
		for(int i=0;i<n;i++) {
			if(i>0) cnt[i] += cnt[i-1];//i-1인덱스에 접근하기 때문에 i>0인 조건이 필요함!
			bulbs[i] = ch(bulbs[i],cnt[i]);//현재 cnt값으로 먼저 변환을 해준다!
			//그리고 난 후, R인지 아닌지에 따라 추갖적으로 더 눌러주는 것이 필요함!
			if(bulbs[i] == 'R') continue;//R이면 바로 다음 전구로 넘어감!
			else {//cnt 배열 이용!
				if(i+k>=n+1) return -1;//k 크기 윈도우가 전구 전체 크기를 넘어가면 불가능 
				int click = 0;//누를 횟수를 카운팅
				
				if(bulbs[i] == 'B') click = 1;//B이면 1번 눌러서 R이 됨 
				else click = 2;//G이면 2번 눌러서 R이 됨 
				cnt[i] += click;
				cnt[i+k] -= click;//k만큼 떨엊진 칸은 click만큼 빼줌.
				ans += click;//누른 횟수 정답 저장!
			}
		}
		
		return ans;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String bulbs = br.readLine();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		System.out.println(solution(n,k,bulbs));
	}

}
