package ss;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class NM7 {
	static int N,M;//N개의 정수를 중복가능 M개 선택.
	static StringBuilder sb;
	static boolean[] check;
	static ArrayList<Integer> num;
	static void go(int index,ArrayList<Integer> order) {
		//정답 찾은 경우 :현재까지 선택한 순열 order를 StringBuilder에 차곡차곡 저장한다!
		if(index == M+1) {
			for(int i=0;i<M;i++) {//order의 0번째,1번째를 sb에 저장.
				sb.append(order.get(i)+" ");
				if(i==M-1) {
					sb.append("\n");
				}
			}
			return;
		}
		//불가능한 경우
		if(index>=M+1) return;
		//다음 경우 호출: N개 숫자 저장된 배열 중에서 index번째 정수를 선택한다. 총 M개 선택!
		for(int i=0;i<N;i++) {
			order.add(num.get(i));
			go(index+1,order);//2번째 수 선택할 차례.현재까지 선택한 순열 넘김.
			order.remove(order.size()-1);//원상복구.
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		//객체 초기화.
		check = new boolean[N+1];
		num = new ArrayList<Integer>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		for(int i=0;i<N;i++) {//N개의 정수를 입력받는다.
			num.add(sc.nextInt());
		}
		//배열리스트 오름차순 정렬.
		Collections.sort(num);
		
		go(1,order);
		
		//StringBuilder에 숫자를 저장해서 한번에 출력한다!
		System.out.print(sb);
	}

}
