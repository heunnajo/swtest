import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
  
public class CultureCell_Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
  
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  
    static int T, R, C, K;
    static boolean[][] map;
  
    static PriorityQueue<Cell> liveCells;// 살아있는 세포들(활성화 + 비활성화)
    static int res;
  
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(input.readLine());
  
        for (int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());// 세로 크기 1≤N≤50
            C = Integer.parseInt(tokens.nextToken());// 가로 크기 1≤M≤50
            K = Integer.parseInt(tokens.nextToken());// 배양 시간 1≤K≤300
            map = new boolean[R + K][C + K];
            liveCells = new PriorityQueue<>();
            res = 0;
            for (int r = 0; r < R; r++) {
                tokens = new StringTokenizer(input.readLine());
                for (int c = 0; c < C; c++) {
                    int life = Integer.parseInt(tokens.nextToken());
                    if (life > 0) {
                        // 원점 이동
                        Cell cell = new Cell(r + K / 2, c + K / 2, life, life+1);
                        map[cell.r][cell.c] = true;
                        liveCells.offer(cell);
                        // 이녀석들은 어차피 살꺼다.
                        if (cell.life * 2 > K) {
                            res++;
                        }
                    }
                }
            }
            bfs();
            output.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.println(output);
    }
    private static void bfs() {
        int time = 0;
        Cell cur;
   
        //while (time <= K) {
        while (true) {
            cur = liveCells.poll();
            time = cur.time;
   
            if (time > K) break;
   
            for (int d = 0; d < dirs.length; d++) {
                int curX = cur.r + dirs[d][0];
                int curY = cur.c + dirs[d][1];
   
                if (!map[curX][curY]) {
                    map[curX][curY] = true;
                    liveCells.offer(new Cell(curX, curY, cur.life,time + cur.life + 1));
   
                    if (time + cur.life * 2 > K) res++;
                }
            }
        }
    } // end bfs
  
  
  
    static class Cell implements Comparable<Cell> {
        int r, c;// row, col,
        int life; // 살아있는 시간,
        int time;// 턴을 확인하기 위한 값
  
        public Cell(int r, int c, int life, int time) {
            this.r = r;
            this.c = c;
            this.life = life;
            this.time = time;
        }
  
        public int compareTo(Cell o) {
            if (this.time == o.time) {
                return Integer.compare(o.life, life);
            } else {
                return Integer.compare(this.time, o.time);
            }
        }
    }
  
}