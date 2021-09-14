public class HikingTrail_BFS {
    static int[][] search = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, K, MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            MAX = Integer.MIN_VALUE;
            StringTokenizer token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());
            int[][] arr = new int[N][N];
            int max = 0;
            for (int r = 0; r < N; r++) {
                token = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Integer.parseInt(token.nextToken());
                    if (max < arr[r][c]) {
                        max = arr[r][c];
                    }
                }
            }
            List<Point> list = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (max == arr[r][c]) {list.add(new Point(r, c));}
                }
            }
            for (int k = 0; k <= K; k++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (arr[r][c] - k < 0) {
                            continue;
                        } else {
                            arr[r][c] -= k;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            bfsQueue(list.get(i).r, list.get(i).c, arr);
                        }
                        arr[r][c] += k;
                    }
                }
            }
            System.out.println("#" + tc + " " + MAX);
        }
    }

    static void bfsQueue(int r, int c, int[][] copy) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                Point top = queue.poll();
                for (int s = 0; s < search.length; s++) {
                    int nr = top.r + search[s][0];
                    int nc = top.c + search[s][1];
                    if (isIn(nr, nc)  && copy[top.r][top.c] > copy[nr][nc]) {
                        queue.offer(new Point(nr, nc));
                    }
                }
            }
        }
        if (MAX < time) {
            MAX = time;
        }
    }
 
    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
 
    static class Point {
        int r, c;
 
        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
 
        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }
    }
}
