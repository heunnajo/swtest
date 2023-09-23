import java.io.BufferedReader;
import java.io.InputStreamReader;

//moo 게임
public class BOJ5904 {
	static int N;
	static char[] base = {'m','o','o'};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		go(N,1,3);//N,K,len
	}
	static void go(int N,int K,int len) {
		//1.기저 사례
		if(N <= 3) {
			System.out.println(base[N-1]);
			return;
		}
		//2.재귀
		int curLen = 2*len+K+3;
		if(curLen < N) {
			go(N,K+1,curLen);
		} else {
			if(len < N && N <= len+K+3) {
				if(N == len+1) {System.out.println("m");}
				else {System.out.println("o");}
				return;
			} else {
				go(N-(len+K+3),1,3);
			}
		}
	}

}
