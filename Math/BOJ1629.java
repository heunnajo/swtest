//곱셈
package ss;
import java.io.*;
import java.util.*;

public class BOJ1629 {
	static long C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(pow(A,B));
	}
	static long pow(long A, long exp) {
		//1.재귀 종료 조건
		if(exp == 1) return A % C;
		
		//2.현재 숫자의 제곱 : 재귀
		long temp = pow(A,exp/2);
		
		if(exp % 2 == 1) {
			return (temp * temp % C) * A % C;
		}
		return temp*temp%C;
	}
}
