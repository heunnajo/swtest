import java.util.*;
class Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        //연립이차 방정식으로 a와 b를 구한다!
        //brown = 2*a + (b-2)*2;
        //yellow = (a-2)*(b-2);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<2501;i++){//가로 a
            for(int j=1;j<2501;j++){//세로 b
                //범위 체크!
                if(j-2<=0 || i-2<=0) continue;
                if((2*i + (j-2)*2 == brown) && ((i-2)*(j-2) == yellow)){
                    //정답 (가로,세로) 쌍이 유일하다는 전제하임..
                    if(list.size()>=2) continue;//현재 리스트 크기 2 이상이면 동일한 쌍에 대해 더 넣지 않는다.
                    list.add(i); list.add(j);
                    //System.out.println("i: "+i);
                    //System.out.println("j: "+j);
                }
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        answer[0] = list.get(0); answer[1] = list.get(1);
        return answer;
    }
}