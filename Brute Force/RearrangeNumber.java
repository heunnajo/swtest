package ss;
import java.util.*;
public class RearrangeNumber {
	static int[] A;
	static int tmp,B,N,ans;
	static boolean[] Used;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		tmp = sc.nextInt();
		B = sc.nextInt();
		
		Stack<Integer> st = new Stack<>();
		N = 0;
		while(tmp != 0) {
			st.push(tmp%10);
			tmp /= 10;
			N++;
		}
		
		//초기화
		A = new int[N];
		Used = new boolean[N];
		for(int i=0;i<N;i++) {
			A[i] = st.pop();
		}
		ans = -1;
		go(0,0);
		System.out.println(ans);
	}
	static void go(int index,int num) {
		if(index==N) {//N개 다 선택한 후, B보다 작을 때만 ans 최댓값 갱신한다.
			if(num<B) {
				ans = ans<num?num:ans;
			}
			return;
		}
		for(int i=0;i<N;i++) {
			if(Used[i]) continue;
			if(index==0 && A[i] == 0) continue;
			Used[i] = true;
			go(index+1,num*10+A[i]);
			Used[i] = false;
		}
	}
}
