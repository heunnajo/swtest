package ss;
import java.io.*;
import java.util.*;

public class Resignation {
	static int n,ans,t[],p[];
	static void go(int idx,int sum) {
		//1.종료 조건
		//1-1.불가능한 경우
		if(idx > n) {
			return;
		}
		//1-2.정답 찾은 조건
		if(idx == n) {
			//정답 최댓값 도출 후 리턴!
			if(sum>ans) ans = sum;
			return;
		}
		//2.현재 선택
		//2-1.현재 선택O,다음 선택(재귀 호출)
		go(idx+t[idx],sum+p[idx]);//idx==n이 되면 종료처리하는데 왜 8인덱스 초과에러발생?!
		//2-2.현재 선택X,다음 선택(재귀 호출)
		go(idx+1,sum);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		t = new int[n];
		p = new int[n];
		ans = -1;//최댓값 갱신 위해 작은 값 넣어줌.
		for(int i =0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			t[i] = a; p[i] = b;
		}
		go(0,0);//0번일부터 선택을 하고 sum은 0으로 초기 실행하면 된다.
		//재귀함수 내부에서 0번일 선택O,선택X에 따른 처리를 할 것이기 때문이다.
		System.out.println(ans);
	}

}

//입력 확인 완료!
//		for(int i =0;i<n;i++) {
//			System.out.print(t[i]+" ");
//		}
//		System.out.println();
//		for(int i =0;i<n;i++) {
//			System.out.print(p[i]+" ");
//		}
//		System.out.println();