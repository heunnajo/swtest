package boj;
import java.util.*;
import java.io.*;
//주사위 세개
public class BOJ2480 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int[] dice = new int[7];
        
        for(int i=0;i<3;i++) {
        	dice[Integer.parseInt(input[i])]++;
        }
        
        int ans = 0;
        int tmp = 0;

        for(int i=1;i<=6;i++) {
        	if(dice[i] == 3) {
        		ans = 10000 + i*1000;
        		break;
        	} else if(dice[i] == 2) {
        		ans = 1000 + i*100;
        		break;
        	} else if(dice[i] == 1) {
        		tmp = i*100;
        		ans = Math.max(ans, tmp);
        	}
        }
        System.out.println(ans);
	}

}
