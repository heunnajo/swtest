package boj;

import java.io.*;
import java.util.*;

//단어 수학
public class BOJ1339_IntegerType {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String[] input = new String[n];
        Integer[] alpha = new Integer[26];
        for(int i=0;i<26;i++) alpha[i] = new Integer(0);
        
        for(int i=0;i<n;i++) {
        	input[i] = br.readLine();
        }
        
        String cur = "";
        int len = 0;
        
        for(int i=0;i<n;i++) {
        	cur = input[i];
        	len = cur.length();
        	for(int j=0;j<len;j++) {
        		int sum = (int) Math.pow(10, len-1-j);
        		int idx = cur.charAt(j)-'A';
//        		System.out.println("sum: "+sum);
//        		System.out.println("idx: "+idx);
        		alpha[cur.charAt(j)-'A'] += (int) Math.pow(10, len-1-j);//NPE 발생. why?!
        	}
        }
        Arrays.sort(alpha,Collections.reverseOrder());
        
        int ans = 0;//최대 8자리, 가장 큰 숫자는 9
        int num = 9;
        
        for(int i=0;i<26;i++) {
        	if(alpha[i] == 0) continue;
        	ans += alpha[i]*num;
        	num--;
        }
        
        System.out.println(ans);
	}

}
