import java.util.*;
class Solution {
    int ans;
    int compCnt,robotCnt,R;

    int cntRobot(int num) {
        int sum = 0;
        while(num>0) {
            if((num&1) != 0) sum++;
            num = num>>1;
        }
        return sum;
    }

    public int solution(int[][] needs, int r) {
        ans = -1;
        R = r;
        compCnt = needs.length;
        robotCnt = needs[0].length;
        //LinkedList<Integer> list;
        for(int subset=0;subset<(1<<robotCnt);subset++){
            //list = new LinkedList<Integer>();
            if(cntRobot(subset) == R){//R개 선택
                int cnt = 0; boolean flag = true;
                for(int idx = 0;idx<compCnt;idx++){
                    int[] cur = needs[idx];
                    for(int k=0;k<robotCnt;k++){
                        if(((subset & (1<<k)) == 0) && cur[k] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) cnt++;
                    //list.add(idx);
                }
                if(ans<cnt) {
                    ans = cnt;
                    //System.out.println("완성 가능한 완제품 번호");
                    // for(int x:list){
                    //     System.out.print(x+" ");
                    // }
                }
            }
        }


        return ans;
    }
}
//각 완제품마다 필요한 부품 번호 확인
        // for(int idx=0;idx<compCnt;idx++){
        //     for(int needed:list[idx])
        //         System.out.print(needed+" ");

        //     System.out.println();
        // }
