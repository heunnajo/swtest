//Q68644 두 개 뽑아서 더하기
import java.util.*;
class Solution {
    static int N;
    static int[] Numbers;
    static int[] Selected;
    static boolean[] Checked;
    static ArrayList<Integer> List;
    public int[] solution(int[] numbers) {
        //변수 초기화
        N = numbers.length;
        Numbers = new int[N];
        for(int i=0;i<N;i++) Numbers[i] = numbers[i];
        Selected = new int[2];
        Checked = new boolean[N];
        List = new ArrayList<Integer>();
        
        //List를 완성
        go(0,0);
        
        //List 오름차순 후 정답 배열에 저장
        Collections.sort(List);
        int size = List.size();
        int[] answer = new int[size];
        for(int i=0;i<size;i++){
            answer[i] = List.get(i);
        }
        return answer;
    }
    static void go(int idx,int from){
        //1.종료
        if(idx == 2){
            //DFS를 통해 인덱스 2개 선택한 값 확인
            //System.out.println("선택한 인덱스 2개: "+Selected[0]+","+Selected[1]);
            int sum = Numbers[Selected[0]]+Numbers[Selected[1]];
            boolean res = List.contains(sum);
            if(!res){//없는 경우에만 추가
                List.add(sum);
            }
            return;
        }
        
        //2.현재 경우 선택, 다음 경우 처리
        for(int i=from;i<N;i++){
            if(Checked[i]) continue;
            Checked[i] = true;
            Selected[idx] = i;
            go(idx+1,i+1);
            Checked[i] = false;
        }
    }
}