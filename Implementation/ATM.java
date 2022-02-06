package ss;
import java.io.*;
import java.util.*;

public class ATM {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] time = new int[n];
		int[] d = new int[n];
		for(int i=0;i<n;i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time);
		int sum = 0;
		for(int i=0;i<n;i++) {
			sum += time[i];
			d[i] = sum;
		}
		int answer = 0;
		for(int i=0;i<n;i++) answer += d[i];
		
		System.out.println(answer);
	}

}
