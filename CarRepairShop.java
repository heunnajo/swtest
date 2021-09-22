package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class CarRepairShop {
	static int T,N,M,K,A,B;
	static int[] a,b,t;//입력값 저장 배열
	static class Customer{
		int startRecep;//접수 시작 시간
		int finishRecep;//접수 완료 시간
		int startCheck;//정비 시작 시간
		int finishCheck;//정비 완료 시간
		int recepNum;//접수 창구 번호
		int checkNum;//정비 창구 번호
	}
	static Customer[] Customers;
	static class fastRecepInfo{//가장 빠른 접수 끝나는 정보
		int time;//끝나는 시간
		int num;//접수 창구 번호
		fastRecepInfo(int time,int num){
			this.time = time;
			this.num = num;
		}
		fastRecepInfo(){}
	}
	static class fastCheckInfo{
		int time;
		int num;
		fastCheckInfo(int time,int num){
			this.time = time;
			this.num = num;
		}
		fastCheckInfo(){}
	}
	static Comparator<Customer> Comp = new Comparator<Customer>() {
		@Override
		public int compare(Customer c1,Customer c2) {
			if(c1.finishRecep == c2.finishRecep) {
				return c1.recepNum - c2.recepNum;
			} else {
				return c1.finishRecep-c2.finishRecep;
			}
		}
	};
	static LinkedList<fastRecepInfo> fastRecep;//가장 빠른 접수 끝나는 정보 N개
	static LinkedList<fastCheckInfo> fastCheck;//가장 빠른 접수 끝나는 정보 M개
	static int getAns() {
		int sum=0;
		for(int i=0;i<K;i++) {
			if(Customers[i].recepNum == A && Customers[i].checkNum == B) {
				sum += (i+1);//1번 사람+ 6번 사람 = 7번이 정답이기 때문에 인덱스+1해준다.
				System.out.println("만족하는 사람 번호 : "+(i+1));
			}
		}
		if(sum==0) return -1;
		return sum;
	}
	static void print_forCheck() {
//		System.out.println("고객번호  접수창구번호  접수시작시간  접수완료시간 정비창구번호  정비시작시간 정비완료시간");
		System.out.println("고객번호  접수창구번호  접수시작시간  접수완료시간");
		for(int i=0;i<Customers.length;i++) {
			//System.out.println("고객수 : "+Customers.length);
			Customer cur = Customers[i];
//			System.out.printf("%6d %8d %8d %8d %8d  %8d %8d",i,cur.recepNum,cur.startRecep,cur.finishRecep,cur.checkNum,cur.startCheck,cur.finishCheck);
			System.out.printf("%6d %8d %8d %8d",i,cur.recepNum,cur.startRecep,cur.finishRecep);
			System.out.println();
		}
	}
	static void solve() {//Customers 객체 배열에 모든 Customer의 속성값을 완성한다.
		//1.접수 창구
		for(int i=0;i<K;i++) {//전체 K명에 대해 접수.
			Customer cur = Customers[i];
			if(i<N) {//0~N-1까지 N명은 바로 접수받을 수 있다.N개 접수창구 반복변수는 i와 동일하다.
				cur.startRecep = t[i];//여기서 NPE가 발생.객체는 null인데 접근하려고 하니발생.
				cur.finishRecep = cur.startRecep+a[i];//접수끝나는 시간=t[i]+a[i]
				cur.recepNum = i;//0부터 N-1까지 순서대로 저장.
				fastRecep.add(new fastRecepInfo(cur.finishRecep,i));//끝나는 접수 시간, 접수 창구번호 저장.
			}
			else if(i>=N) {//time이 작은 오름차순으로 정렬해야함!
				Collections.sort(fastRecep,(r1,r2)->
				Integer.compare(r1.time, r2.time));//time이 빨리 끝나는 것부터 오름차순 정렬.
				if(fastRecep.size()>0) {//아니여기 들어왓다는 건 fastReecep에 N개가 있다는 거 아님?근데 왜 
					fastRecepInfo fastestR = fastRecep.remove(0);//가장 첫번째 요소인 가장 빨리 끝나는 것을 조회.
					cur.startRecep = fastestR.time;
					cur.finishRecep = fastestR.time+a[fastestR.num];
					cur.recepNum = fastestR.num;
				}
			}
		}
		
		//2.정비 창구:접수가 끝난사람들 모두 접수 창구 작은 순서대로 정렬.
		//동시에 정비 창구로 몰리는 경우:접수 창구 번호 작은 순서대로 정렬.
		//K명 모두 동시다발적으로 접수가 끝날 수 있기 때문에 접수가 끝나면 그냥 바로 정렬처리해버린다.=>잘못됐나?
//		Arrays.sort(Customers,(cus1,cus2)->
//		Integer.compare(cus1.recepNum, cus2.recepNum));
		Arrays.sort(Customers,Comp);//접수끝나는 시간 빠른 순으로 Customer정렬, 접수끝나는 시간 같으면 접수창구번호 작은순 정렬.
		for(int i=0;i<K;i++) {
			Customer cur = Customers[i];
			if(i<M) {//M명은 바로 정비 들어간다.
				cur.startCheck = cur.finishRecep;
				cur.finishCheck = cur.finishRecep+b[i];
				cur.checkNum = i;
				fastCheck.add(new fastCheckInfo(cur.finishCheck,i));
			} else if(i>=M) {//fastCheck 배열에는 무조건 M개의 원소들이 존재한다. 그중 가장 시간이 빠른 것을 가져온다.
				Collections.sort(fastCheck,(c1,c2)->
				Integer.compare(c1.time, c2.time));//앞의 M명이 정비 끝나는 시간 빠른 순으로 오름차순 정렬.
				if(fastCheck.size()>0) {
					
					fastCheckInfo fastestC = fastCheck.remove(0);
					cur.startCheck = fastestC.time;
					cur.finishCheck = fastestC.time+b[fastestC.num];
					cur.checkNum = fastestC.num;
				}
			}
		}
	}
//	static void init() {//각 객체 배열 초기화
//		for(int i=0;i<N;i++) {
//			fastRecep[i] = new fastRecepInfo();
//		}
//		for(int i=0;i<M;i++) {
//			fastCheck[i] = new fastCheckInfo();
//		}
//		for(int i=0;i<K;i++) {
//			Customers[i] = new Customer();
//		}
//	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int k=1;k<=T;k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken())-1;//창구번호 1부터 시작하는 걸로 주어지기 때문에 1 빼준다!
			B = Integer.parseInt(st.nextToken())-1;
			
			Customers = new Customer[K];
			fastRecep = new LinkedList<>();
			fastCheck = new LinkedList<>();
			//init();//각 객체배열 초기화
			for(int i=0;i<K;i++) {
				Customers[i] = new Customer();
			}
			a = new int[N];
			b = new int[M];
			t = new int[K];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<K;i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}
		
			solve();
			print_forCheck();
			sb.append("#"+k+" "+getAns()+"\n");//각 TC마다 정답 어디에 저장/출력?
		}
		System.out.println(sb);
	}
	
}
//System.out.println("N: "+N+" M: "+M+" K: "+K+" A: "+A+" B: "+B);
//

////입력 저장 확인.성공.
//for(int i=0;i<N;i++) {
//	System.out.println(a[i]+" ");
//}
//System.out.println();
//for(int i=0;i<M;i++) {
//	System.out.println(b[i]+" ");
//}
//System.out.println();
//for(int i=0;i<K;i++) {
//	System.out.println(t[i]+" ");
//}
//System.out.println();
