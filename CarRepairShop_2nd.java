//SWEA 차량 정비소
import java.io.*;
import java.util.*;

public class Solution {
	static class Customer{
		int enterTime;//해당 창구 입실 시간, 퇴실 시간
		int idx;//고객 번호
		int recepNum;//접수 창구 번호
		int checkNum;//정비 창구 번호
		Customer(int idx,int enterTime){
			this.idx = idx; this.enterTime = enterTime;
		}
	}
//	static class Comp implements Comparator {
	static Comparator<Customer> Comp = new Comparator<Customer>() {
		@Override
		public int compare(Customer c1, Customer c2) {
			if(c1.enterTime == c2.enterTime) {//접수 끝난 시간 동일한 경우 접수 창구 번호 오름차순!
				return c1.recepNum - c2.recepNum;
			}
			return c1.enterTime - c2.enterTime;//접수 끝난 시간 오름차순!
		}
	};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		//변수 초기화
		int T = Integer.parseInt(br.readLine());
		int ans;
		int N,M,K,A,B;
		int[] a;
		int[] b;
		int[] tt;
		
		PriorityQueue<Customer> recepQ;//도착 시간 오름차순
		PriorityQueue<Customer> mechQ;//접수 종료 시간 오름차순, 접수 종료 시간 동일한 경우 접수 창구 오름차순
		
		Customer[] reception;
		Customer[] mechanic;
		
		for(int t = 1;t<=T;t++) {
			//N M K A B
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			a = new int[N+1];
			b = new int[M+1];
			tt = new int[K+1];
			
			recepQ = new PriorityQueue<>((x,y)->{return x.idx - y.idx;});//도착 시간 오름차순enterTime=>틀렸음.고객 번호 오름차순! 
			mechQ = new PriorityQueue<>(Comp);//접수 종료 시간 오름차순, 접수 종료 시간 동일한 경우 접수 창구 오름차순
			
			reception = new Customer[N+1];
			mechanic = new Customer[M+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=K;i++) {
				tt[i] = Integer.parseInt(st.nextToken());
				recepQ.add(new Customer(i,tt[i]));//고객 번호,고객 도착 시간
			}
			//로직 구현 여기
			int cnt = 0;
			int time = 0;
			ans = 0;
			while(true) {
				//1.접수 창구 빼기
				for(int i=1;i<=N;i++) {
					if(reception[i] != null && reception[i].enterTime+a[i] <= time) {
						reception[i].enterTime = time;//1.접수 끝난시간으로 갱신
						mechQ.add(reception[i]);
						reception[i] = null;
					}
				}
				//2.접수 창구 넣기
				for(int i=1;i<=N;i++) {
					if(reception[i] == null) {//논리적 비약. null인지 체크를 안했음.
						if(!recepQ.isEmpty()) {
							if(recepQ.peek().enterTime <= time) {//고객 도착 시간 <= 현재 시간  
								reception[i] = recepQ.remove();
								reception[i].recepNum = i;
								//if(i==3)System.out.println("고객번호 :"+reception[i].idx);
								reception[i].enterTime = time;
							}
						}
					}
				}
				//3.정비 창구 빼기
				for(int i=1;i<=M;i++) {
					if(mechanic[i] != null && mechanic[i].enterTime+b[i] <= time) {
						if(mechanic[i].recepNum == A && mechanic[i].checkNum == B) {
							ans += mechanic[i].idx;
						}
						mechanic[i] = null; cnt++;
					}
				}
				if(cnt == K) {
					break;
				}
				//4.정비 창구 넣기
				for(int i=1;i<=M;i++) {
					if(mechanic[i] == null) {
						if(!mechQ.isEmpty()) {
							mechanic[i] = mechQ.remove();
							mechanic[i].enterTime = time;//정비 창구 들어가는 시간
							mechanic[i].checkNum = i;
						}
					}
				}
				time++;
			}
			
			if(ans == 0) ans = -1;
			sb.append("#"+t+" "+ans+"\n");
		}//TC 반복문
		
		System.out.println(sb);
	}
}