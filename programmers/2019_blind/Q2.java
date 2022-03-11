//2. 실패율
import java.util.*;
class Solution {
    class Stage implements Comparable<Stage>{
        int id; double failure;
        Stage(int id,double failure){
            this.id = id; this.failure = failure;
        }
        public int compareTo(Stage s){
            //return s.failure - this.failure;
            if(this.failure-s.failure>0) return -1;//자리 바꾸지 않는다.
            if(this.failure-s.failure<0) return 1;//자리 바꾼다.
            else return 0;
        }
    }
    public int[] solution(int N, int[] stages) {
        //1.자료구조
        int total = stages.length;
        int[] nums = new int[N+2];//1~N까지의 스테이지마다 플레이어 수를 저장!
        for(int i=0;i<total;i++){
            nums[stages[i]] += 1;
        }
        
        //2.알고리즘
        List<Stage> list = new LinkedList<>();
        for(int id=1;id<=N;id++){
            double failure = (double)nums[id]/total;
            total -= nums[id];
            list.add(new Stage(id,failure));
        }
        // Collections.sort(list,Collections.reverseOrder());
        Collections.sort(list);
        int[] answer = new int[N];
        for(int i=0;i<N;i++) answer[i] = list.get(i).id;
        return answer;
    }
}