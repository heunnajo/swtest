import java.util.*;

public class JumpJump {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
            d[i] = -1;//-1로 초기화
        }
        d[0] = 0;
        for (int i=0; i<n-1; i++) {//0부터 n-2까지
            if (d[i] == -1) continue;//제일 중요
            for (int j=1; j<=a[i]; j++) {//i의 배열값만큼만 가능ㅇ
                if (i+j >= n) {
                    break;
                }
                if (d[i+j] == -1 || d[i+j] > d[i] + 1) {
                    d[i+j] = d[i] + 1;
                }
            }
        }
        System.out.println(d[n-1]);
    }
}