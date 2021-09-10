import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkMiddleSchool_Solution
 {
    static int dy[] = {0, 1, 0, -1};
    static int dx[] = {1, 0, -1, 0};


    static int N;
    static int M;//색깔수
    static int[][] map;
    static boolean[][] isStarted;

    private static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sumScore = 0;
        //블록그룹이 형성되지않는경우 break
        while (true) {
            isStarted = new boolean[N][N];
            Queue<Point> q = new LinkedList<>();
            int maxGroupCnt = 0;
            int maxRainbowCnt = 0;
            int targetY = -1;
            int targetX = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (0 < map[i][j] && map[i][j] < 6 && !isStarted[i][j]) {
                        isStarted[i][j] = true;
                        int GroupCnt = 0;
                        int rainbowCnt = 0;
                        int GroupY = i;
                        int GroupX = j;

                        int color = map[i][j];
                        q.add(new Point(i, j));
                        boolean[][] isVisited = new boolean[N][N];
                        while (!q.isEmpty()) {
                            Point p = q.poll();
                            GroupCnt++;
                            isVisited[p.y][p.x] = true;
                            for (int d = 0; d < 4; d++) {
                                int jy = p.y + dy[d];
                                int ix = p.x + dx[d];
                                if (safe(jy, ix) && (map[jy][ix] == 0 || map[jy][ix] == color) && !isVisited[jy][ix]) {
                                    isVisited[jy][ix] = true;
                                    q.add(new Point(jy, ix));
                                    if(map[jy][ix] == 0)
                                        rainbowCnt++;
                                    if (map[jy][ix] == color) {
                                        isStarted[jy][ix] = true;
                                        if (GroupY > jy) {
                                            GroupY = jy;
                                            GroupX = ix;
                                        } else if (GroupY == jy && GroupX > ix) {
                                            GroupX = ix;
                                        }
                                    }
                                }
                            }
                        }

                        //최대갯수 찾기
                        if (maxGroupCnt < GroupCnt) {
                            maxGroupCnt = GroupCnt;
                            maxRainbowCnt = rainbowCnt;
                            targetX = GroupX;
                            targetY = GroupY;
                        } else if (maxGroupCnt == GroupCnt) {
                            if(maxRainbowCnt < rainbowCnt){
                                maxRainbowCnt = rainbowCnt;
                                targetY = GroupY;
                                targetX = GroupX;
                            }else if(maxRainbowCnt == rainbowCnt ) {
                                if (targetY < GroupY) {
                                    targetY = GroupY;
                                    targetX = GroupX;
                                } else if (targetY == GroupY && targetX < GroupX) {
                                    targetX = GroupX;
                                }
                            }
                        }

                    }
                }
            }
            if (maxGroupCnt < 2)
                break;
            sumScore += Math.pow(maxGroupCnt, 2);
            //제거된 위치는 6으로 치환
            q = new LinkedList<>();
            q.add(new Point(targetY, targetX));
            int color = map[targetY][targetX];
            while (!q.isEmpty()) {
                Point p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int jy = p.y + dy[d];
                    int ix = p.x + dx[d];
                    if (safe(jy, ix) && (map[jy][ix] == 0 || map[jy][ix] == color)) {
                        map[jy][ix] = 6;
                        q.add(new Point(jy, ix));
                    }
                }
            }
            gravity();
            leftRotate();
            gravity();
        }

        System.out.println(sumScore);
    }

    private static boolean safe(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    private static void gravity() {
        for (int j = 0; j < N; j++) {
            int[] list = new int[N];
            int idx = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == -1 ) {
                    for(; idx > i;idx--){
                        list[idx] = 6;
                    }
                }
                if (map[i][j] != 6 ) {
                    list[idx--] = map[i][j];
                }
            }
            for(;idx >= 0;idx--){
                list[idx] = 6;
            }
            for (int i = 0; i < N; i++) {
                map[i][j] = list[i];
            }
        }
    }

    private static void leftRotate() {
        int[][] mapClone = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mapClone[i][j] = map[j][N-1-i];
            }
        }
        for (int i = 0; i < N; i++) {
            System.arraycopy(mapClone[i], 0, map[i], 0, N);
        }
    }

}