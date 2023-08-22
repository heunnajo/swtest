import java.util.*;
import java.io.*;
//회전 초밥
public class BOJ2531 {
	static int N,d,k,c;
	static int[] Sushi,Eated;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		Sushi = new int[N];
		Eated = new int[d+1];
		
		int max = 0;
		for(int i=0;i<N;i++) {
			Sushi[i] = Integer.parseInt(br.readLine());
			if(i<k) {
				if(Eated[Sushi[i]] == 0) {max++;}//종류 수 카운팅!
				Eated[Sushi[i]]++;
			}
		}
		
		int count = max;
		for(int i=0;i<N;i++) {
			if(max <= count) {
				if(Eated[c] == 0) max = count+1;
				else max = count;
			}
			int end = (i+k)%N;
			if(Eated[Sushi[end]]++ == 0) count++;
			if(--Eated[Sushi[i]]==0) count--;
			
		}
		System.out.println(max);

	}

}