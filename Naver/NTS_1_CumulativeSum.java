package ss;
import java.util.*;

public class NTS_1_CumulativeSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();//N: 상담시간 갯수
		int limit = 10000000;
		int[] cnt = new int[limit+1];
		
		//1.N개의 상담 시간 s,e 각각 1씩 더하고 빼준다.
		for(int i=0;i<N;i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			cnt[s] += 1; cnt[e] -= 1;
		}
		//2.누적합 구한다.
		for(int i=1;i<=limit;i++) {
			cnt[i] += cnt[i-1];
		}
		//3.정답 도출:0이 아닌 칸 갯수 
		int answer = 0;
		for(int i=1;i<=limit;i++) {
			if(cnt[i]>0) answer++;
		}
		System.out.println(answer);
	}

}
