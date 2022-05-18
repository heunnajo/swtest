//퇴사
import java.util.*;
import java.io.*;
public class BOJ14501 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];
        int[] d = new int[n + 2];
        
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            t[i] = Integer.valueOf(input[0]);
            if (n + 1 < t[i] + i) {
            	p[i] = 0;
            } else p[i] = Integer.parseInt(input[1]);
        }
        
        d[n] = p[n];
        for(int i=n-1;1<=i;i--) {
        	if(t[i]+i-1 <= n) {
        		d[i] = Math.max(d[t[i]+i]+p[i], d[i+1]);
        	} else d[i] = d[i+1];
        }
        
        int ans = 0;
        for (int i = 1; i <= n; i++) {
        	ans = Math.max(d[i], ans);
        }

        System.out.println(ans);
	}

}
