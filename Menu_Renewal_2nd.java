import java.util.*;
class Menu_Renewal_2nd {
    //static String[] Order;
    static String[] answer;
    static Map<String,Integer> menuMap;
    static int maxFreq;
    static PriorityQueue<String> pq = new PriorityQueue<>();
    //빈도수가 가장 높은 문자열들을 알파벳 순서로 배열에 저장해야함.
    //course[i]마다 course[i]개의 가장 빈도수가 높은 문자열이면 저장한다! 
    static void makeMap(int index,int selected,String str,String order,int target){
        //base case : 문자열을 course[i]개 다 만든 것이니까 Map에 저장하고 종료!
        if(index == target){
            //만든 문자열을 알파벳순서로 먼저 정렬하기 위해 문자 배열로 변환.
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String tmp = "";
            for(int i=0;i<tmp.length();i++) tmp += strArr[i];//다시 문자열로 변환
            //Map에 있는지 확인. 있으면 기존 value 조회해서+1, 없으면 <str,Integer> 생성해서 0+1
            menuMap.put(tmp,menuMap.getOrDefault(tmp,0)+1);
            maxFreq = Math.max(menuMap.get(str),maxFreq);//course[i]길이의 최빈값 갖는 문자열을 찾아서 그 빈도수를 저장!
            return;
        }
        //예외 케이스 구현! 계속 선택X하다가 결국 orders길이를 벗어나는 경우
        if(index < target){
            if(selected >= order.length())return;
        }
        //recursive case : 다음 경우 호출(선택기법)
        //1.선택O
        char now = order.charAt(selected);
        makeMap(index+1,selected+1,str+now,order,target);
        //2.선택X
        makeMap(index,selected+1,str,order,target);
            
    }
    static void makeMenu(String[] orders,int[] course){//메인 로직.course[i]개의  문자열 조합 생성해서 Map완성&최빈값 maxFreq 셋팅
        for(int i=0;i<course.length;i++){
            menuMap = new HashMap<>();
            maxFreq = 0;
            for(int j=0;j<orders.length;j++){
                makeMap(0,0,"",orders[j],course[i]);//index,string,만들 문자열재료orders[i],M=course[i]
                //Map 완성한 후, 조건 검사해서 최종 저장소에 정답 저장
                for(String s : menuMap.keySet()){
                    if(menuMap.get(s) == maxFreq && s.length()>=2){
                        pq.add(s);
                    }
                }
            }
        }
        //pq 하나식 뽑아서 answer에 저장해야함.
        int idx=0;//answer배열 초기화 안해줘도 되나...
        String ans[] = new String[pq.size()];
        while(!pq.isEmpty()){
            answer[idx++] = pq.remove();
        }
        return;
    }
    
    public String[] solution(String[] orders, int[] course) {
        makeMenu(orders,course);//핵심 알고리즘 함수
        return answer;//전역변수 리턴.
    }
}