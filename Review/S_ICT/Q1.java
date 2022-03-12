//1.DP
import java.util.*;
class Solution {
    public int solution(int money, int[] costs) {
        int INF = 987654321;
        //전처리 : 동전의 생산단가
        int[] price = new int[200000];
        Arrays.fill(price,INF);
        price[1] = costs[0];price[5] = costs[1];price[10] = costs[2];
        price[50] = costs[3];price[100] = costs[4];price[500] = costs[5];
        //d배열 초기화
        int[] d = new int[money+1];
        Arrays.fill(price,INF);
        d[1] = costs[0]; d[2] = costs[0]*2;d[3] = costs[0]*3;d[4] = costs[0]*4;
        for(int i=5;i<=money;i++){
            d[i] = costs[0]*i;//초기화 값이 적절한가?과연.
        }
        //dp
        for(int i=5;i<=money;i++){
            for(int j=0;j<money;j++){
                if(i-j<0) continue;
                d[i] = Math.min(d[i],d[i-j]+price[j]);//0으로 되면 안 되는뎅
            }
        }
        return d[money];
    }
}