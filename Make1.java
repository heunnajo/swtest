package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Make1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		while(N!=1) {
			cnt++;
			System.out.println("연산 전 N:"+N);
			N -= 1;
			if(N%3 == 0) {
				N = N/3;
			} else if(N%2 == 0) {
				N = N/2;
			}
			System.out.println("연산 후 N:"+N);
			
		}
		System.out.println(cnt);
	}

}
