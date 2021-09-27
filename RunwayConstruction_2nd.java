import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class RunwayConstruction_2nd {
    static int ans,T,N,K,Map[][];
    static boolean solve(int dir,int idx) {
        int[] arr = new int[N];
        boolean[] constructed = new boolean[N];
 
        for(int i=0;i<N;i++) {
            arr[i] = (dir==0)?Map[idx][i]:Map[i][idx];
        }
        //본격 경사로 건설 판단& 건설
        //내리막
        for(int i=0;i<N-1;i++) {
            if(arr[i] == arr[i+1]) continue;//컨티뉴처리, 모두 같으면 디폴트값인 true를 리턴.
            if(Math.abs(arr[i]-arr[i+1]) > 1) return false;//백트랙킹.1초과 차이나면 경사로 건설 못함.=>활주로 건설 못함.
            //1.내리막
            if(arr[i]-arr[i+1] == 1) {
                for(int k=i+1;k<=i+K;k++) {
                    if(k>N-1 || constructed[k] || arr[k] != arr[i+1]) return false;
                    constructed[k] = true;
                }
            }
             
            //2.오르막
            if(arr[i+1]-arr[i]==1) {
                for(int k=i;k>=i+1-K;k--) {
                    if(k<0 || constructed[k] || arr[k] != arr[i]) return false;
                    constructed[k] = true;
                }
            }
                 
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        T = Integer.parseInt(br.readLine());
         
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            Map = new int[N][N];
             
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            ans = 0;
            for(int i=0;i<N;i++) {
                if(solve(0,i)) ans++;//i번째 가로 행.
                if(solve(1,i)) ans++;//i번째 세로 열.
            }
            sb.append("#"+t+" "+ans+"\n");
        }
        System.out.print(sb);
         
    }
}