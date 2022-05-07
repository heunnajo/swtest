//2개의 큐 합 같게 하는 최소 작업 횟수 구하기
import java.util.*;
class Solution {
    static int n,ans;
    static long tot,sum1,sum2;
    static ArrayList<Integer> q1,q2;
    static HashSet<Long> set;
    static class Pos{
        long sum1;
        int cnt;
        Pos(long sum1,int cnt){
            this.sum1 = sum1;
            this.cnt = cnt;
        }
    }
    public int solution(int[] queue1, int[] queue2) {
        ans = -2;
        n = queue1.length;

        q1 = new ArrayList<>();
        q2 = new ArrayList<>();

        set = new HashSet<>();
        tot = 0;
        for(int i=0;i<n;i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            tot += queue1[i];
            tot += queue2[i];
            sum1 += queue1[i]; sum2 += queue2[i];
        }
        if(sum1 == sum2) ans = 0;
        else{bfs();}

        return ans;
    }
    void bfs(){
        Pos st = new Pos(sum1,0);

        Queue<Pos> movingQ = new LinkedList<>();
        movingQ.add(st);
        set.add(sum1);

        while(!movingQ.isEmpty()){
            Pos cur = movingQ.poll();
            //if(cur.sum1 == tot/2){
            if(cur.sum1 == sum2){
                ans = cur.cnt;
                System.out.println("현재 값 기준 ans: "+ans);
                return;
            }
            //q1=>q2
            if(q1.size()==0) return;
            sum1 -= q1.get(0); sum2 += q1.get(0);//sum2도 갱신해줘야함!
            // System.out.println("q1=>q2");
            // System.out.println("이동시키는 수: "+q1.get(0));
            // System.out.println("sum1: "+sum1+","+"sum2: "+sum2);
            q2.add(q1.remove(0));
            if(sum1 == sum2) {
                ans = cur.cnt+1;
                //System.out.println("다음 값 기준 ans: "+ans);
                return;
            }
            if(!set.contains(sum1)){
                set.add(sum1);
                movingQ.add(new Pos(sum1,cur.cnt+1));
            }

            //q2=>q1
            if(q2.size()==0) return;
            sum2 -= q2.get(0); sum1 += q2.get(0);//sum1도 갱신해줘야함!
            // System.out.println("q2=>q1");
            // System.out.println("이동시키는 수: "+q2.get(0));
            // System.out.println("sum1: "+sum1+","+"sum2: "+sum2);
            q1.add(q2.remove(0));
            if(sum1 == sum2) {
                ans = cur.cnt+1;return;
            }
            if(!set.contains(sum1)){
                set.add(sum1);
                movingQ.add(new Pos(sum1,cur.cnt+1));//1이 아니라 2가 되야함!
            }
        }
    }
}

// for(int a:q1) System.out.print(a+" ");
//         System.out.println();
//         for(int a:q2) System.out.print(a+" ");
//         System.out.println();

// System.out.println("sum1: "+sum1);
        // System.out.println("sum2: "+sum2);
        // System.out.println("tot: "+tot);