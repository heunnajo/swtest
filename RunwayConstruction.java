package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class RunwayConstruction {
	static int ans,T,N,K,Map[][];
	static void solve(int dir,int idx) {
		int[] arr = new int[N];
		boolean[] constructed = new boolean[N];//전역으로 해야하는 것 아닌감..? 행/열에 대해서 수행한다.
		//리팩토링 가능할 텐데 분명.
		if(dir == 0) {//idx번째 가로행 
			for(int j=0;j<N;j++) {
				arr[j] = Map[idx][j];
			}
		}
		else if(dir == 1) {//idx번째 세로행 
			for(int i=0;i<N;i++) {
				arr[i] = Map[i][idx];
			}
		}
		
		//본격 경사로 건설 판단& 건설
		//내리막
		for(int i=0;i<N-1;i++) {
			if(arr[i]-arr[i+1] == 1) {
				boolean flag = true;//평지 길이가 경사로ㅗ 길이보다 짧은 경우는?
				for(int k=i+1;k<K;k++) {//아직 K길이가 안 됐는데, 배열 초과하는 경우 처리해야함.
					if(k+1>N-1) {flag = false;break;}//K만큼 반복해야하는데 배열 범위밖이면 flag=flase처리.
					if(arr[k] == arr[i+1] && !constructed[k]) constructed[k] = true;
					else {i = k+1; flag = false; break;}
				}
				if(flag) ans++;
			}
			
		}
		//오르막
		for(int i=N-1;i>=1;i--) {
			if(arr[i]-arr[i-1]==1) {
				boolean flag = true;
				for(int k=i-1;k-1>=0;k--) {
					if(k-1<0) {flag = false;break;}
					if(arr[k] == arr[i-1] && !constructed[k]) constructed[k] = true;
					else {i=k-1;flag = false;break;}
				}
				if(flag) ans++;
			}
			
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			for(int i=0;i<N;i++) {
				solve(0,i);//i번째 가로 행.
			}
			for(int i=0;i<N;i++) {
				solve(1,i);//i번째 세로 열.
			}
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
		
	}
}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}