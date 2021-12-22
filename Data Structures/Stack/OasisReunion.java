package ss;
import java.util.*;
import java.io.*;
public class OasisReunion {
	static class Pair{
		int h,n;
		Pair(int h,int n){
			this.h = h;
			this.n = n;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		Stack<Pair> s = new Stack<>();
		int ans = 0;
		for(int i=0;i<n;i++) {
			Pair p = new Pair(a[i],1);
			while(!s.isEmpty()) {
				if(s.peek().h <=a[i]) {
					if(s.peek().h == a[i]) {
						s.peek().n++;
					}
					ans+=s.peek().n;
					s.pop();
				} else {
					ans++;//이건 무조건 여기 들어강야할 것 같은딩!이 때는 1만 증가
					break;
				}
				//공통적으로 ans++라면 if-else구문 밖에 한번만 넣어도 되지 않나?
			}
			s.push(p);//스택에 넣는 것은 공통 로직.그런데 top>h일 때에도 ans 증가시켜야함!
		}
		System.out.println(ans);
	}

}
//		for(int i=0;i<n;i++) {
//			System.out.println(a[i]);
//		}
