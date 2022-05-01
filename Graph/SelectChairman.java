import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
    static final int INF = 987654321;
    static int N,arr[][];
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        N = Integer.parseInt(br.readLine());
 
        arr = new int[N + 1][N + 1];
 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    arr[i][j] = INF;
                }
            }
        }
 
        while (true) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
 
            if (u == -1 && v == -1) break;
 
            arr[u][v] = arr[v][u] = 1; // 친구 관계는 양방향
        }
 
        FloydWarshall();
 
        int leaderScore = INF;
 
        int[] scoreArr = new int[N + 1]; // 친구 점수 목록
 
        for (int i = 1; i <= N; i++) {
            int score = 0;
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] != INF) {
                    score = Math.max(score, arr[i][j]);
                }
            }
 
            scoreArr[i] = score;
            leaderScore = Math.min(leaderScore, score);
        }
 
        StringBuilder title = new StringBuilder();
        title.append(leaderScore + " ");
 
        int leaderNum = 0;
 
        StringBuilder body = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (leaderScore == scoreArr[i]) {
                leaderNum++;
                body.append(i + " ");
            }
        }
 
        title.append(leaderNum + "\n");
 
        System.out.print(title);
        System.out.print(body + "\n");
    }
    static void FloydWarshall() {
    	for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
    }
}