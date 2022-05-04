import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//나머지 합
public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		int[] remainder = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			sum += Integer.parseInt(st.nextToken()) % m;
			remainder[sum%m]++;
		}
		
		long ans = remainder[0];
		for(int i=0;i<m;i++) {
			int tmp = remainder[i];
			ans += (long)tmp*(tmp-1)/2;
		}
		System.out.println(ans);
		
	}

}