//DP + 구현
/*
입출력 예
masks	dates	result
[[3200, 4], [2300, 2], [1100, 1], [4200, 6]]	["2022/05/02", "2022/05/01", "2022/05/07", "2022/05/05", "2022/05/08", "2022/05/13~2022/05/15", "2022/05/14~2022/05/17", "2022/05/01~2022/05/02", "2022/05/16"]	9600
[[600, 2], [500, 1], [1015, 400]]	["2023/01/01~2023/01/02", "2021/12/31"]	1015
[[3651, 365], [10, 1]]	["2025/01/01~2025/12/31"]	3650
*/
import java.util.*;
class Solution {
    int[] dateInfo = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    int INF = Integer.MAX_VALUE;
    public int solution(int[][] masks, String[] dates) {

        int n = masks.length;
        int[] dp = new int[6001];//dp[i] : i일 사용할 때 마스크 최소 비용.

        Arrays.fill(dp,INF);

        //dp:bottom-up으로 점화식을 완성
        dp[0] = 0;
        for(int i=0;i<n;i++){
            int cost = masks[i][0];
            int durability = masks[i][1];//내구도
            for(int j=durability;j<=6000;j++){//내구도값 최대 2000이므로 3650+2000 = 5650이지만 600으로잡음.
                //dp 점화식 수행해야함.
                int prev = dp[j-durability];//초기 prev = 0
                if(prev!= INF) dp[j] = Math.min(dp[j],cost+prev);//이미 만들어진 적이 있더라도 대소비교 통해 최솟값 도출해야하는 거 아닌가?
            }
        }

        //날짜에 대한 처리
        int len = dates.length;
        int curDate = 0; 
        for(int i=0;i<len;i++){
            if(dates[i].length()>10){//기간인 경우!일수를 계산!
                curDate += calculateDate(dates[i]);
            } else{
                curDate++;
            }
        }

        //최솟값 갖는 정답 도출!
        int answer = INF;
        for(int i=curDate;i<=6000;i++){
            answer = Math.min(answer,dp[i]);
        }

        return answer;
    }
    int calculateDate(String period){
        //시작일, 종료일을 구해서 각 달마다 일수를 더함.
        //연도에 대해서도 반복해야함.
        int total = 0;
        //1.문자열 파싱
        String[] tmp = period.split("~");
        String st = tmp[0];
        int stY = Integer.parseInt(tmp[0].substring(0,4));
        int stM = Integer.parseInt(tmp[0].substring(5,7));
        int stD = Integer.parseInt(tmp[0].substring(8,10));

        String end = tmp[1];
        int endY = Integer.parseInt(tmp[1].substring(0,4));
        int endM = Integer.parseInt(tmp[1].substring(5,7));
        int endD = Integer.parseInt(tmp[1].substring(8,10));

        int m = stM;
        for(int y=stY;y<=endY;y++){
            for(;m<=12;m++){
                total+=dateInfo[m];
            }
            m = 1;
        }
        //System.out.println("total: "+total);
        return total;
    }
}
//점화식 제대로 만들어지나?
        // for(int i=0;i<=6000;i++){
        //     if(dp[i] != INF) System.out.print(dp[i]+" ");
        //     else System.out.print(-1+" ");
        // }
        // System.out.println();
