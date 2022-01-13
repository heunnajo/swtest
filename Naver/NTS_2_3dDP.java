package ss;
import java.util.*;
public class NTS_2_3dDP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int MOD = 1000000007;
		int[][][] d = new int[n+1][m+1][m+1];
		
		d[0][0][0] = 1;
		for(int i = 1;i<=n;i++) {
			for(int s = 1;s<=m;s++) {
				if(s>i*k) break;
				for(int os=1;os<=m;os++) {
					if(os>s) break;
					if(i%2 == 1) {
						for(int l=1;l<=k;l++) {
							if(s<l || os<l) break;
							d[i][s][os] += d[i-1][s-l][os-l];
							d[i][s][os] %= MOD;
						}
					} else {
						for(int l=1;l<=k;l++) {
							if(s<l) break;
							d[i][s][os] += d[i-1][s-l][os];
							d[i][s][os] %= MOD;
						}
					}
					
				}
			}	
		}
		System.out.println(d[n][m][m/2]);	
		
	}

}
