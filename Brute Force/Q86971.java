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
            //i를 제외하고 연결정보 Adj를 생성
            go(i,wires);
        }
        //System.out.println("Ans: "+Ans);
        return Ans;
    }
    static void go(int idx,int[][] wires){
        Adj = new ArrayList[N+1];//각 케이스마다 초기화 필요
        for(int i=1;i<=N;i++) Adj[i] = new ArrayList<>();
        
        for(int i=0;i<wires.length;i++){
            if(i == idx) continue;
            int u = wires[i][0]; int v = wires[i][1];
            Adj[u].add(v); Adj[v].add(u);
        }
        
        //완성된 연결정보 Adj로 그래프 탐색 : 미방문한 정점을 탐색
        int gap = getDiff();
        
        if(Ans > gap) Ans = gap;
    }
    static int getDiff(){
        boolean[] visited = new boolean[N+1];//각 케이스마다 초기화 필요
        int a = 0;
        for(int i=1;i<=N;i++){
        	if(a == 0) {
        		a = dfs(i,visited,1);
        	} 
        }
        int diff = Math.abs(a-(N-a));
        return diff;
    }
    static int dfs(int i,boolean[] visited,int idx) {
    	//i번 정점과 그 인접 정점들 탐색!
    	
    	visited[i] = true;
    	int sum = 1;
    	for(int next : Adj[i]) {
    		if(visited[next]) continue;
    		sum += dfs(next,visited,idx);
    	}
    	return sum;
    }
}
