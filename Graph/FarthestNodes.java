import java.util.*;
class FarthestNodes {
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    static int[] Dist;
    static final int INF = 987654321;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<edge.length;i++){
            int a = edge[i][0]; int b = edge[i][1];
            list[a].add(b); list[b].add(a);
        }
        Dist = new int[n+1];
        for(int i=1;i<=n;i++){
            Dist[i] = INF;
        }
        dfs(1,0);
        //Arrays.sort(Dist);
        int max = Dist[0];
        for(int i=0;i<Dist.length;i++){
            System.out.print(Dist[i]+" ");
        }
        System.out.println();
        for(int i=0;i<n-1;i++){
            if(max == Dist[i]) answer++;
        }
        return answer;
    }
    void dfs(int node,int dist){
        visited[node] = true;
        for(int a:list[node]){
            if(!visited[a]){
                Dist[a] = Math.min(dist,Dist[a]);
                dfs(a,dist+1);
            }
        }
    }
}