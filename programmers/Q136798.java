//#136798 기사단원의 무기
class Solution {
    public int solution(int n, int limit, int power) {
        int sum = 0;
        int[] count = new int[n+1];
        
        for(int i=1;i<=n;i++){
            count[i] = getCount(i);//i의 약수 갯수 리턴
            if(count[i] > limit){
                count[i] = power;
            } 
            sum += count[i];
        }

        // for(int i=1;i<=n;i++){
        //     System.out.print(count[i]+" ");
        // }
        return sum;
    }
    int getCount(int n){
        int count = 0;
        
        for(int i=1;i * i <= n;i++){
            if(n % i  == 0) {
                count++;
                if(i * i < n) count++;//제곱근이 아닌 경우, 한번 더 더함!
            }
        }
        return count;
    }
}