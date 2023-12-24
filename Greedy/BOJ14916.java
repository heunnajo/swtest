//거스름돈
import java.util.*;
import java.io.*;
public class BOJ14916 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int cnt = Integer.MAX_VALUE;
		
		int n = 0;
		int tmpCnt = 0;
		for(int i=0;i*5<=input;i++) {
			n = input;
			tmpCnt = 0;//초기화 안해줌...
			tmpCnt += i;
			n = n- (5*i);
			
			//2원 사용해서 거슬러준다!
			tmpCnt += (n/2);
			n = n- 2*(n/2);
			if(n == 0) {
				if(cnt > tmpCnt) cnt = tmpCnt;
			}
		}
		if(cnt == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(cnt);
	}

}
