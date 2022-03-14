//백준 #1943 동전 분배
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N,sum;
    private static int[] dp;
    private static Pair[] coins;

    private static void Init() {
        dp = new int[sum+1];
        for (int i = 1; i <= sum; i++) dp[i] = Integer.MAX_VALUE;
    }

    private static int Solve() {
        Init();
        int answer = 0;
        if(sum%2!=0) return answer;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= sum/2; j++) {
                if(dp[j]==Integer.MAX_VALUE) continue;
                //"갯수", "금액" 조건 판단
                if((dp[j]<=coins[i].cnt) && (j+coins[i].coin<=sum/2)) {
                    if(dp[j+coins[i].coin]>dp[j] + 1) dp[j+coins[i].coin] = dp[j] + 1;
                }
                dp[j] = 0;
            }
        }
//        for (int i = 0; i <= sum; i++) {
//            if(dp[i]!=Integer.MAX_VALUE) System.out.print(i+":"+dp[i]+" ");
//        }
//        System.out.println();
        //무한대 값으로 남아있다면 0을, 그렇지 않다면 1을 리턴
        if(dp[sum/2]!=Integer.MAX_VALUE) answer = 1;
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            coins = new Pair[N];
            sum = 0;
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                coins[j] = new Pair(coin, cnt);
                sum += coin * cnt;
            }
            result.append(Solve()).append("\n");
        }
        System.out.println(result.toString());

    }

    private static class Pair {
        int coin, cnt;
        private Pair(int coin, int cnt) {
            this.coin = coin;
            this.cnt = cnt;
        }
    }
}