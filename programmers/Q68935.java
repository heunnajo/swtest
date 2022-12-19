//Q68935 3진법 뒤집기
import java.util.*;

class Solution {
    int N,len,ans;
    Stack<Integer> baseThree;
    int[] reversedBaseThree;
    public int solution(int n) {
        N = n;
        
        transeToBaseThree();//N->baseThree 완성
        reverseBaseThree();//baseThree 앞뒤 반전 후 reversedBaseThree에 저장
        calculteDecimal();//reversedBaseThree 로 10진법 계산 ans에 저장
        
        return ans;
    }
    void transeToBaseThree(){
        baseThree = new Stack<>();
        
        while(N > 0){
            baseThree.push(N % 3);
            N /= 3;
        }
        
        // while(!baseThree.isEmpty()){
        //     System.out.print(baseThree.pop());
        // }
        // System.out.println();
    }
    void reverseBaseThree(){
        len = baseThree.size();
        reversedBaseThree = new int[len];
        
        for(int i=len-1;i>=0;i--){
            reversedBaseThree[i] = baseThree.pop();
        }
        
        // for(int i=0;i<len;i++){
        //     System.out.print(reversedBaseThree[i]);
        // }
        // System.out.println();
    }
    void calculteDecimal(){
        ans = 0;
        
        for(int i=0;i<len;i++){
            ans += (reversedBaseThree[i]*Math.pow(3,len-1-i));
        }
        //System.out.println("ans: "+ans);
    }
}