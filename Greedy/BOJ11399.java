package boj;
import java.util.*;
import java.io.*;

//ATM
public class BOJ11399 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] p = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(p);
		//for(int pi:p) System.out.print(pi+" ");
		
		int sum = 0;
		for(int end=0;end<n;end++) {
			int tmp = 0;
			//System.out.println("end: "+end);
			for(int i=0;i<=end;i++) {
				//System.out.println("tmp: "+tmp);//
				//System.out.println("i: "+i+"p[i]: "+p[i]);
				tmp += p[i];
				//sum += p[i];
				//System.out.println("tmp: "+tmp);
			}
			sum += tmp;
			//System.out.println("tmp: "+tmp);
			//System.out.println("sum: "+sum);
			//System.out.println();
			//System.out.println();
		}
		System.out.println(sum);
	}

}
