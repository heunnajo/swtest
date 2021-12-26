import java.util.LinkedList;

public class Tomato_7569 {

    private static final int CUT = (1 << 7) - 1;
    private static final int BITSIZE = 1 << 3;

    public static void main(String[] args) throws Exception {

        // 입력값 할당
        int M = read(), N = read(), H = read();

        // 토마토상자 생성
        boolean[][][] tomatoBox = new boolean[H][N][M];

        // 덜 익은 토마토 수
        int greenTomato = 0;

        // Queue를 미리 생성하고 상자에 입력값 할당 시 미리 Queue에 넣어둔다.
        // 덜 익은 토마토가 하나도 없는 경우에는 성능에 저하가 올 수 있다.
        // 하지만 어차피 BFS 수행 시 다시 익은 토마토의 위치를 찾는 것이 더 좋지 않다.
        // 또한 별도의 배열을 생성하고 좌표를 할당하는 경우도 메모리를 낭비하게 될 수 있다.
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {

                    // 입력값 할당
                    int tomato = System.in.read();

                    // 빈 공간일 경우
                    if (tomato == '-') {
                        System.in.read();
                        tomatoBox[i][j][k] = true;
                    }

                    // 익은 토마토일 경우
                    else if (tomato == 49) {
                        tomatoBox[i][j][k] = true;
                        int pos = (i << (BITSIZE << 1)) | (j << BITSIZE) | k;
                        queue.offer(pos);
                    }

                    // 덜 익은 토마토일 경우
                    else greenTomato++;

                    System.in.read(); // WhiteSpace
                }
            }
        }

        // 덜 익은 토마토가 하나도 없을 경우 0 출력 후 종료
        if (greenTomato == 0) {
            System.out.print(0);
            return;
        }

        // BFS 수행
        int result = greenToRedBFS(tomatoBox, greenTomato, queue);

        // 결과 출력
        System.out.print(result);

    }

    private static int greenToRedBFS(boolean[][][] box, int greenTomato, LinkedList<Integer> queue) {

        int day = 0; // 며칠 걸리는지 세기 - BFS Depth Check
        int red = 0; // 초록이었다가 빨강으로 변한 토마토 수

        // 상자 크기
        int H = box.length;
        int N = box[0].length;
        int M = box[0][0].length;

        // 방향벡터 - 위 아래 동 서 남 북
        int[] dx = {0, 0, 1, -1, 0, 0};
        int[] dy = {0, 0, 0, 0, 1, -1};
        int[] dz = {1, -1, 0, 0, 0, 0};

        // BFS 수행
        while (!queue.isEmpty()) {

            // 토마토가 다 익었을 경우 며칠 걸렸는지 return
        	if (red == greenTomato) return day;

            // Queue의 크기
            int size = queue.size();

            // Queue에 담겨있는 만큼 반복 수행 - Depth Check
            for (int d = 0; d < size; d++) {

                int pos = queue.poll();
                int m = pos & CUT;
                int n = (pos >> BITSIZE) & CUT;
                int h = (pos >> (BITSIZE << 1)) & CUT;

                for (int i = 0; i < 6; i++) {

                    int nh = h + dz[i];
                    int nn = n + dy[i];
                    int nm = m + dx[i];

                    // 유효성 확인 - 박스를 넘어가는지, 이미 방문했는지
                    if (nh < 0 || nn < 0 || nm < 0
                        || nh == H || nn == N || nm == M
                        || box[nh][nn][nm]) continue;

                    red++; // 익은 토마토 갱신

                    // 방문처리 및 Queue에 좌표 넣기
                    box[nh][nn][nm] = true;
                    queue.offer((nh << (BITSIZE << 1)) | (nn << BITSIZE) | nm);

                }

            }

            // 하루 지남
            day++;

        }

        return -1; // 위 기저조건에 통과하지 못할 경우 모든 토마토를 익힐 수 없음

    }

    private static int read() throws Exception {

        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;

        return N;

    }

}