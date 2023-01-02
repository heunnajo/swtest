package boj;
import java.util.*;
import java.io.*;
//윤년
public class BOJ2753 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int ans = 0;
        if(n % 4 == 0 && n%100 != 0) {ans = 1;}
        if(n % 400 == 0) {ans = 1;}
        
        System.out.println(ans);
        
	}

}
