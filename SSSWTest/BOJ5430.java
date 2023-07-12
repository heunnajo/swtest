package barkingAlgo;
import java.util.*;
import java.io.*;

//AC
public class BOJ5430 {
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		String func;
		int n;
		Deque<Integer> deque;
		StringTokenizer st;
		sb = new StringBuilder();
		while(TC-- >0) {
			func = br.readLine();
			n = Integer.parseInt(br.readLine());
			deque = new LinkedList<>();
			st = new StringTokenizer(br.readLine(),"'[],");
			
			for(int i=0;i<n;i++) {
				deque.offer(Integer.parseInt(st.nextToken()));// <- 데이터 삽입 방향
			}
			
			//각 테케마다 연산 수행, 연산 결과 기록
			solve(func,deque);//근데 연산 결과 어떻게 가져오지? 매개변수로 넘기면 그 함수 내에서만 변하고 리턴된 후에는 그대로일 텐데 : 전역변수에 기록.
		}
		
		System.out.println(sb.toString());
	}
	static void solve(String func, Deque<Integer> deque) {
		boolean isRight = true;
		int len = func.length();
		
		for(int i=0;i<len;i++) {
			if(func.charAt(i) == 'R') {
				isRight = !isRight;
			} else {//'D'
				if(deque.isEmpty()) {
					sb.append("error").append("\n");
					return;//이 테스트케이스는 종료.
				} else {
					if(isRight) {//head에서 삭제
						deque.pollFirst();
					} else {//tail에서 삭제
						deque.pollLast();
					}
				}
			}
		}
		
		makeOutput(isRight,deque);
	}
	static void makeOutput(boolean isRight, Deque<Integer> deque) {
		sb.append("[");
		
		if(isRight) {
			while(!deque.isEmpty()) {
				sb.append(deque.pollFirst());
				if(!deque.isEmpty()) sb.append(",");//현재 원소가 마지막 원소(=덱이 비었으면) 하지 않고, 그 외으 ㅣ모든 경우는 ,을 붙임
			}
		} else {
			while(!deque.isEmpty()) {
				sb.append(deque.pollLast());
				if(!deque.isEmpty()) sb.append(",");
			}
			
		}
		
		sb.append("]").append("\n");
	}

}

//			
//			while(!deque.isEmpty()) {
//				System.out.println(deque.poll());// 데이터 삭제 방향 <-
//			}