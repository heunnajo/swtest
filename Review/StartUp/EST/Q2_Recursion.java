import java.util.*;
class Solution {
    int ans;
    int compCnt,robotCnt,R;
    ArrayList<Integer>[] list;
    HashSet<Integer> selected;
    boolean[] v;
    public int solution(int[][] needs, int r) {
        ans = -1;
        R = r;
        compCnt = needs.length;
        robotCnt = needs[0].length;
        list = new ArrayList[compCnt];
        for(int idx=0;idx<compCnt;idx++){
            list[idx] = new ArrayList<>();
        }
        v = new boolean[robotCnt];

        for(int idx=0;idx<compCnt;idx++){
            for(int i=0;i<robotCnt;i++){
                if(needs[idx][i] == 1){
                    list[idx].add(i);
                }
            }
        }
        selected = new HashSet<>();
        go(0);

        return ans;
    }
    
    void go(int idx){
        if(idx == robotCnt){
            // for(int s:selected){
            //     System.out.print(s+" ");
            // }
            int cnt = 0;//현재 선택으로 만들 수 있는 완제품 갯수
            for(int i=0;i<compCnt;i++){
                boolean flag = true;
                for(int needed:list[i]){
                    if(!selected.contains(needed)) {
                        flag = false;//하나라도 없는 거 나오면 break;
                        break;
                    }
                }
                if(flag) cnt++;//needed가 다 있을 때만 증가해야함! break;하고 cnt 증가되는 건 아니겠지!?
                //System.out.println("완성가능 완제품번호: "+i);            
            }
            if(ans<cnt) {
                ans = cnt;
            }
            return;
        }
        //현재 선택
        for(int i=0;i<robotCnt;i++){
            //아직 선택 안한 i번 부품을 선택
            if(v[i]) continue;
            v[i] = true;
            selected.add(i);//부품 번호
            go(idx+1);
            selected.remove(selected.size()-1);
            v[i] = false;
        }
    }
}
//각 완제품마다 필요한 부품 번호 확인
        // for(int idx=0;idx<compCnt;idx++){
        //     for(int needed:list[idx])
        //         System.out.print(needed+" ");

        //     System.out.println();
        // }