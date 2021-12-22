import java.util.*;
import java.io.*;
public class Main {
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
		long ans = 0;
		for(int i=0;i<n;i++) {
			Pair p = new Pair(a[i],1);
			while(!s.isEmpty()) {
				if(s.peek().h <=a[i]) {
                    ans+=(long)s.peek().n;
					if(s.peek().h == a[i]) {
						p.n+=s.peek().n;
					}
					s.pop();
				} else {
                    ans+= 1L;
					break;
				}
			}
			s.push(p);
		}
		System.out.println(ans);
	}

}
//		for(int i=0;i<n;i++) {
//			System.out.println(a[i]);
//		}