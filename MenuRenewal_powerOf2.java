import java.util.*;
class MenuRenewal_powerOf2 {
    static HashMap<String,Integer> map;
    static int m;
    //N!
    static void makeMap(int index,int selected,int targetN,String str,String word){
        //1.정답 찾은 경우(재귀 종료 조건)
        if(selected == targetN){
            char[] c = str.toCharArray();
            Arrays.sort(c);//문자 오름차순 정렬~!
            String tmp = "";//다시 문자열로 변환
            for(int i=0;i<c.length;i++) tmp+=c[i];
            map.put(tmp,map.getOrDefault(tmp,0)+1);
            m = Math.max(m,map.get(tmp));//현재의 m과 tmp의 value 중 최댓값으로 갱신!
            return;
        }
        //2.불가능한 경우
        if(selected<targetN){
            if(index>=word.length()){
                //더이상 선택할 게 없는데index==info.length selected<targetnumber인 경우 예외 처리!!
                return;//받은 매개변수 str를 리턴?!//리턴 타입 void이면 return; 정수형이면 int인데.
            }
        }
        //3.다음 경우 호출
        char now = word.charAt(index);
        //3-1.선택O
        makeMap(index+1,selected+1,targetN,str+now,word);
        //3-2.선택X
        makeMap(index+1,selected,targetN,str,word);
    }
    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();//정답배열에 사전오름차순으로 저장하기 위해
        for(int i=0;i<course.length;i++){
            map = new HashMap<>();
            m=0;//course[i]개의 문자 조합 중에서 빈도수 가장 큰값을 저장
            for(int j=0;j<orders.length;j++){//Map,m을 완성!
                makeMap(0,0,course[i],"",orders[j]);
            }
            //Map,m 완성 후, 최댓값 m을 value로 갖는 str을 pq에 저장한다!
            for(String s:map.keySet()){//Map 순회해서 저장된 key(string)은 value(빈도수_가 1/3/5.. 중에서 최댓값 m 갖는 key를 pq에 넣어준다! 
                if(map.get(s) == m && map.get(s)>1){
                    pq.add(s);
                }
            }
        }
        //answer에는 총 몇개의 문자 조합이 들어갈지 모른다.그렇기 때문에 pq를 완성한후 생성&초기화한다!
        String[] answer = new String[pq.size()];
        int index=0;
        while(!pq.isEmpty()){
            answer[index++] = pq.remove();
        }
        return answer;
    }
}