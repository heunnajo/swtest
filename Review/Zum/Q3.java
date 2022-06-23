//BOJ 6549 히스토그램에서 가장 큰 직사각형st
import java.util.*;
class Solution {
    public int solution(int[] histogram) {
        int answer = 0;
        Stack<Integer> st = new Stack<>();
        int n = histogram.length;

        for(int i=0;i<n;i++){//히스토그램 막대 마다 비교하면서 스택에 넣음
            while(!st.isEmpty() && histogram[st.peek()] > histogram[i]){//top>x인 경우 이 때 직사각형 넓이 구하고, pop
                int height = histogram[st.pop()];
                int width = i;

                if(!st.isEmpty()) {
                    width = i-st.peek()-1;//top 이전 값(peek)과 비교
                }

                if(answer<width*height){
                    answer = width*height;
                }
            }
            st.push(i);
        }

        while(!st.isEmpty()){
            int height = histogram[st.pop()];
            int width = n;

            if(!st.isEmpty()){
                width = n-st.peek()-1;
            }

            if(answer < width*height){
                answer = width*height;
            }
        }
        return answer;
    }
}