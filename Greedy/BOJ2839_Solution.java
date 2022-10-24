package boj;
import java.util.*;
import java.io.*;
//설탕 배달
public class BOJ2839_Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int input = Integer.parseInt(br.readLine());
		int num = 0;
		while(input%5!=0){
			input -= 3;
			num++;
		}
		
		if(input < 0) System.out.println(-1);
		else{
			num += input/5;				
			System.out.println(num);
		}
        br.close();
	}
	
}
