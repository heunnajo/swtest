package ss;
import java.util.*;
import java.io.*;
public class Teleport {
	static int N,M,T,cityInfo[][],Time[][];
	static final int INF = 987654321;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		cityInfo = new int[N][3];
		Time = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cityInfo[i][0] = s;
			cityInfo[i][1] = x;
			cityInfo[i][2] = y;
		}
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			sb.append(getTime(a,b)+"\n");
		}
		System.out.print(sb);
	}
	static int getTime(int a,int b) {
		int sa = cityInfo[a][0], sb = cityInfo[b][0];
		int dist = 0;
		if(sa==1 && sb==1) {
			dist = getBetween(a,b);
		} else if(sa == 1) {
			dist = T+getTele(b);
		} else if(sb == 1) {
			dist = T+getTele(a);
		} else {
			dist = getTele(a)+T+getTele(b);
		}
		return dist;
	}
	static int getBetween(int a,int b) {
		int time = INF;
		time = Math.abs(cityInfo[a][1]-cityInfo[b][1])+Math.abs(cityInfo[a][2]-cityInfo[b][2]);
		if(cityInfo[a][0] == 1 && cityInfo[b][0]==1) {
			time = Math.min(time, T);
		}
		return time;
	}
	static int getTele(int x) {
		int time = INF;
		for(int i=0;i<N;i++) {
			if(cityInfo[i][0]==0)continue;
			if(time>getBetween(i,x)) time = getBetween(i,x);
		}
		return time;
	}
}
