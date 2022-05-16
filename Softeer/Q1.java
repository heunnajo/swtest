//1.택배 마스터 광우
import java.util.*;
import java.io.*;

public class Main {
	static int N,M,K,ans;
	static int[] Weight;
	static int[] order;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		
		Weight = new int[N];
		order = new int[N];
		v = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Weight[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) order[i] = Weight[i];
		move(0,0,0);
		
		makeOrder(0,0);
		System.out.println(ans);
	}
	static void makeOrder(int idx,int cnt) {
		if(idx == N) {
			move(0,0,0);
			return;
		}
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			order[idx] = Weight[i];
			makeOrder(idx+1,cnt);
			v[i] = false;
		}
	}
	static void print() {
		for(int i=0;i<N;i++) {
			System.out.print(order[i]+" ");
		}
		System.out.println();
	}
	static void move(int idx,int sum,int from) {
		if(idx == K) {
			if(ans>sum) ans = sum;
			return;
		}
        //int curSum = order[from%N];from++;
		int curSum = 0;
		while(curSum+order[from%N] <= M) {
			curSum += order[from%N];
			from++;
		}
		move(idx+1,sum+curSum,from);
	}
}