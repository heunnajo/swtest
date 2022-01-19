package ss;
import java.io.*;
public class ChameleonSubstring {
	private static class KMP{
		private final int[] fail;
		private final String s;
		
		public KMP(String s) {
			this.s = s;
			fail = new int[s.length()];
			fail();
		}
		private void fail() {
			int j=0;
			for(int i=1;i<s.length();i++) {
				while(j>0 && s.charAt(i) != s.charAt(j))
					j = fail[j-1];
				if(s.charAt(i) == s.charAt(j))
					fail[i] = ++j;
				else
					j = 0;
					//기본적으로 fail[i] = 0이기 때문에 굳이 하지 않는다.
					//여기서 j=0으로 만드는 건, 같다가 중간에 끝겼다는 것. 따라서 j는 1부터 다시 시작해야함~!
					//따라서 j=0으로 초기화해준다!
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		KMP kmp = new KMP(br.readLine());
		int len = kmp.s.length();
		int[] cnt = new int[len+1];//길이에 대해 횟수 카운팅할 것이기 때문에 len+1 크기로 생성!
		
		//kmp의 fail에 따라 cnt를 완성한다.
		//cnt 배열에서 2번 이상 나온 부분 문자열에 대해 정답을 찾는다!=>cnt값이 1이상
		for(int i=0;i<len-1;i++) {
			cnt[kmp.fail[i]]++;
		}
		for(int i=len;i>0;i=kmp.fail[i-1]) {//i=kmp.fail[i-1] 무슨 의미!?
			if(cnt[i]>=1) {
				System.out.println(kmp.s.substring(0,i));
				return;
			}
		}
		System.out.println(-1);
	}

}
