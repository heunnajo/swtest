//2.등수 구하기
import java.util.*;
class Solution {
    public int[] solution(int[] grade) {        
        //1.학생 번호, 점수 저장
		int[][] rank = new int[grade.length][2];
        for (int i = 0; i < grade.length; i++) {
            rank[i][0] = i;
            rank[i][1] = grade[i];
        }
        //2.점수 기준 내림차순 정렬
        Arrays.sort(rank, (a, b) -> {
            return b[1] - a[1];
        });
        //3.N번 순회하면서 i번 학생 등수 answer[i]를 구함
        //필요한 자료구조 : 순위 r, 동점자수 cnt
        int[] answer = new int[grade.length];
        answer[rank[0][0]] = 1;
        int r = 1;
        int cnt = 0;
        for (int i = 1; i < rank.length; i++) {
            if (rank[i][1] == rank[i - 1][1]) {//점수가 같다면
                cnt++;//동점자수 cnt 1 증가
                answer[rank[i][0]] = r;
            } else {//점수가 다르다면
                r = r + cnt + 1;//갱신되는 r = 현재까지 r + 동점자수 cnt + 1
                answer[rank[i][0]] = r;
                cnt = 0;//동점자수 cnt 0으로 초기화
            }
        }
        return answer;
    }
}