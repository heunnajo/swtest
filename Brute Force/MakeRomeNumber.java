package ss;
import java.util.*;
public class MakeRomeNumber {
	static int N,ans;
	static void go(int index,int selected) {
		//1.종료 조건
		if(index == N) {
			ans++;
			return;
		}
		//2.현재 선택, 다음 경우 호출!
		for(int i=selected;i<4;i++) {
			go(index+1,i);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		go(0,0);//0번부터 n개 선택한다!
		System.out.println(ans);
	}

}
