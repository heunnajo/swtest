package ss;
import java.io.*;
import java.util.*;

public class RemoteControl_BFS {
	static int n,m,ans;
	static boolean[] isBroken;
	static class Pair{
		int flag,num,cnt;
		Pair(int flag,int num,int cnt){
			this.flag = flag;
			this.num = num;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		String strN = br.readLine();
		n = Integer.parseInt(strN);
		m = Integer.parseInt(br.readLine());
		isBroken = new boolean[10];
		if(m==0) {
			ans = Math.min(strN.length(), Math.abs(100-n));
			System.out.println(ans);
		} else {
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<m;i++) {
				int x = Integer.parseInt(st.nextToken());
				isBroken[x] = true;
			}
			
			bfs(100);//n으로 가는 최소 횟수 찾는다!
		}
	}
	static void bfs(int start) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[2][1000000];//첫 인덱스:+-/숫자를 의미
		q.add(new Pair(0,start,0));//(flag,num,cnt) = (0,start,0)
		int cnt,num,flag;
		
		cnt = 0;
		while(!q.isEmpty()){
			Pair cur = q.poll();//flag,num,cnt
			flag = cur.flag;
			num = cur.num;
			cnt = cur.cnt;
			//System.out.println("현재 채널: "+cur.num);
			if(num == n) {
				break;
			}
			if(visited[flag][num]) continue;//중복 방지!
			visited[flag][num] = true;//일관되게 방문처리. +-/숫자 케이스에 따라 방문 상태가 다름.
			
			//1.+1
			if(num+1<=1000000)
				q.add(new Pair(0,cur.num+1,cnt+1));
			//2.-1
			if(num-1>=0)
				q.add(new Pair(0,cur.num-1,cnt+1));
			
			//3.숫자
			if(flag == 1 || num == 100) {
				for(int i=0;i<10;i++) {
					if(isBroken[i]) continue;
					
					if(flag == 1 && num*10+i <1000000 ) {//현재 flag = 1: 이전에 숫자를 누른 경우, 범위체크!
						q.add(new Pair(1,num*10+i,cnt+1));
					} else if(cur.flag == 0) {//이전에 +-를 눌렀음:현재 누르는 숫자로 리셋됨!
						q.add(new Pair(1,i,cnt+1));
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
