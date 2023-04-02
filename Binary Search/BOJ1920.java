package boj;
import java.util.*;
import java.io.*;
//수 찾기
public class BOJ1920 {
	static int N,M;
	static int[] Arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		N = Integer.parseInt(br.readLine());
		Arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(Arr);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int key;
		for(int i=0;i<M;i++) {//M개의 숫자마다 BS해서 존재유무 판단 결과 저장! 
			key = Integer.parseInt(st.nextToken());
			if(binarySearch(key)) {
				sb.append("1").append("\n");
			} else {
				sb.append("0").append("\n");
			}
		}
		
		System.out.println(sb.toString());

	}
	static boolean binarySearch(int key) {
		int l = 0;
		int r = Arr.length-1;
		int mid = -1;
		
		while(l <= r) {
			mid = (l+r)/2;
			
			if(key < Arr[mid]) {
				r = mid-1;
			} else if(key > Arr[mid]) {
				l = mid+1;
			} else {
				return true;
			}
		}
		
		return false;
	}

}
