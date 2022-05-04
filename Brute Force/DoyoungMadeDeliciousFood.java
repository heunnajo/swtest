//BOJ#2961 도영이가 만든 맛있는 음식
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoyoungMadeDeliciousFood {
	static int[] S,B;//S:신맛*, B:쓴맛+
	static int N,ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        S = new int[N];
        B = new int[N];
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	S[i] = Integer.parseInt(st.nextToken());
        	B[i] = Integer.parseInt(st.nextToken());
        }
        if(N == 1) {
        	System.out.println(Math.abs(S[0]-B[0]));
        	System.exit(0);
        }
        ans = 1000000000;//어떤 요리의 신맛/쓴맛 최댓값은 10억 
        go(0,1,0);
        System.out.println(ans);
	}
	
	static void go(int idx,int sSum,int bSum) {
		//1.재귀 종료 : N개 다 고른 경우
		if(idx == N) {
			int curAns = Math.abs(sSum-bSum);
			if(bSum != 0 && ans>curAns) {
				ans = curAns;
			}
			return;
		}
		//2.현재 선택, 다음 경우 호출
		go(idx+1,sSum*S[idx],bSum+B[idx]);
		go(idx+1,sSum,bSum);
	}

}