// 백준 14888번. 연산자 끼워넣기
import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static ArrayList<Integer> nums;
	static int[] ops;
	static int max,min;//정답 저장 변수
	static char[] realOps = {'+','-','*','/'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		nums = new ArrayList<>();
		
		n = Integer.parseInt(br.readLine());
		ops = new int[4];
		
		st = new StringTokenizer(br.readLine());//n개의 숫자 저장!
		for(int i=0;i<n;i++)
			nums.add(Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());//n개의 숫자 저장!
		for(int i=0;i<4;i++)
			ops[i] = Integer.parseInt(st.nextToken());
		
		max = Integer.MIN_VALUE; min = Integer.MAX_VALUE;
		go(0,new char[n-1]);
		System.out.println(max);
		System.out.println(min);
	}
	
	//idx:현재 선택하는 연산자의 인덱스
	//selected:현재까지 선택한 연산자 정보
	static void go(int idx, char[] selected) {
		//1.종료 조건 :n-1개 연산자 다 선택한 경우
		if(idx == n-1) {
			//확인O
//			for(int i=0;i<n-1;i++) System.out.print(selected[i]+" ");
//			System.out.println();
			operate(selected);//max,min 결과 갱신!
			return;
		}
		//2.현재 선택, 다음 경우 호출 : 선택정보를 배열에 저장
		for(int i=0;i<4;i++) {
			if(ops[i]==0) continue;//갯수가 0이면 선택하지 않고 컨티뉴처리!
			ops[i]--; selected[idx] = realOps[i];
			go(idx+1,selected);
			ops[i]++; 
		}
	}
	//숫자 정보:전역변수 nums에서 복사해서 조회
	//연산자 순서 정보:매개변수 selected에서 조회
	static void operate(char[] selected) {
		ArrayList<Integer> copiedNums = new ArrayList<>(nums);
		int res = 0;
		int idx=0;
		for(int i=0;i<selected.length;i++) {
			res = cal(copiedNums.remove(idx),copiedNums.remove(idx),selected[i]);
			copiedNums.add(idx,res);
		}
		//계산하고 난 후 최종 결과값을 최솟값, 최댓값 갱신시켜준다!
        res = copiedNums.get(0);
		max  =  max < res ? res : max;
		min  =  min > res ? res : min;
	}
	static int cal(int n1,int n2,char op) {
		int sum = 0;
		if(op == '+') {
			return n1+n2;
		} else if(op == '-') {
			return n1-n2;
		} else if(op == '*') {
			return n1*n2;
		} else {//"/"
			return n1/n2;
		}
	}

}