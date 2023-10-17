//Q42839 소수 찾기
import java.util.*;

public class Solution {
	static int Ans,N;
	static String Input;
	static boolean[] Visited;
	static ArrayList<Integer> List;
	static boolean isPrime(int n) {//에라토스테네스체 생각해본다!
		if (n == 0) return false;
        if (n == 1) return false;
		for(int i=2; i*i<=n;i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		String numbers = "17";
		solution(numbers);
	}
	static int solution(String numbers) {
        Ans = 0;
        N = numbers.length();
        Input = numbers;
        Visited = new boolean[N];
        List = new ArrayList<>();
        
        //go(0,"");
        for(int i=1;i<=N;i++) {
        	go(0,"",i);
        }
        return Ans;
    }
	static void go(int idx,String num,int len) {
		//1.종료
		if(idx == len) {
			//System.out.println(num);
			if(!List.contains(Integer.parseInt(num))) {
				List.add(Integer.parseInt(num));
				if(isPrime(Integer.parseInt(num))) Ans++;
			}
			return;
		}
		for(int i=0;i<N;i++) {//N=2, i=1일 때.
			if(Visited[i]) continue;
			Visited[i] = true;
			go(idx+1,num+Input.charAt(i),len);
			Visited[i] = false;
		}
	}
}
