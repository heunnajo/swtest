package boj;
//거스름돈
import java.util.*;
import java.io.*;
public class BOJ5585 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int x = 1000-Integer.parseInt(br.readLine());
        int cnt = 0;
        
        int[] unit = {500,100,50,10,5,1};
        for(int i=0;i<6;i++) {
        	if(x >= unit[i]) {
        		cnt += x/unit[i];
        		x %= unit[i];
        	}
        }
        
        System.out.println(cnt);
	}

}
