package boj;
import java.io.*;
import java.util.*;

public class SMC_Q1 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] dir = new int[N];
        
        String[] input = br.readLine().split(" ");
        for(int i=0;i<N;i++) {
        	dir[i] = Integer.parseInt(input[i]);
        }

        int l = 0;int r = 0;
        int[] cnt = new int[3];
        int Ans = 0; int tmpAns = 0;
        while(l<=r && r<N) {
        	cnt[dir[r]]++;
        	tmpAns = Math.abs(cnt[1]-cnt[2]);
        	if(Ans < tmpAns) {
        		Ans = tmpAns;
        	} else {//다시 시작
        		Arrays.fill(cnt, 0);
        		cnt[dir[r]]++;
        		l = r;
        	}
        	r++;
        }
        System.out.println(Ans);
	}

}

//        for(int i=0;i<N;i++) {
//        	System.out.print(dir[i]+" ");
//        }