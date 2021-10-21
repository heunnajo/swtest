import java.util.*;
class LotteryHighestLowestRanking {
    public int[] solution(int[] lottos, int[] win_nums) {
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        //1.현재  공개된 번호 갯수 중 Hit 갯수 구하기
        //2.0의 갯수 구하기
        //최고 순위 = 1+2, 최저 순위 = 1
        HashMap<Integer,Boolean> mymap = new HashMap<>();
        for(int e:win_nums){
            mymap.put(e,true);
        }
        
        //1.현재  공개된 번호 갯수 중 Hit 갯수 구하기
        int hit = 0;
        for(int i=0;i<lottos.length;i++){
            if(lottos[i]==0) continue;
            if(mymap.containsKey(lottos[i])) hit++;
        }
        //2.0갯수 구하기
        int zero_cnt = 0;
        for(int z:lottos){
            if(z==0) zero_cnt++;
        }
        
        //switch-case문으로 최고, 최저 순위 구하기
        //최고
        int maxR = 0,minR = 0;
        switch(hit+zero_cnt){
            case 6:maxR = 1;
                break;
            case 5:maxR = 2;
                break;
            case 4:maxR = 3;
                break;
            case 3:maxR = 4;
                break;
            case 2:maxR = 5;
                break;
            default:maxR = 6;
                break;
        }        
        //최저
        switch(hit){
            default:minR = 6;
                break;
            case 2:minR = 5;
                break;
            case 3:minR = 4;
                break;
            case 4:minR = 3;
                break;
            case 5:minR = 2;
                break;
            case 6:minR = 1;
                break;
        }
        int[] answer = new int[2];
        answer[0] = maxR; answer[1] = minR;
        return answer;
    }
}