package ss;
import java.util.*;
import java.io.*;
public class SubArraySum2 {
	static int N,S,M,arr[],up[],down[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		M = N/2;
		N = N-M;
//		up = new int[n];
//		down = new int[m];
		//길이 N의 모든 가능한 부분 수열의 합 경우의 수 : 2^N -1이므로
		
		//up의 모든 부분수열합 구하기
		up = new int[1<<N];
		for(int i=0;i<(1<<N);i++) {
			for(int k=0;k<N;k++) {
				if((i&(1<<k)) == (1<<k)) {//k번째 원소를 합으로 선택했다는 뜻.
					up[i] += arr[k];
				}
			}
		}
		//down의 모든 부분수열합 구하기
		down = new int[1<<M];
		for(int i=0;i<(1<<M);i++) {
			for(int k=0;k<M;k++) {
				if((i&(1<<k)) == (1<<k)) {
					down[i] += arr[k+N];
				}
			}
		}
		Arrays.sort(up);
		Arrays.sort(down);
		N = (1<<N); M = (1<<M);
		
		for(int i=0;i<M/2;i++) {
			int tmp = down[i];
			down[i] = down[M-i-1];
			down[M-i-1] = tmp;
		}
		int i=0;int j=0;
		long ans = 0;
		
		while(i<N && j < M) {
			if(up[i]+down[j] == S) {
				long c1 = 1; long c2 = 1;
				i+=1; j+= 1;
				while(i<N && up[i] == up[i-1]) {//i가 0이면 인덱스초과 어쩌려고?
					c1+=1; i+=1;
				}
				while(j<M && down[j] == down[j-1]) {
					c2+=1;j+=1;
				}
				ans += c1*c2;
			} else if(up[i]+down[j]<S) {
				i+=1;
			} else { j+=1;}
		}
		if(S == 0) ans -= 1;
		System.out.println(ans);
	}

}
