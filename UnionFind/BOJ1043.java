//거짓말
class BOJ1043 {

    static int[] set;

    public static void main(String[] args) throws Exception {

        int N = read();
        int M = read();

        int knownNum = read();
        if (knownNum == 0) {
            System.out.print(M);
            return;
        }

        set = new int[N + 1];
        int highRank = -2501;
        for (int i = 0; i <= N; i++) set[i] = -1;

        boolean[] isKnown = new boolean[N + 1];
        for (int i = 0; i < knownNum; i++) {
            int known = read();
            isKnown[known] = true;
            set[known] = highRank;
        }

        int[][] parties = new int[M][];
        int[] people = null;

        for (int i = 0; i < M; i++) {

            int peopleNum = read();
            people = parties[i] = new int[peopleNum];

            int host = people[0] = read();

            for (int j = 1; j < peopleNum; j++) {
                union(host, people[j] = read());
            }

        }

        int party = M;
        for (int i = 0; i < M; i++) {

            people = parties[i];
            int peopleNum = people.length;

            for (int j = 0; j < peopleNum; j++) {
                if (isKnown[find(people[j])]) {
                    party--;
                    break;
                }
            }

        }

        System.out.print(party);

    }

    static boolean union(int u, int v) {
        if ((u = find(u)) == (v = find(v))) return false;

        if (set[u] < set[v]) {
            set[u] += set[v];
            set[v] = u;
        } else {
            set[v] += set[u];
            set[u] = v;
        }

        return true;
    }

    static int find(int u) {
        return set[u] < 0 ? u : (set[u] = find(set[u]));
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}