package ss;
import java.util.ArrayList;
import java.util.Scanner;
public class NM2_selection {
	static int N,M;//1부터 N까지의 정수를 중복없이 M개 
	static StringBuilder sb;
	static boolean[] check;
	//index번째 수를 결정하고, 결정할 수는 selected번째부터!
	//go(1,1,null?)
	static void go(int index,int start,ArrayList<Integer> order) {//이건 12,13,14,23,24,34
		//정답 찾은 경우 :현재까지 선택한 순열 order를 StringBuilder에 차곡차곡 저장한다!
		if(index == M) {
			for(int i=0;i<M;i++) {//order의 0번째,1번째를 sb에 저장.
				sb.append(order.get(i)+" ");
				if(i==M-1) {
					sb.append("\n");
				}
			}
			return;
		}
		//불가능한 경우
		if(start>N) {//더 선택할 숫자가 없는 경우.
			return;
		}
		//다음 경우 호출:1부터 N까지의 정수 중에서 index번째 정수를 선택한다. 총 M개 선택!
		//1.선택O
		order.add(start);
		go(index+1,start+1,order);//123까지 선택하고 리턴됨.
		//2.선택X
		order.remove(order.size()-1);//12.
		go(index, start+1, order);//
		//order.remove(order.size()-1);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		check = new boolean[N+1];
		ArrayList<Integer> order = new ArrayList<Integer>();
		go(0,1,order);
		
		//StringBuilder에 숫자를 저장해서 한번에 출력한다!
		System.out.print(sb);
	}

}
