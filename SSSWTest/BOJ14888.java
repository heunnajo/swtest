package boj;
import java.util.*;
import java.io.*;

//연산자 끼워넣기
public class BOJ14888 {
	static int N,Max,Min;
	static int[] Selected,Num,Operator;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Num = new int[N];
		Selected = new int[N-1];
		Operator = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			Operator[i] = Integer.parseInt(st.nextToken());
		}
		
		Max = -1000000001;
		Min = 1000000001;
		go(0);
		System.out.println(Max);
		System.out.println(Min);
	}
	static void go(int idx) {
		//1.종료 : 최댓값, 최솟값 갱신 후 리턴
		if(idx == N-1) {
			int cur = calculate();
			if(cur > Max) {
				Max = cur;
//				System.out.println("Max");
//				print();
//				System.out.println(cur);
			}
			if(cur < Min) {
				Min = cur;
//				System.out.println("Min");
//				print();
//				System.out.println(cur);
			}
			return;
		}
		//2.현재 경우 선택, 다음 경우 호출
		for(int i=0;i<4;i++) {
			if(Operator[i] > 0) {
				Operator[i]--;
				Selected[idx] = i;
				go(idx+1);
				Operator[i]++;
				Selected[idx] = -1;
			}
		}
	}
	static void print() {
		for(int i=0;i<N-1;i++) {
			System.out.print(Selected[i]+" ");
		}
		System.out.println();
	}
	static int calculate() {
		int res = 0;
		
		for(int i=0;i<N-1;i++) {
			if(i == 0) {
				if(Selected[i] == 0) {
					res = Num[i] + Num[i+1];
				} else if(Selected[i] == 1) {
					res = Num[i] - Num[i+1];
				} else if(Selected[i] == 2) {
					res = Num[i] * Num[i+1];
				} else if(Selected[i] == 3) {
					res = Num[i] / Num[i+1];
				}
			} else {
				if(Selected[i] == 0) {
					res = res + Num[i+1];
					//res += Num[i+1];
				} else if(Selected[i] == 1) {
					res = res - Num[i+1];
					//res -= Num[i+1];
				} else if(Selected[i] == 2) {
					res = res * Num[i+1];
					//res *= Num[i+1];
				} else if(Selected[i] == 3) {
					res = res / Num[i+1];
					//res /= Num[i+1];
				}
			}
		}
		
		return res;
	}

}

//		for(int i=0;i<N;i++) {
//			System.out.print(Num[i]+" ");
//		}
//		System.out.println();
//		
//		for(int i=0;i<4;i++) {
//			System.out.print(Operator[i]+" ");
//		}