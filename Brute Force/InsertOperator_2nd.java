// 백준 14888번. 연산자 끼워넣기
package ss;
import java.util.*;
import java.io.*;
public class InsertOperator_4th {
	static int n;
	static ArrayList<Integer> nums;
	//static ArrayList<String> ops;
	static int[] ops;
	//static long max,min;//정답 저장 변수
	static int max,min;//정답 저장 변수
	static String[] realOps = {"+","-","*","/"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		nums = new ArrayList<>();
		//ops = new ArrayList<>();
		
		n = Integer.parseInt(br.readLine());
		ops = new int[4];
		
		st = new StringTokenizer(br.readLine());//n개의 숫자 저장!
		for(int i=0;i<n;i++)
			nums.add(Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());//n개의 숫자 저장!
		for(int i=0;i<4;i++)
			ops[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<String> selected = new ArrayList<>();
		go(0,selected);
	}
	
	//idx:현재 선택하는 연산자의 인덱스
	//selected:현재까지 선택한 연산자 정보
	static void go(int idx, ArrayList<String> selected) {
		//1.종료 조건 :n-1개 연산자 다 선택한 경우
		if(idx == n-1) {
			int res = operate(selected);
			max  =  max < res ? max : res;
			min  =  min > res ? min : res;
			return;
		}
		//2.현재 선택, 다음 경우 호출 : 선택정보를 배열에 저장, O(1)로 조회해야할까?
		for(int i=0;i<4;i++) {
			if(ops[i]==0) continue;
			ops[i]--; selected.add(realOps[i]);
			System.out.println("add 후 selected 크기: "+selected.size());//왜 값이 다르게 나오는 거야?????
			
			go(idx+1,selected);
			ops[i]++; 
			System.out.println("selected 크기: "+selected.size());
			selected.remove(selected.size()-1);//원복.왜지?왜 여기서 -1? 하나 추가한 원소를 삭제하는 건데  
		}
	}
	//숫자 정보:전역변수 nums에서 복사해서 조회
	//연산자 순서 정보:매개변수 selected에서 조회
	static int operate(ArrayList<String> selected) {
		ArrayList<Integer> copiedNums = new ArrayList<>(nums);
		
		int ans = 0;
		for(int i=0;i<selected.size();i++) {
			ans = cal(copiedNums.remove(i),copiedNums.remove(i),selected.remove(i));
			copiedNums.add(i,ans);
			i--;
		}
		return copiedNums.get(0);
	}
	static int cal(int n1,int n2,String op) {
		int sum = 0;
		if(op.equals("+")) {
			return n1+n2;
		} else if(op.equals("-")) {
			return n1-n2;
		} else if(op.equals("*")) {
			return n1*n2;
		} else {//"/"
			return n1/n2;
		}
	}

}

//제대로 순열을 생성하는지 확인:O
//for(String s: selected) {
//	System.out.print(s+" ");
//}
//System.out.println();
//return;

//		for(int i=0;i<n;i++)
//			System.out.print(nums.get(i)+" ");
//		System.out.println();
//		
//		
//		for(int i=0;i<4;i++)
//			System.out.print(ops[i]+" ");
//		System.out.println();


//System.out.println("n: "+n);