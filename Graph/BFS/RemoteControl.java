package ss;
import java.io.*;
import java.util.*;

public class RemoteControl_BFS {
	static int n,m,ans;
	static boolean[] isBroken;
	static class Pair{
		int num,dist;
		Pair(int num,int dist){
			this.num = num;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		for(int i=0;i<m;i++) {
			int x = Integer.parseInt(br.readLine());
			isBroken[x] = true;
		}
		ans = 987654321;//초기화값이 적절할까?!
		bfs(100);//n으로 가는 최소 횟수 리턴
		System.out.println(ans);
	}
	static void bfs(int start) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[2][500000];//첫 인덱스:+-/숫자를 의미
		q.add(new Pair(start,0));
		while(!q.isEmpty()){
			Pair cur = q.poll();
			
			if(cur.num == n) {
				//n에 도달했다고 해서 그게 최ㅗ소소값을 보장하지는 않음!=>그럼 큐를 계속 돌게 나두나..?무한루프..?
				ans = Math.min(ans, cur.dist);
			}
			visited[0][cur.num] = true;//여기서 좀 막힘.일관되게 방문처리를 하고 싶은데, +-/숫자 케이스에 따라 방문 상태가 다른데 어떻하짖?! 
			
			//1.+1
			q.add(new Pair(cur.num+1,cur.dist+1));
			//2.-1
			q.add(new Pair(cur.num-1,cur.dist+1));
			
			//3.숫자
			for(int i=0;i<=9;i++) {
				if(isBroken[i]) continue;
				q.add(new Pair(cur.num*10+i,cur.dist+1));
			}
		}
	}
}
