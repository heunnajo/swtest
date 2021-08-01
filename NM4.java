package ss;
import java.util.ArrayList;
import java.util.Scanner;
public class NM4 {
	static int N,M;//1부터 N까지의 정수를 중복없이 M개, 비내림차순.
	static StringBuilder sb;
	static boolean[] check;
	static void go(int index,int start,ArrayList<Integer> order) {
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
		//다음 경우 호출:1부터 N까지의 정수 중에서 index번째 정수를 선택한다. 총 M개 선택!
		for(int i=start;i<=N;i++) {
			order.add(i);//1번째 수로 1을 선택.
			go(index+1,i,order);//2번째 수 선택할 차례.현재 선택한 수&현재까지 선택한 순열 넘김.
			order.remove(order.size()-1);//원상복구.
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		check = new boolean[N+1];
		ArrayList<Integer> order = new ArrayList<Integer>();
		go(1,1,order);
		//StringBuilder에 숫자를 저장해서 한번에 출력한다!
		System.out.print(sb);
	}

}