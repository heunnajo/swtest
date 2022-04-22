//K,M 주어질 때 1~K 자연수 1번식 써서 만들 수 있는 모든 자연수들 중 m의 배수 갯수 리턴
//K : 1~9, M : 1~1,000,000,000 
import java.util.*;
class Solution {
    boolean[] check;
    ArrayList<Integer> list;//k는 최대9, 이 때 경우의수는 9!=362880 < 1억
    int K,M,cnt;
    public int solution(int k, int m) {

        check = new boolean[k+1];
        list = new ArrayList<Integer>();
        K = k; M = m;cnt = 0;

        go(0,"");//1부터 k까지 모든 순열을 생성, list를 완성
        count();//cnt를 완성

        return cnt;
    }
    //k자리의수를 선택.
    //k자리의 수를 다 선택하고나면 정수로 형변환해서 리스트에 저장한다!
    void go(int idx,String selected){//idx == k가 되면 k 자리 숫자 모두 고른 경우
        //1.재귀 종료 조건
        if(idx == K){
            int num = Integer.parseInt(selected);
            System.out.println("num: "+num);
            list.add(num);

            return;
        }
        //2.현재 선택, 다음 선택(재귀 호출)
        for(int i=1;i<=K;i++){
            if(check[i]) continue;
            check[i] = true; 
            go(idx+1,selected+i);
            check[i] = false; 
        }
    }
    void count(){
        for(int a:list){
            if(a%M == 0) {
                cnt++;
            }
        }
    }
}