//백준 15650 N과 M2
import java.util.*;
public class Main {
	static boolean[] c = new boolean[10];
	static int[] a = new int[10];
	static void go(int index,int start,int n,int m) {
        //1.종료 조건
		if(index == m) {
			for(int i=0;i<m;i++) {
				System.out.print(a[i]);
				if(i!=m-1) System.out.print(' ');
			}
			System.out.println();
			return;
		}
        //2.현재 경우 선택, 다음 경우 호출!
		for(int i=start;i<=n;i++) {
			if(c[i]) continue;
			c[i] = true;a[index] = i;
			go(index+1,i+1,n,m);
			c[i] = false;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		go(0,1,n,m);
	}

}