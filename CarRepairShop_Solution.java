package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CarRepairShop_Solution {
	static class Customer{
		int customerNo;//1.고객 번호
		int receptionNo;//2.접수 창구 번호
		int repairNo;//3.정비 창구 번호
		int arriveTime;///4.도착 시간
		int receptionStartTime;//5.접수 시작 시간
		int receptionEndTime;//6.접수 종료 시간
		int repairStartTime;//7.정비 시작 시간 (정비 종료 시간은 불필요)
		
		Customer(int customerNo, int arriveTime){
			this.customerNo = customerNo;
			this.arriveTime = arriveTime;
		}
	}
	static PriorityQueue<Customer> receptionQueue,repairQueue;
	static Customer[] receptionCounter,repairCounter;//O
	static int[] reception, repair;//입력
	static int N,M,K,A,B,T,ans;//입력
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			ans = 0;
			
			reception = new int[N+1];
			repair = new int[M+1];
			receptionCounter = new Customer[N+1];
			repairCounter = new Customer[M+1];
			receptionQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1,Customer o2) {
					return o1.customerNo - o2.customerNo;
				}
			});
			repairQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1,Customer o2) {
					if(o1.receptionEndTime == o2.receptionEndTime) {
						return o1.receptionNo - o2.receptionNo;
					} else {
						return o1.receptionEndTime - o2.receptionEndTime;
					}
				}
			});
			
			st = new StringTokenizer(br.readLine());
			for(int n=1;n<=N;n++) {
				reception[n] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int m=1;m<=M;m++) {
				repair[m] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=K;i++) {//고객 인덱스번호랑 도착시간 함께 저장 
				receptionQueue.offer(new Customer(i,Integer.parseInt(st.nextToken())));
			}
			
			int time = 0;//time을 카운팅
			int cnt = 0;//반복 종료 조건
			while(true) {
				//1.접수 끝난 사람 정비창구 보내기
				for(int i=1;i<=N;i++) {
					if(receptionCounter[i]==null)continue;//i번창구에 사람없으면 넘어간다.
					Customer c = receptionCounter[i];//i번 접수창구의 고객 조회.
					
					if(c.receptionStartTime+reception[i]<=time) {//접수완료 시간<=현재시간 비교 : 부등호!
						c.receptionEndTime = time;
						repairQueue.offer(c);//O
						receptionCounter[i] = null;
					}
				}
				//2.접수 창구 채우기
				for(int i=1;i<=N;i++) {//1번 접수창구부터 차례대로 고객 배정.
					if(receptionCounter[i] == null) {//i번 창구 비었으면 고객 1명씩 배정한다.
						if(!receptionQueue.isEmpty()) {
							if(receptionQueue.peek().arriveTime<=time) {//?
								receptionCounter[i] = receptionQueue.poll();//receptionQueue에 있는 사람(번호 작은 순) i번 창구에 할당.
								receptionCounter[i].receptionNo = i;
								receptionCounter[i].receptionStartTime = time;
							}
						}
					}
				}
				//3.정비 끝난 사람 내보내기
				for(int i=1;i<=M;i++) {
					if(repairCounter[i] == null) continue;
					
					Customer c = repairCounter[i];
					
					if(c.repairStartTime + repair[i] <= time) {
						if(c.receptionNo == A && c.repairNo == B) {
							ans += c.customerNo;
							repairCounter[i] = null;
							cnt++;
						}
					}
				}
				if(cnt == K) break;
				//4.정비 창구 채우기
				for(int i=1;i<=M;i++) {
					if(repairCounter[i] == null) {
						if(!repairQueue.isEmpty()) {
							repairCounter[i] = repairQueue.poll();
							repairCounter[i].repairNo = i;
							repairCounter[i].repairStartTime = time;
						}
					}
				}
				time++;
			}
			if(ans == 0) ans = -1;
			System.out.println("#" + t+" "+ans);
		}//T 

	}

}
