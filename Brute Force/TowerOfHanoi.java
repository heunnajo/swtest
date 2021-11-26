package ss;
import java.util.*;
import java.math.BigInteger;
public class TowerOfHanoi {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sb = new StringBuilder();
		
		BigInteger arr[] = new BigInteger[101];
		arr[1] = BigInteger.valueOf(1);
		for(int i=2;i<=100;i++) {
			arr[i] = arr[i-1].multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
		}
		sb.append(arr[N]+"\n");
		if(N <=20) {
			hanoi(N,1,3,2);//시작점:1 도착점:3 경유지:2
		}
		System.out.print(sb);
	}
	static void hanoi(int N,int start,int to,int via) {
		if(N==1) {
			sb.append(start+" "+to+"\n");
			return;
		} else {
			hanoi(N-1,start,via,to);
			sb.append(start+" "+to+"\n");
			hanoi(N-1,via,to,start);
		}
	}
	static void move(int N,int start,int to) {
		System.out.println(start+" "+to);
	}
}
