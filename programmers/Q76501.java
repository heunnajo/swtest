//#76501 음양 더하기

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int n = absolutes.length;
        
        for(int i=0;i<n;i++){
            if(signs[i]){answer += absolutes[i];}
            else{answer = answer + absolutes[i]*(-1);}
        }
        
        return answer;
    }
}