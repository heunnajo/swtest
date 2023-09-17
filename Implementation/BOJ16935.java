import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//배열 돌리기3
public class BOJ16935 {

    static int rowSZ, colSZ, opCnt, rotateCnt;
    static int[] op;
    static int[][] arr, tempArr, ans;
    static List<int[][]> compressionArrList = new ArrayList<>(4);

    static void solve() {
        compressionArr();

        for (int i = 0; i < opCnt; i++) {
            int curOp = op[i];
            if (curOp == 1) operator1();
            else if (curOp == 2) operator2();
            else if (curOp == 3) operator3();
            else if (curOp == 4) operator4();
            else if (curOp == 5) operator5();
            else if (curOp == 6) operator6();
        }
        showAns();
    }

    private static void compressionArr() {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            int[][] arr = new int[2][2];
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 2; c++) {
                    arr[r][c] = num++;
                }
            }
            compressionArrList.add(arr);
        }
    }

    /**
     * 상하 뒤집기
     */
    static void operator1() {
        for (int i = 0; i < 4; i++) {
            int[][] arr = compressionArrList.get(i);
            swapArr(arr, 0, 0, 1, 0);
            swapArr(arr, 0, 1, 1, 1);
        }
        swapCompressionArr(0, 2);
        swapCompressionArr(1, 3);
    }

    /**
     * 좌우 뒤집기
     */
    static void operator2() {
        for (int i = 0; i < 4; i++) {
            int[][] arr = compressionArrList.get(i);
            swapArr(arr, 0, 0, 0, 1);
            swapArr(arr, 1, 0, 1, 1);
        }
        swapCompressionArr(0, 1);
        swapCompressionArr(2, 3);

    }

    /**
     * 시계 방향으로 회전
     */
    static void operator3() {
        rotateRightCompressionArr();
        for (int i = 0; i < 4; i++) {
            rotateRightArr(compressionArrList.get(i));
        }
        rotateCnt++;
    }

    /**
     * 반시가 방향으로 회전
     */
    static void operator4() {
        rotateLeftCompressionArr();
        for (int i = 0; i < 4; i++) {
            rotateLeftArr(compressionArrList.get(i));
        }
        rotateCnt++;
    }

    static void operator5() {
        rotateRightCompressionArr();
    }

    static void operator6() {
        rotateLeftCompressionArr();
    }

    static void swapCompressionArr(int arrIdx1, int arrIdx2) {
        int[][] tempArr = compressionArrList.get(arrIdx1);
        compressionArrList.set(arrIdx1, compressionArrList.get(arrIdx2));
        compressionArrList.set(arrIdx2, tempArr);
    }

    static void swapArr(int[][] arr, int r1, int c1, int r2, int c2) {
        int temp = arr[r1][c1];
        arr[r1][c1] = arr[r2][c2];
        arr[r2][c2] = temp;
    }

    static void rotateRightCompressionArr() {
        int[][] temp = compressionArrList.get(0);
        compressionArrList.set(0, compressionArrList.get(2));
        compressionArrList.set(2, compressionArrList.get(3));
        compressionArrList.set(3, compressionArrList.get(1));
        compressionArrList.set(1, temp);
    }

    static void rotateLeftCompressionArr() {
        int[][] temp = compressionArrList.get(0);
        compressionArrList.set(0, compressionArrList.get(1));
        compressionArrList.set(1, compressionArrList.get(3));
        compressionArrList.set(3, compressionArrList.get(2));
        compressionArrList.set(2, temp);
    }

    static void rotateRightArr(int[][] arr) {
        int temp = arr[0][0];
        arr[0][0] = arr[1][0];
        arr[1][0] = arr[1][1];
        arr[1][1] = arr[0][1];
        arr[0][1] = temp;
    }

    static void rotateLeftArr(int[][] arr) {
        int temp = arr[0][0];
        arr[0][0] = arr[0][1];
        arr[0][1] = arr[1][1];
        arr[1][1] = arr[1][0];
        arr[1][0] = temp;
    }

    static void showAns() {
        int rs = rowSZ;
        int cs = colSZ;
        if (rotateCnt % 2 == 1) {
            rs = colSZ;
            cs = rowSZ;
        }
        ans = new int[rs][cs];

        for (int i = 0; i < 4; i++) {
            int[][] comArr = compressionArrList.get(i);
            int[][] extractedArr = extractArr(comArr);

            initAns(i, comArr, extractedArr, rs, cs);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void initAns(int idx, int[][] comArr, int[][] extArr, int rs, int cs) {
        int[][] temp = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = i * 2 + j;
            }
        }

        int[][] endComArr = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                endComArr[i][j] = comArr[i][j] % 4;
            }
        }

        Queue<State> que = new LinkedList<>();
        Set<State> set = new HashSet<>();
        State start = new State(temp, extArr);
        State end = new State(endComArr, null);
        que.add(start);
        set.add(start);

        while (!que.isEmpty()) {
            State cur = que.poll();
            if (cur.equals(end)) {
                int r = 0;
                int c = 0;
                if (idx == 1) {
                    c = ans[0].length / 2;
                } else if (idx == 2) {
                    r = ans.length / 2;
                } else if (idx == 3) {
                    r = ans.length / 2;
                    c = ans[0].length / 2;
                }
                for (int i = 0; i < ans.length / 2; i++) {
                    for (int j = 0; j < ans[0].length / 2; j++) {
                        ans[i + r][j + c] = cur.arr[i][j];
                    }
                }
                return;
            }

            for (int i = 0; i < 4; i++) {
                State next = cur.rotateRight();
                if (!set.contains(next)) {
                    set.add(next);
                    que.add(next);
                }
                State next2 = next.flipDown();
                State next3 = next.flipRight();
                if (!set.contains(next2)) {
                    set.add(next2);
                    que.add(next2);
                }
                if (!set.contains(next3)) {
                    set.add(next3);
                    que.add(next3);
                }
                cur = next;
            }
        }

    }

    static class State {
        int[][] comArr;
        int[][] arr;

        public State(int[][] comArr, int[][] arr) {
            this.comArr = comArr;
            this.arr = arr;
        }

        public State rotateRight() {
            int[][] rotComArr = rotateRight(comArr);
            int[][] rotArr = rotateRight(arr);
            return new State(rotComArr, rotArr);
        }

        public State flipRight() {
            int[][] copyComArr = new int[2][2];
            int[][] copyArr = new int[arr.length][arr[0].length];
            for (int i = 0; i < copyComArr.length; i++) {
                System.arraycopy(comArr[i], 0, copyComArr[i], 0, comArr[0].length);
            }
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, copyArr[i], 0, arr[i].length);
            }

            swap(0, 0, 0, 1, copyComArr);
            swap(1, 0, 1, 1, copyComArr);
            int rowSZ = copyArr.length;
            int colSZ = copyArr[0].length;
            for (int i = 0; i < rowSZ; i++) {
                for (int j = 0; j < colSZ / 2; j++) {
                    swap(i, j, i, colSZ - 1 - j, copyArr);
                }
            }

            return new State(copyComArr, copyArr);
        }

        public State flipDown() {
            int[][] copyComArr = new int[2][2];
            int[][] copyArr = new int[arr.length][arr[0].length];
            for (int i = 0; i < copyComArr.length; i++) {
                System.arraycopy(comArr[i], 0, copyComArr[i], 0, comArr[0].length);
            }
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, copyArr[i], 0, arr[i].length);
            }
            swap(0, 0, 1, 0, copyComArr);
            swap(0, 1, 1, 1, copyComArr);
            int rowSZ = copyArr.length;
            int colSZ = copyArr[0].length;
            for (int i = 0; i < rowSZ / 2; i++) {
                for (int j = 0; j < colSZ; j++) {
                    swap(i, j, rowSZ - 1 - i, j, copyArr);
                }
            }

            return new State(copyComArr, copyArr);

        }

        public void swap(int r1, int c1, int r2, int c2, int[][] arr) {
            int temp = arr[r1][c1];
            arr[r1][c1] = arr[r2][c2];
            arr[r2][c2] = temp;
        }

        public int[][] rotateRight(int[][] arr) {
            int rowSZ = arr.length;
            int colSZ = arr[0].length;
            int[][] ret = new int[colSZ][rowSZ];
            for (int i = 0; i < colSZ; i++) {
                for (int j = 0; j < rowSZ; j++) {
                    ret[i][j] = arr[rowSZ - 1 - j][i];
                }
            }
            return ret;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.deepEquals(comArr, state.comArr);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(comArr);
        }
    }


    private static void swapSize() {
        int temp = rowSZ;
        rowSZ = colSZ;
        colSZ = temp;
    }

    static int[][] extractArr(int[][] paramArr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[][] ret = new int[rowSZ / 2][colSZ / 2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                min = Math.min(min, paramArr[i][j]);
                max = Math.max(max, paramArr[i][j]);
            }
        }

        int arrNum = min / 4;
        int r = 0;
        int c = 0;
        if (arrNum == 1) {
            c = colSZ / 2;
        } else if (arrNum == 2) {
            r = rowSZ / 2;
        } else if (arrNum == 3) {
            r = rowSZ / 2;
            c = colSZ / 2;
        }
        for (int i = 0; i < rowSZ / 2; i++) {
            for (int j = 0; j < colSZ / 2; j++) {
                ret[i][j] = arr[i + r][j + c];
            }
        }

        return ret;
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        // FastReader fr = new FastReader("input/input1.txt");
        FastReader fr = new FastReader();

        rowSZ = fr.nextInt();
        colSZ = fr.nextInt();
        opCnt = fr.nextInt();
        arr = new int[rowSZ][colSZ];
        tempArr = new int[2][2];
        ans = new int[rowSZ][colSZ];
        op = new int[opCnt];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        for (int i = 0; i < opCnt; i++) {
            op[i] = fr.nextInt();
        }
        solve();
    }

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
