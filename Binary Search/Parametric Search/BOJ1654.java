//랜선 자르기
import java.util.*;
import java.io.*;
public class BOJ1654 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] lan = new int[K];
		
		
		long s = 1; long e = -1; long mid = (s+e)/2;
		for(int i=0;i<K;i++) {
			lan[i] = Integer.parseInt(br.readLine());
			if(e < lan[i]) e = lan[i];
		}
		e++;//이 부분 추가함.
		while(s < e) {
			mid = (s+e)/2;
//			int count = 0;
			long count = 0;//데이터 타입 long으로 바꿈.
			
			for(int i=0;i<K;i++) {
				count += (lan[i] / mid);
			}
			
			if(count >= N) {
				s = mid+1;
			} else {
				e = mid;
			}
		}
//		System.out.println(mid);
		System.out.println(s-1);
	}

}

//		for(int i=0;i<K;i++) {
//			System.out.println(lan[i]+",");
//		}