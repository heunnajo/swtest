//Q4.공평한 용돈 나누기
import java.util.*;
class Solution {
    int n,total,ans,totalCnt;
    int[][] Unit_Cnt;
    public int solution(int[][] money) {
        ans = 987654321;//최솟값 도출 위해 큰 값 저장!
        total = 0;totalCnt = 0;
        n = money.length;
        Unit_Cnt = new int[n][2];

        for(int i=0;i<n;i++){
            Unit_Cnt[i][0] = money[i][0];//화폐 단위
            Unit_Cnt[i][1] = money[i][1];//갯수
            total += Unit_Cnt[i][0]*Unit_Cnt[i][1];
            totalCnt += Unit_Cnt[i][1];
        }
        //System.out.println("total: "+total);
        //모든 가능한 금액 price의 경우의 수를 만들어 보고, 각각의 경우마다 그 금액 price과 total-price 절댓값 차이 최소값 도출해주면 됨!
        //이 또한 완탐. i번째 화폐 선택 : 갯수가 0보다 크면 선택 가능, 선택하고 나면 1 감소, 원복 1 증가
        //재귀함수 필요 정보는? 언제까지 반복해야함?
        //1.지금까지 선택한 금액 합.
        //2.반복 조건 : n개 종류의 동전이 있다면 n번만 선택하면 되나? 4개의 동전 종류가 있을 때 100원짜리 3개선택이 가장 공평이라면 4번 선택X
        go(0,0);//얼마나 반복해야하나?

        //반복문 구현
        // int sum = 0;
        // for(int i=0;i<n;i++){
        //     int unit = Unit_Cnt[i][0];int cnt = Unit_Cnt[i][1];

        // }
        return ans;
    }
    ArrayList<Integer> list = new ArrayList<>();//몇번재 동전 선택하는지 검사
    //전체 동전 갯수가 totalCnt개.
    //더 이상 선택할 수 있는 동전이 없으면 선택을 멈춤.
    //한쪽만 금액을 만들면되기 때문에 totalCnt/2개라고 생각을 했음. |total-sum|
    void go(int idx,int sum){
        //1.재귀 종료 조건:2500*2,700*1 이렇게 골라야함.
        if(idx == totalCnt/2){//올바른 종료 조건이 아님. 더 선택하지 못하게 됨.

            int ab = Math.abs((total-sum) - sum);
            if(ans>ab){
                System.out.println("sum: "+sum);//과연 sum이 잘 만들어지나????
                ans = ab;
            }
            return;
        }
        if(idx < n){
            if(sum>=ans) return;
        }
        //2.현재 선택, 다음 선택(재귀 호출)
        for(int i=0;i<n;i++){
            int unit = Unit_Cnt[i][0];int cnt = Unit_Cnt[i][1];
            if(cnt == 0) continue;
            Unit_Cnt[i][1]--;
            go(idx+1,sum+unit*cnt);
            Unit_Cnt[i][1]++;
        }
    }
}

// for(int i=0;i<n;i++){
//             System.out.println("화폐 단위: "+Unit_Cnt[i][0]);
//             System.out.println("화폐 갯수: "+Unit_Cnt[i][1]);
//         }


//System.out.println("선택한 동전의 인덱스 : ");
            //for(int a:list)System.out.print(a+" ");
            //System.out.println();
