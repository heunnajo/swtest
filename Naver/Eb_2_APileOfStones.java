package ss;
import java.util.*;
public class Eb_2_APileOfStones {//돌무더기.완탐.경우의 수는?
	static Set<Long> mem = new HashSet<>();//중복 제거
	
	static String solution(int[] stones,int  k) {
		int sum = 0, n = stones.length;
		for(int x:stones) sum += x;
		if(n>2 && (sum-k) % (n-2) != 0) return "-1";
		if(sum < k) return "-1";
		
		return dfs(stones,k,"");//완탐
	}
	static String dfs(int[] stones,int k,String path) {
		mem.add(conv(stones));
		
		//1.종료 조건(k개 돌무더기 하나만 있는가?)
		int cnt_k = 0, cnt_zero = 0;
		for(int x:stones) {
			if(x == k) cnt_k++;
			if(x == 0) cnt_zero++;
		}
		if(cnt_zero == stones.length-1 && cnt_k == 1) return path;
		
		//2.완전 탐색
		//처음 찾은 방법이 사전순으로 마지막임을 보장하기 위해 역순 탐색
		for(int i=stones.length-1;i>=0;i--) {
			boolean  flag = true;
			stones[i] += 2;
			for(int x:stones) {
				x--;
				if(x<0) flag = false;
			}
			//if(flag && mem.find(conv(stones))==mem.end()) {//c언어의 unordered_set.find 무슨 역할?
			if(flag && !mem.contains(conv(stones))) {//java로는 hashset에 값이 있는지 확인해서 없는지 판단.
				String res = dfs(stones,k,path+(char)(i+'0'));
				if(res != "-1") return res;
			}
			stones[i] -= 2;
			for(int x:stones) {
				x++;
			}
		}
		
		return "-1";
	}
	static long conv(int[] stones) {
		long res = 0;
		
		for(int x:stones) res = res*25+x;//?
		return res;
	}
	public static void main(String[] args) {
		
	}

}
