package ss;
import java.io.*;
import java.util.*;
public class TheBiggestRectInHisto {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n,a[];
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			a = new int[n];
			for(int i=0;i<n;i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			Stack<Integer> s = new Stack<>();
			long ans = 0;//높이 제하 10억, n제한 10만이므로 long으로
			for(int i=0;i<n;i++) {//히스토 막대 하나씩 높이 비교하면서 스택에 넣는다.
				while(!s.isEmpty() && a[s.peek()] > a[i]) {//top>x인 경우 top이 갖는 최대 직사각형 넓이 구학고, pop한다!
					long height = a[s.pop()];
					long width = i;//흠.
					if(!s.isEmpty()) {
						width = (i-s.peek()-1); 
					}
					if(ans<(long)width*height) {
						ans = (long)width*height;
					}
				}
				s.push(i);//top<=x인 경우 : 그냥 스택에 넣으면 됨.
			}
			while(!s.isEmpty()) {
				long height = a[s.pop()];
				long width = n;
				if(!s.isEmpty()) {
					width = n-s.peek()-1;
				}
				if(ans < (long)width*height) {
					ans = (long)width*height;
				}
			}
			System.out.println(ans);
		}
	}

}
