import java.util.*;
class Menu_1st {
    static PriorityQueue<String> pq = new PriorityQueue<>();
    static String makeStr(int index,int selected,String str,int targetNumber,char[] info){
        //1.정답 찾은 경우
        if(selected == targetNumber){
            return str;
        }
        if(index>=info.length){
            if(selected<targetNumber){
                //더이상 선택할 게 없는데index==info.length selected<targetnumber인 경우 예외 처리!!
                return str;//받은 매개변수 str를 리턴?!//리턴 타입 void이면 return; 정수형이면 int인데.
            }
        }
        //2.다음 경우 호출
        //2-1.선택O
        makeMap(index+1,selected+1,str+info[index],targetNumber,info);
        //2-2.선택X
        makeMap(index+1,selected,str,targetNumber,info);
    }
    static void makeMap(HashMap<String,Integer> map,char[] info){
        
        for(int i=0;i<course.length;i++){
            Map<String,Integer> map = new HashMap<>();
            
            for(int j=0;j<orders.length;j++){
                int m=0;//course[i]개의 문자 조합 중에서 빈도수 가장 큰값을 저장
                String str = makeMap(0,0,"",course[i],orderArr);
                
                if(map.containsKey(str) == false){
                    map.put(str,1);
                } else{
                    map.get(str)++;
                }
                m = Math.max(m,map.get(str));//str에 대한 가장 큰 빈도값이 정답.
                
                //정답 찾은 경우
                if(map.get(str) == m && str.length >=2){
                    pq.add(str);
                }
            }
        }
    }
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};//answer에는 총 몇개의 문자 조합이 들어갈지 모른다.
        for(int i=0;i<course.length;i++){
            Map<String,Integer> map = new HashMap<>();
            int m=0;//course[i]개의 문자 조합 중에서 빈도수 가장 큰값을 저장
            for(int j=0;j<orders.length;j++){
                char[] orderArr = orders[j].toCharArray();
                makeMap(map,orderArr);
            }
        }
        int index=0;
        while(!pq.isEmpty()){
            answer[idex++] = pq.remove();
        }
        return answer;
    }
}