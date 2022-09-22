import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//배열에서 이동 (Binary Search + BFS/DFS)
public class BOJ1981_Solution {
    static int N, map[][], max, min, ans;
    static int[] dr = {0,0,-1,1}, dc = {1,-1,0,0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        min = 200; max = 0; ans = 200;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int end = max;
        max = map[0][0];
        while(min <= map[0][0] && max <= end){
//            System.out.println("~~~~~~~~~~~");
            visited = new boolean[N][N];
            if(dfs(0,0)) {
                ans = Math.min(ans, max-min);
                min++;
//                System.out.printf("true, min: %d, max: %d\n", min, max);
            }
            else{
                max++;
//                System.out.printf("false, min: %d max: %d\n",min, max);
            }
//            System.out.printf("ans: %d\n", ans);
        }
        System.out.println(ans);
    }

    static boolean dfs(int r, int c){
        if(r == N-1 && c == N-1) return true;
        visited[r][c] = true;

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] < min || map[nr][nc] > max) continue;
//                System.out.printf("nr: %d, nc: %d, value: %d\n", nr, nc, map[nr][nc]);
            if(dfs(nr, nc)) return true;
        }
        return false;
    }

    static boolean bfs(){
//        System.out.println("===========");
//        System.out.printf("min: %d, max: %d\n", min, max);

        Queue<int[]> q = new LinkedList<int[]>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[]{0,0});
        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();

            visited[r][c] = true;
//            System.out.printf("r: %d, c:%d, value: %d\n", r, c, map[r][c]);
            if(r == N-1 && c == N-1)
                return true;

            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] < min || map[nr][nc] > max) continue;
//                System.out.printf("nr: %d, nc: %d, value: %d\n", nr, nc, map[nr][nc]);
                q.offer(new int[]{nr,nc});
            }
        }
        return false;
    }
}