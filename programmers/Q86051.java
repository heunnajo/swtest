//#86051 없는 숫자 더하기

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        int len = numbers.length;
        boolean[] exist = new boolean[10];
        
        for(int i=0;i<len;i++){
            exist[numbers[i]] = true;
        }
        
        for(int i=0;i<10;i++){
            if(!exist[i]){
                answer += i;
            }
        }
        
        return answer;
    }
}