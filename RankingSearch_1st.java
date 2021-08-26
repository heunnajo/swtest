import java.util.*;
class RankingSearch_1st {
    static Map<String,List<Integer>> map = new HashMap<>();//전역 변수 선언과 동시에 초기화
    static int[] answer;
    static void dfs(int index,String str,String[] info){//3번째 매개변수는 info의 String 배열 원소!
        //1.정답 찾은 경우
        if(index == 4){
            //1-1.Map에 없는 경우
            if(map.contains(str) == false){
                List<Integer> list = new LinkedList<Integer>();
                list.add(info[4]);//info배열의 마지막 원소인 점수를 넣는다! =>오름차순 정렬 안해도되나?나중에 동일한 점수에 대해 최솟값 인덱스 찾아서 x이상인 요소 갯수 구해야하는데...
                map.put(str,list);
            }
            else{//1-2.Map에 있는 경우
                map.get(key).add(info[4]);
            }
        }
        //2.다음 경우 호출
        //2-1.선택O : info배열에서 index번째 문자열을 선택한다!
        dfs(index+1,str+info[index],info);
        //2-2.선택X : "-"를 선택
        dfs(index+1,str+"-",info);
    }
    static void makeMap(String[] info){
        //info에서 공백문자 제거하고, 4개의 지원정보와 점수를 분리해서 저장해야한다!
        //4개의 지원 정보 => 문의 조건, Map의 key(String)
        //점수 : query에서 점수 x에 해당, Map의 value(List<Integer>)
        for(int i=0;i<info.length;i++){
            //i번재 원소마다 공백 제거
            String[] tmp = info[i].split(" ");//java,backend,junior,pizza,150
            for(int j=0;j<input.length()-1;j++){//input에서 문의조건만 추출!
                String input = "";
                input += tmp[j];//input = input + tmp[j];
            }
            dfs(0,"",Integer.parseInt(tmp[tmp.length()-1]));
        }
    }
    static void counting(String str,int score){
        
    }
    static void makeAnswer(String[] query){
        //query에서 유의미한 문의조건, 점수 딱 2가지로 추출해야한다!
        for(int i=0;i<query.length();i++){
            String[] tmp = query[i].split(" ");//java,and,backend,and,junior,and,...
            String input = "";
            for(int j=0;j<tmp.length()-1;j++){//마지막 숫자 제외!
                if(tmp[j].equals("and")) continue;
                input += tmp[j];//javabackendjuniorpizza
            }
            counting(input,Integer.parseInt(tmp[tmp.length()-1]);
        }
    }
    public int[] solution(String[] info, String[] query) {
        //1.info의 원소로 모든 가능한 문자열 순열을 만든다. 
        //2.각각의 경우의 수에 부합하는 사람의 점수를 List에 저장한다!
        answer = {};
        makeMap(info);
        makeAnswer(query);
        return answer;
    }
}