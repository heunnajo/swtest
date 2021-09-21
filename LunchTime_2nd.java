import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;
public class LunchTime_2nd {
     
    static int ans,N,T,Map[][];
    static class Point{
        int x,y,K;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
        Point(int x,int y,int K){
            this.x = x;
            this.y = y;
            this.K = K;//계단인 경우 K값을 함께 저장!
        }
    }
    static Point[] People;
    static Point[] Stairs;
    static int pCnt;//총 사람수를 저장.
    static int sCnt;
    static int[] Selected;
    static void solve(int index) {//solve는 index번째 사람이 계단 이동하는데 걸린 시간을 더해간다? 아님. 이상함. 시간의 차원이 필요하다.
        //재귀를 종료할 때 : 모든 선택한 경우, 시뮬레이션!
        if(index == pCnt) {
            int time=0;//각 경우의 수마다 전체 사람 이동 완료 시간 저장=계단1 이동시간+계단2 이동시간이기 때문에 스코프는 계단 반복문 밖에 위치해야함!
            for(int s=0;s<sCnt;s++) {
                LinkedList<Integer> aT = new LinkedList<>();
                for(int p=0;p<pCnt;p++) {
                    if(Selected[p] == s) {
                        aT.add(Math.abs(People[p].x-Stairs[s].x)+Math.abs(People[p].y-Stairs[s].y));
                    }
                }
                Collections.sort(aT);
                //3명 초과인 경우 처리
                int size = aT.size();
                int K = Stairs[s].K;
                for(int i=3;i<size;i++) {
                    if(aT.get(i)-aT.get(i-3)<K) {
                        aT.set(i, aT.get(i-3)+K);
                    }
                }
                //부분해 time 값 갱신 : 현재 계단 s 아무도 선택안하는 경우도 있기 때문에 aT가 양수인 경우만 처리!
                time = (size>0 && aT.get(size-1)+K+1 > time)? aT.get(size-1)+K+1:time;
                //최종해 ans 최솟값 갱신 :모든 경우에 대해 모든 time들 중 최솟값을 갱신하기 때문에 계단 반복문 밖에 위치해야함!
            }//s-for문
            ans = ans>time?time:ans;
            return;
        }
        //index번째 사람 계단 선택 후 재귀호출
        for(int s=0;s<sCnt;s++) {
            Selected[index] = s;
            solve(index+1);
        }
    }
    static int toStairs(int px,int py,int sx,int sy) {
        return Math.abs(px-sx) + Math.abs(py-sy);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
         
        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
             
            Map = new int[N][N];
            People = new Point[10];
            Selected = new int[10];
            Stairs = new Point[2];
            pCnt = 0; sCnt = 0;
            ans = Integer.MAX_VALUE;
             
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                    if(Map[i][j] == 1) {
                        People[pCnt++] = new Point(i,j);
                    } else if(Map[i][j] >= 2) {
                        Stairs[sCnt++] = new Point(i,j,Map[i][j]);
                    }
                }
            }
            solve(0);
            sb.append("#"+t+" "+ans+"\n");
        }
        System.out.println(sb);
    }
 
}