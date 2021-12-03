package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Teleport3_permutation {
    static Pair s, e;
    static ArrayList<Teleport> tps = new ArrayList<>();
    static boolean[] check = new boolean[3];
    static long min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());
        s = new Pair(xs, ys);
        st = new StringTokenizer(br.readLine(), " ");
        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());
        e = new Pair(xe, ye);

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            tps.add(new Teleport(new Pair(x1, y1), new Pair(x2, y2)));
        }

        // i : 거치는 텔레포트의 수
        for (int i = 0; i <= 3; i++) {
            go(0, i, 0, s);
        }

        System.out.print(min);
    }


    static void go(int index, int ntp, long sum, Pair cur) {

        if (index == ntp) {
            min = Math.min(min, sum + getDist(cur, e));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!check[i]) {
                check[i] = true;
                Teleport t = tps.get(i);
                go(index+1, ntp, sum + getDist(cur, t.p1) + 10, t.p2);
                go(index+1, ntp, sum + getDist(cur, t.p2) + 10, t.p1);
                check[i] = false;
            }
        }
    }

    static long getDist(Pair p1, Pair p2) {
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
    }

    static class Pair {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Teleport {
        private final Pair p1, p2;
        public Teleport(Pair p1, Pair p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
