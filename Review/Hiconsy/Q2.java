//애초에 설계부터가 잘못된 듯ㅜ 구현 문제가 아니라 조합 문제...
import java.util.*;
class Solution {
    //5개의 과목 분반 선택
    int[][] selected;
    void selected(int idx){
        //1.종료
        if(idx == 5){
            //현재 selected 정보로 가능한 시간표 갯수 도출해야함.
            return;
        }
        //2.현재 경우 선택 & 다음 경우 재귀호출실행
        for(int i=0;i<4;i++){
            selected[idx] = i;
            go(idx+1);
        }
    }
    public int solution(String[][] schedule) {
        int answer = 0;

        int[] possibleCnt = new int[5];
        Arrays.fill(possibleCnt,4);

        boolean[][] table = new boolean[18][5];

        HashMap<String,Integer> dayMap = new HashMap<>();
        dayMap.put("MO",0); dayMap.put("TU",1); dayMap.put("WE",2); dayMap.put("TH",3); dayMap.put("FR",4); 

        selected = new int[5][4];

        go(0);//selected 조합 정보로 아래 처리.

        for(int i=0;i<5;i++){
            for(int j=0;j<4;j++){
                String cur = schedule[i][j];//i번 과목의 분반
                String[] input = cur.split(" ");
                //format1. Day HH:MM => 3시간 보다 적은 시간으로 주어지는 경우 생각.
                if(input.length == 2){

                    String[] tmp = input[1].split(":");
                    int h = Integer.parseInt(tmp[0]);
                    int m = Integer.parseInt(tmp[1]);
                    int dayIdx = dayMap.get(input[0]);
                    //table[dayMap.get(input[0])]을 마킹
                    int st = (h-9)*2;
                    if(m == 30){st += 1;}
                    for(int x=0;x<6;x++){
                        if(st+x == 18) break;
                        if(table[st+x][dayIdx]) {possibleCnt[i]--; break;}//겹치는 분반 하나라도 나오면 그 과목이 가능한 분반 수는 1 감소
                        else{table[st+x][dayIdx] = true;}
                    }
                } else{
                //format2. Day HH:MM Day HH:MM
                    int stDayIdx = dayMap.get(input[0]);
                    String[] tmp = input[1].split(":");
                    int stH = Integer.parseInt(tmp[0]);
                    int stM = Integer.parseInt(tmp[1]);

                    int endDayIdx = dayMap.get(input[2]);
                    tmp = input[3].split(":");
                    int endH = Integer.parseInt(tmp[0]);
                    int endM = Integer.parseInt(tmp[1]);

                    int start = (stH-9)*2;
                    if(stM == 30) start += 1;
                    for(int x=0;x<3;x++){
                        if(start+x == 18) break;
                        if(table[start+x][stDayIdx]) {possibleCnt[i] -= 1;}
                        table[start+x][stDayIdx] = true;
                    }

                    start = (stH-9)*2;
                    if(endM == 30) start += 1;
                    for(int x=0;x<3;x++){
                        if(start+x == 18) break;
                        if(table[start+x][endDayIdx]) {possibleCnt[i] -= 1;}
                        table[start+x][endDayIdx] = true;
                    }
                }

            }
        }
        int tmpAns = 1;
        for(int i=0;i<5;i++){
            System.out.print(possibleCnt[i]+" ");
            if(possibleCnt[i] != 0){
                tmpAns *= possibleCnt[i];
            }
        }
        if(tmpAns != 1) answer = tmpAns;
        return answer;
    }
}