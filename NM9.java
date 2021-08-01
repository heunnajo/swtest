package ss;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class NM9 {
	static int N,M;//N개의 정수 num배열의 인덱스(0,1,..N-1)를 선택해서 순열만든다, 재귀함수는 stringbuilder를 바로 반환한다.
	//static StringBuilder sb;
	static int[] num,cnt;
	static StringBuilder go(int index,ArrayList<Integer> order) {
		//정답 찾은 경우 :현재까지 선택한 순열 order를 StringBuilder에 차곡차곡 저장한다!
		if(index == M) {
			StringBuilder sub_ans = new StringBuilder();
			for(int i=0;i<M;i++) {//order의 0번째,1번째를 sb에 저장.
				sub_ans.append(num[order.get(i)]+" ");
				if(i==M-1) {
					sub_ans.append("\n");
				}
			}
			return sub_ans;
		}
		//불가능한 경우
		if(index>=M+1) {//num배열의 인덱스를 선택해서 순열만든다.
			StringBuilder none = new StringBuilder();
			return none;
		}
		//다음 경우 호출:N개의 정수 배열에서 0,1,..N-1 총 N개의 인덱스 중에서 M개 선택하는 순열!
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<N;i++) {//num배열의 인덱스를 선택해서 순열 만든다!
			if(cnt[i]>0) {
				cnt[i]--;
				order.add(i);
				ans.append(go(index+1,order));
				order.remove(order.size()-1);
				cnt[i]++;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		ArrayList<Integer> order = new ArrayList<Integer>();
		num = new int[N];
		cnt = new int[N];
		int[] tmp = new int[N];
		for(int i=0;i<N;i++) {
			tmp[i] = sc.nextInt();
		}
		Arrays.sort(tmp);
		//1.중복 제거해서 num에 저장.
		int x = tmp[0];
		int k = 0; int c = 1;
		for(int i=1;i<N;i++) {
			if(x == tmp[i]) {c++;}//중복횟수 카운팅!! 중복이 끝날 때까지.
			else {
				num[k] = x;
				cnt[k] = c;
				k++;
				x = tmp[i];
				c = 1;
			}
		}
		num[k] = x;cnt[k] = c;N = k+1;//중복 제거한 수로 N 갱신!
		
		System.out.print(go(0,order));
	}

}
