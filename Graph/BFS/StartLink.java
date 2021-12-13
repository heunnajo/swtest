package ss;
import java.io.*;
import java.util.*;
public class StartLink {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		//초기화
		int[] dist = new int[1000001];
		boolean[] visited = new boolean[1000001];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(S);
		visited[S] = true;
		while(!q.isEmpty()) {
			int now = q.remove();
			if(now+U<=F && !visited[now+U]) {
				visited[now+U] = true;
				q.add(now+U);
				dist[now+U] = dist[now]+1;//dist[now]는 0에서 시작.
			}
			if(now-D>=1 && !visited[now-D]) {
				visited[now-D] = true;
				q.add(now-D);
				dist[now-D] = dist[now]+1;//dist[now]는 0에서 시작.
			}
		}
		if(visited[G]) System.out.println(dist[G]);
		else System.out.println("use the stairs");
	}

}
