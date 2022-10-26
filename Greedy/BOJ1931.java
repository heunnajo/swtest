package boj;
import java.util.*;
import java.io.*;

//회의실 배정
public class BOJ1931 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		
		StringTokenizer st;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr,new Comparator<int[]>() {
			
			@Override
			public int compare(int[] a,int[] b) {
				if(a[1] == b[1]) {
					return a[0] - b[0];
				}
				return a[1]-b[1];
			}
		});
		
		int count = 0;
		int prev_end_time = 0;//회의 끝나는 시간 비교 위해
		
		for(int i=0;i<n;i++) {
			if(prev_end_time <= arr[i][0]) {//끝시간과 시작 시간을 비교!
				prev_end_time = arr[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.println(arr[i][0]+" "+arr[i][1]);
//		}