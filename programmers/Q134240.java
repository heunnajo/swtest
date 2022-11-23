//푸드 파이트 대회 : O(2n)
//#134240
class Solution {
    public String solution(int[] food) {
        int n = food.length-1;
        int[] possible = new int[n+1];
        
        for(int i=1;i<=n;i++){
            if(food[i] >= 2){
                possible[i] = food[i]/2;
            }
        }
        //possible 확인
        
        StringBuilder sb = new StringBuilder();
        //A 먼저
        for(int i=1;i<=n;i++){
            if(possible[i] > 0){
                for(int j=0;j<possible[i];j++){
                    sb.append(i);
                }
            }
        }
        sb.append(0);
        for(int i=n;i>=1;i--){
            if(possible[i] > 0){
                for(int j=0;j<possible[i];j++){
                    sb.append(i);
                }
            }
        }
        
        
        return sb.toString();
    }
}