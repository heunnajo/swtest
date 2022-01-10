package ss;
import java.util.*;
public class Tiling2xn2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] d = new int[n+3];//n이 1이라도 초기값으로 d[3]까지 구할 것이기 때문
		
		d[1] = 1;d[2] = 3;d[3] = 5;
		for(int i=4;i<=n;i++) {
			d[i] = d[i-1]%10007+2*d[i-2]%10007;
		}
//		for(int i=1;i<=n;i++) {
//			System.out.print(d[i]+" ");
//		}
//		System.out.println();
		
		System.out.println(d[n]%10007);
	}

}
