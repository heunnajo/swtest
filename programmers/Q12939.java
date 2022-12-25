//Q12939 최댓값과 최솟값

class Solution {
    public String solution(String s) {
        
        String[] input = s.split(" ");
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<input.length;i++){
            int cur = Integer.parseInt(input[i]);
            if(cur < min){min = cur;}
            if(cur > max){max = cur;}
        }
        
        String answer = min+" "+max;
        return answer;
    }
}