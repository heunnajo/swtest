package ss;
import java.util.*;

class Tuple implements Comparable<Tuple> {
    int row, col, size;
    int index;
    Tuple(int row, int col, int size, int index) {
        this.row = row;
        this.col = col;
        this.size = size;
        this.index = index;
    }
    public int compareTo(Tuple that) {
        if (this.index < that.index) {
            return -1;
        } else if (this.index == that.index) {
            return 0;
        } else {
            return 1;
        }
    }
}

public class RotateArray4_2nd {
    static boolean next_permutation(Tuple[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1].index >= a[i].index) {
            i -= 1;
        }
        if (i <= 0) return false;

        int j = a.length-1;
        while (a[j].index <= a[i-1].index) {
            j -= 1;
        }

        Tuple temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    static void go(int[][] a, Tuple t) {
        int row = t.row;
        int col = t.col;
        int size = t.size;
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int s=1; s<=size; s++) {
            ArrayList<Integer> group = new ArrayList<>();
            // (r-s, c-s) -> (r-s, c+s)
            for (int r=row-s, c=col-s; c<col+s; c++) {
                group.add(a[r][c]);
            }
            // (r-s, c+s) -> (r+s, c+s)
            for (int r=row-s, c=col+s; r<row+s; r++) {
                group.add(a[r][c]);
            }
            // (r+s, c+s) -> (r+s, c-s)
            for (int r=row+s, c=col+s; c>col-s; c--) {
                group.add(a[r][c]);
            }
            // (r+s, c-s) -> (r-s, c-s)
            for (int r=row+s, c=col-s; r>row-s; r--) {
                group.add(a[r][c]);
            }
            groups.add(group);
        }
        //껍질 별로 배열값 넣은 각 리스트를 회전시킨다! 
        //각 리스트에 인덱스 0번째부터 각 껍데기에 해당하는 배열값들이 들어가있다!
        //따라서 s-1로 2차원 배열 리스트에 조회한다!
        for (int s=1; s<=size; s++) {
            ArrayList<Integer> group = groups.get(s-1);
            Collections.rotate(group, 1);//회전
            int index = 0;
            // (r-s, c-s) -> (r-s, c+s)
            for (int r=row-s, c=col-s; c<col+s; c++) {
                a[r][c] = group.get(index++);
            }
            // (r-s, c+s) -> (r+s, c+s)
            for (int r=row-s, c=col+s; r<row+s; r++) {
                a[r][c] = group.get(index++);
            }
            // (r+s, c+s) -> (r+s, c-s)
            for (int r=row+s, c=col+s; c>col-s; c--) {
                a[r][c] = group.get(index++);
            }
            // (r+s, c-s) -> (r-s, c-s)
            for (int r=row+s, c=col-s; r>row-s; r--) {
                a[r][c] = group.get(index++);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] a = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        Tuple[] d = new Tuple[k];
        for (int i=0; i<k; i++) {
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            int s = sc.nextInt();
            d[i] = new Tuple(r, c, s, i);
        }
        int ans = 100*100;
        do {
        	//0.원래 배열 복사해서 쓰기
            int[][] b = new int[n][m];
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    b[i][j] = a[i][j];
                }
            }
            //1.현재 순서로 모든 배열 회전 연산 수행!
            for (Tuple t : d) {
                go(b, t);
            }
            //2.배열 최솟값 구하기
            for (int i=0; i<n; i++) {
                int sum = 0;
                for (int j=0; j<m; j++) {
                    sum += b[i][j];
                }
                if (ans > sum) ans = sum;
            }
        } while (next_permutation(d));//3.다음 증가 순열 존재하면 구하고, 0~2 반복!
        System.out.println(ans);
    }
}