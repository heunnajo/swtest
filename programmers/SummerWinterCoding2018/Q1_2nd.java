//1.소수 만들기
import java.util.*;
import java.util.*;
class Q1_2nd {
    int[] Nums;
    int N,ans;
    boolean[] v;
    public int solution(int[] nums) {
        //1.변수 초기화
        ans = 0;
        N = nums.length;
        Nums = new int[N];
        v = new boolean[N];
        
        for(int i=0;i<N;i++){
            Nums[i] = nums[i];
        }
        
        //2.ans를 만드는 재귀 실행
        go(0,0,0);
        return ans;
    }
    void go(int from, int idx,int sum){
        //1.종료
        if(idx == 3){
            if(isPrime(sum)) {
                ans++;
            }
            return;
        }
        //2.현재 선택, 다음 재귀 호출
        for(int i=from;i<N;i++){
            if(v[i]) continue;
            v[i] = true;
            go(i+1,idx+1,sum+Nums[i]);
            v[i] = false;
        }
    }
    boolean isPrime(int x){
        int root = (int)Math.sqrt(x);
        for(int i=2;i<=root;i++){
            if(x%i == 0) return false;
        }
        return true;
    }
}