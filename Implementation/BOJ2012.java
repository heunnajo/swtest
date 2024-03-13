package cdTest;
import java.util.*;
import java.io.*;
//등수 매기기
public class BOJ2012 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[500001];
		
		for(int i=1;i<=N;i++) {
			int input = Integer.parseInt(br.readLine());
			count[input]++;
		}
		
		long sum = 0;
		int rank = 1;
		for(int i=1;i<=500000;i++) {
			if(count[i] == 0) continue;
			
			for(int j=0;j<count[i];j++) {
				sum += Math.abs(rank-i);//매기는 등수 - 현재 예상 등수
				rank++;
			}
		}
		System.out.println(sum);
	}

}

//		for(int i=1;i<=N;i++) {
//			System.out.println("예상등수"+i+" 의 갯수: "+count[i]);
//		}