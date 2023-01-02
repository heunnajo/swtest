package boj;
import java.util.*;
import java.io.*;
//ACM 호텔(라인 기출과 흡사)
public class BOJ10250 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        int h,w,n;
        int num;
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(t-- >0) {
        	st = new StringTokenizer(br.readLine());
        	h = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	n = Integer.parseInt(st.nextToken());
        	
        	num = solve(h,w,n);
        	sb.append(num).append("\n");
        }
        
        System.out.print(sb.toString());
		
	}
	static int solve(int h,int w,int n) {
		int num = 0;
		for(int j=0;j<w;j++) {
			for(int i=h-1;i>=0;i--) {
				num++;
				if(num == n) {return (h-i)*100 + (j+1);}
			}
		}
		return -1;
	}

}
