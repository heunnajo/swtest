package swea;
import java.util.*;
import java.io.*;
//회전 초밥 :debugging
public class BOJ2531 {
	static int N,d,k,c;
	static HashMap<Integer,Integer> map;
	static int[] Sushi;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		Sushi = new int[N];
		
		for(int i=0;i<N;i++) {
			Sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int tmpSum;
		int sum = 0;
		for(int i=0;i<N;i++) {
			map = new HashMap<>();
			tmpSum = 0;
//			int end = i+k;
//			if(end>N) end = (i+k)%N;
//			for(int j=i; j < end ;j++) {
//				map.put(Sushi[j], 1);
//			}
			int stIdx = i; int endIdx = i+k;
			for(int j=stIdx;j<endIdx;j++) {
				if(j>=N) {j = j%N;}
				map.put(Sushi[j], 1);
			}
			tmpSum = map.size();
			if(!map.containsKey(c)) {
				tmpSum += 1;
			}
			if(sum < tmpSum) {
				//System.out.println("갱신된 시작 인덱i: "+i);
				sum = tmpSum;
			}
		}
		System.out.println(sum);

	}

}
