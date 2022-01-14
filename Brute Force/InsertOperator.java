package ss;
import java.io.*;
import java.util.*;
public class InsertOperator {
	static int max,min,n,num[],op[];
	static final int INF = 1000000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());//수의 갯수
		num = new int[n];
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		max = -1000000000;min = INF;
		go(num[0],0);
		System.out.println(max);
		System.out.println(min);
	}
	static void go(int sum,int idx) {
		//1.재귀 종료 조건
		if(idx == n-1) {
			if(sum>max) max = sum;
			if(sum<min) min = sum;
			return;
		}
		//2.현재 선택
		for(int i=0;i<4;i++) {
			if(op[i]<=0) continue;//op[i]는 절대 음수가 될 수 없음.원복시킴.
			op[i]--;
			if(i==0) go(sum+num[idx+1],idx+1);
			if(i==1) go(sum-num[idx+1],idx+1);
			if(i==2) go(sum*num[idx+1],idx+1);
			if(i==3) go(sum/num[idx+1],idx+1);
			op[i]++;
		}
	}
}

//		for(int i=0;i<n;i++) {
//			System.out.print(num[i]+" ");
//		}
//		System.out.println();
//		for(int i=0;i<4;i++) {
//			System.out.print(op[i]+" ");
//			
//		}