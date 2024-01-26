package cdTest;
import java.util.*;
import java.io.*;
//랜선 자르기

public class BOJ1654 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		//System.out.println("N: "+N+", K: "+K);
		
		int[] lan = new int[K];
		
		long min = 0;
		long max = 0;
		for(int i=0;i<K;i++) {
			lan[i] = Integer.parseInt(br.readLine());
			if(max < lan[i]) max = lan[i];
		}
		//System.out.println("max: "+max);
		max++;
		long mid;
		while(min < max) {
			mid = (min+max) / 2;
			
			//mid 길이로 K개 랜선을 자르고, 갯수 구한다.
			int count = 0;
			for(int i=0;i<K;i++) {
				count += (lan[i]/mid);
			}
			//N과의 대소비교 통해 이분 탐색 구간 조정한다.
			if(count < N) {//더 잘게 자른다. 자르는 길이 더 작게 조정.
				max = mid;
			} else {//count >= N : 길이를 더 길게 만들어야한다.상한값 찾아야함.
				min = mid+1;
			}
		}
		System.out.println(min-1);
	}

}

//		for(int i=0;i<K;i++) {
//			System.out.println(lan[i]);
//		}