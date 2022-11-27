import java.util.*;
class Solution {
    String dest;
    int ans;
    static class Pos{
        int[] state;
        int cnt;

        Pos(int[] state,int cnt){
            this.state = state;
            this.cnt = cnt;
        }
    }
    public int solution(int[] s1, int[] s2) {
        dest = "";

        dest = intToString(s2);

        bfs(s1);

        return ans;
    }
    void bfs(int[] st){
        Queue<Pos> q = new LinkedList<>();
        HashSet<String> v = new HashSet<>();
        String start = intToString(st);

        v.add(start);
        q.offer(new Pos(st,0));

        String next = "";
        while(!q.isEmpty()){
            Pos cur = q.poll();

            if(intToString(cur.state).equals(dest)){
                ans = cur.cnt;
                return;
            }

            int curIdx = getZero(cur.state);
            System.out.println("curIdx: "+curIdx);
            if(curIdx < 6){ //3가지 방향으로 이동
                int one = (curIdx+5)%6;
                //System.out.println("one: "+one);
                int two = (curIdx+1)%6;
                //System.out.println("two: "+two);
                int three = 6;

                //1.int[] swap
                int tmp = cur.state[curIdx];
                cur.state[curIdx] = cur.state[one];
                cur.state[one] = tmp;

                next = intToString(cur.state);
                if(!v.contains(next)){
                    q.offer(new Pos(cur.state,cur.cnt+1));
                    v.add(next);
                }
            } else{//6가지 방향으로 이동
                for(int i=0;i<6;i++){
                    //curIdx <=> i
                    int tmp = cur.state[curIdx];
                    cur.state[curIdx] = cur.state[i];
                    cur.state[i] = tmp;

                    next = intToString(cur.state);
                    if(!v.contains(next)){
                        q.offer(new Pos(cur.state,cur.cnt+1));
                        v.add(next);
                    }
                }
            }
            //이동하고 난 후 cur 상태
            //for(int x:cur.state) System.out.print(x+" ");
        }
    }
    int getZero(int[] arr){
        int idx = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] == 0) idx = i;
        }
        return idx;
    }
    boolean isOut(int x){
        return x < 0 || x>7;
    }
    String intToString(int[] num){
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<7;i++) tmp.append(num[i]);
        return tmp.toString();
    }
}
