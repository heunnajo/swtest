import java.util.*;
//전력망을 둘로 나누기 Q86971
public class Solution {
	static int N;
	static int INF = Integer.MAX_VALUE;
	static int Ans;
	static ArrayList<Integer>[] Adj;
    
    public static int solution(int n, int[][] wires) {
    	
        N = n;
        Ans = INF;
        
        for(int i=0;i<wires.length;i++){
            go(i,wires);
        }
        return Ans;
    }
    static void go(int idx,int[][] wires){
        Adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++) Adj[i] = new ArrayList<>();
        
        for(int i=0;i<wires.length;i++){
            if(i == idx) continue;
            int u = wires[i][0]; int v = wires[i][1];
            Adj[u].add(v); Adj[v].add(u);
        }

        int gap = getDiff();
        
        if(Ans > gap) Ans = gap;
    }
    static ArrayList<Integer> countByGroup;
    static int getDiff(){
    	countByGroup = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        int count = 0;
        for(int i=1;i<=N;i++){
        	if(visited[i]) continue;
        	count = bfs(i,visited);
        	countByGroup.add(count);
        }
        int a = countByGroup.get(0);
        int b = countByGroup.get(1);
        
        int diff = Math.abs(a-b);
        return diff;
    }
    static int bfs(int st,boolean[] v) {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(st);
    	v[st] = true;
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		for(int next:Adj[cur]) {
    			if(v[next]) continue;
    			v[next] = true;
    			q.offer(next);
    			cnt++;
    		}
    	}
    	return cnt;
    }
    
    static int dfs(int i,boolean[] visited) {
    	visited[i] = true;
    	int sum = 1;
    	for(int next : Adj[i]) {
    		if(visited[next]) continue;
    		sum += dfs(next,visited);
    	}
    	return sum;
    }
}
