//Q118669 등산 코스 정하기
import java.util.*;

class edge implements Comparable<edge> {
    int a;
    int b;
    int cost;

    edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(edge o) {
        return this.cost - o.cost;
    }

}

class next {
    int b;
    int cost;

    next(int b, int cost) {
        this.b = b;
        this.cost = cost;
    }
}

class Solution {
    static int n;
    static ArrayList<next>[] g;
    static int[][] paths;
    static int[] gates;
    static int[] summits;
    static boolean[] isSummit;
    static boolean[] isGate;
    static ArrayList<edge> edges;
    static int[] p;
    static int ans = 987654321;
    static int ansMountain = 987654321;

    public int[] solution(int nn, int[][] pat, int[] gat, int[] sum) {
        n = nn;
        paths = pat;
        gates = gat;
        summits = sum;
        edges = new ArrayList<edge>();
        g = new ArrayList[n];
        p = new int[n];
        isSummit = new boolean[n];
        isGate = new boolean[n];

        for (int i = 0; i < gates.length; i++) {
            isGate[gates[i] - 1] = true;
        }

        for (int i = 0; i < summits.length; i++) {
            isSummit[summits[i] - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<next>();
            p[i] = -1;
        }

        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths[i].length; j++) {
                int a = paths[i][0] - 1;
                int b = paths[i][1] - 1;
                int cost = paths[i][2];
                edges.add(new edge(a, b, cost));
            }
        }
        Collections.sort(edges);
        int count = 0;
        for (edge e : edges) {
            if (count == n - 1)
                break;
            if (merge(e.a, e.b)) {
                g[e.a].add(new next(e.b, e.cost));
                g[e.b].add(new next(e.a, e.cost));
                count++;
            }
        }

        for (int i = 0; i < gates.length; i++) {
            dfs(-1, gates[i] - 1, 0);
        }
        int[] answer = new int[2];
        answer[0] = ansMountain;
        answer[1] = ans;
        return answer;

    }

    public static int find(int x) {
        if (p[x] == -1) {
            return x;
        }

        return p[x] = find(p[x]);
    }

    public static boolean merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;
        p[a] = b;
        return true;
    }

    public static void dfs(int prev, int now, int intensity) {
        if (isSummit[now]) {
            if (ans == intensity) {
                ansMountain = Math.min(ansMountain, now + 1);
                ans = Math.min(ans, intensity);
            } else if (ans > intensity) {
                ansMountain = now + 1;
                ans = Math.min(ans, intensity);
            }
            return;
        }
        for (next ne : g[now]) {
            if (ne.b != prev && !isGate[ne.b]) {
                dfs(now, ne.b, Math.max(intensity, ne.cost));
            }
        }
    }

}