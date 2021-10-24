package ss;
import java.io.*;
import java.util.*;
public class HN_2_1DArray {
	static int[] freq;
	static int M,K;//M:지역 갯수 K:주파수 가장 많은 것
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		freq = new int[21];//배열의 인덱스는 주파수를 의미, 배열값은 해당 주파수가 등장하는 빈도수를 의미!
		//M과 M개의 지역의 주파수 정보 입력받는다!
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for(int j=0;j<K;j++) {
				int val = Integer.parseInt(st.nextToken());
				//System.out.println(val+" ");
				freq[val]++;
			}
		}
		Arrays.sort(freq);
		int ans = 0;
		for(int i=20;i>20-K;i--) {
			ans += freq[i];
		}
		System.out.println(ans);
	}

}
