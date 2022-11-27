import java.util.Arrays;
class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int [n];
        Arrays.sort(numbers);
        // for(int x: numbers) System.out.print(x+" ");
        // System.out.println();

        int idx = 0;
        for(int i=0;i<n/2;i++){
            answer[idx] = numbers[n-1-i];
            if(idx+1<n) answer[idx+1] = numbers[i];
            idx += 2;
        }
        if(n % 2 != 0) answer[n-1] = numbers[n/2];

        return answer;
    }
}