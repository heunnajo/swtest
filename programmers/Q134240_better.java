//푸드 파이트 대회 : O(2n)
//#134240
class Solution {
    public String solution(int[] food) {
        int n = food.length-1;
        StringBuilder first = new StringBuilder();
        
        int x;
        for(int i=1;i<=n;i++){
            if(food[i] >= 2){
                x = food[i]/2;
                for(int j=0;j<x;j++){
                    first.append(i);
                }
            }
        }
        first.append(0);
        int firstLen = first.length();
        
        for(int i=firstLen-2;i>=0;i--){
            first.append(first.charAt(i));
        }
        
        return first.toString();
    }
}