import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class InsertOperator {
	static int N,Num[];
	static int[] Op = new int[4];
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	
	static void solve(int index,int num) {
		//정답 찾은 경우.
		if(index == N) {
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			return;
		}
		
		//다음 경우 호출 :index+1번째 연산자를 선택.
		//N-1개의 연산자를 줄세우는 경우의 수.=>결국 4개 중에서 선택하는 것!! 양수이면 선택, 음수이면 패쓰.
		//for(int i=0;i<N;i++) {//제대로 디버깅해보면 배열범위초과ㅗ 나올것이다!Operator는 사칙연산, 크기 4짜리 배열이므로!!
		for(int i=0;i<4;i++) {
			if(Op[i]>0) {
				Op[i]--;
				switch(i) {//op[index] = i;solve(index+1,op)에 해당!
					case 0:solve(index+1,num+Num[index]);break;
					case 1:solve(index+1,num-Num[index]);break;
					case 2:solve(index+1,num*Num[index]);break;
					case 3:solve(index+1,num/Num[index]);break;
				}
				Op[i]++;
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//변수는 입력받고, 객체는 초기화!!!
		N = Integer.parseInt(br.readLine());
		Num = new int[N];
		String[] input = br.readLine().split(" ");//숫자 입력받기.
		for(int i=0;i<N;i++) {
			Num[i] = Integer.parseInt(input[i]);
		}
		input = br.readLine().split(" ");//연산자 정보 입력받기.
		for(int i=0;i<4;i++) {
			Op[i] = Integer.parseInt(input[i]);//+-*/ 갯수 정보 저장!
		}
		solve(1,Num[0]);//index,현재까지 계산한 값.
		System.out.println(MAX);
		System.out.println(MIN);
	}

}