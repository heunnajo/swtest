//2.등수 구하기
import java.util.*;
class Solution {
    public int[] solution(int[] grade) {
        LinkedList<Integer> scores = new LinkedList<>();
        int len = grade.length;
        for(int i=0;i<len;i++){
            scores.add(grade[i]);
        }
        Collections.sort(scores);
        //1.점수가 자신보다 큰 학생 수 x를 구한다 : 이분 탐색
        //2.x+1이 본인의 등수가 된다.
        int[] answer = new int[len];
        for(int i=0;i<len;i++){
            answer[i] = binarySearch(scores,grade[i]);
        }
        // for(int i:scores) System.out.print(i+" ");
        // System.out.println();
        return answer;
    }
    int binarySearch(LinkedList<Integer> list, int X){
        //1.점수가 자신보다 큰 학생 수 x를 구한다 : 이분 탐색
        //2.x+1 = 본인의 등수 를 리턴한다.
        int size = list.size();
        int s = 0,e = size-1, mid = 0;
        while(s<=e){
            mid = (s+e)/2;
            if(list.get(mid)<=X) s = mid+1;
            else if(list.get(mid) > X) e = mid-1;
        }
        return size-e;
    }
}