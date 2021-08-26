import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();//???
    static Map<String,List<Integer>> map = new HashMap<>();//전역 변수 선언과 동시에 초기화
    static int[] answer;
    
    static void dfs(int index,String str,String[] info){//3번째 매개변수는 info의 String 배열 원소!
        //1.정답 찾은 경우
        if(index == 4){
            //1-1.Map에 없는 경우
            if(map.containsKey(str) == false){
                List<Integer> list = new LinkedList<>();
                list.add(Integer.parseInt(info[4]));//info배열의 마지막 원소인 점수를 넣는다! =>오름차순 정렬 안해도되나?나중에 동일한 점수에 대해 최솟값 인덱스 찾아서 x이상인 요소 갯수 구해야하는데...
                map.put(str,list);
            }
            else{//1-2.Map에 있는 경우
                map.get(str).add(Integer.parseInt(info[4]));
            }
            return;
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
        //굳이 여기에서 문의 조건(String), 점수(int) 분리하지 않아도 dfs에서 숫자를 제외한 4개 문자열 조합 생성한다!
        //따라서 makeMap에서는 info의 i번째 문자열들을 공백만 제거해서 매개변수로 전달, dfs 호출해주면 된다!
        for(int i=0;i<info.length;i++){
            //i번재 원소마다 공백 제거
            // String[] tmp = info[i].split(" ");//java,backend,junior,pizza,150
            // for(int j=0;j<input.length()-1;j++){//input에서 문의조건만 추출!
            //     String input = "";
            //     input += tmp[j];//input = input + tmp[j];
            // }
            dfs(0,"",info[i].split(" "));//3번째 매개변수는 info의 String 배열 원소!
        }
        //dfs로 info의 모든 가능한 문자열 조합 만들어서 Map 생성했으면 value인 리스트 오름차순 정렬해야한다!
        //리스트를 순회하면서 변경할 순 없기 때문에 iterator를 사용한다!
        Iterator<String> it = map.keySet().iterator();//map의 value
        while(it.hasNext()){
            String key = it.next();
            List<Integer> li = map.get(key);
            Collections.sort(li);
        }
    }
    static int counting(String str,int score){
        if(map.containsKey(str) == false){return 0;}//해당 문의 조건에 대한 답은 0.
        
        //else if(map.contains(str)){
            List<Integer> list = map.get(str);//리스트를 이분 탐색으로 score값의 최소 인덱스를 찾아야한다.
            int s = 0, e = list.size()-1;//인덱스
            
            int mid = 0;
            while(s<=e){
                mid = (s+e)/2;
                if(list.get(mid)<score){
                    s = mid+1;
                } else if(list.get(mid)>score){
                    e = mid-1;
                } else if(list.get(mid) == score) {
                    break;
                }
            }
            return list.size()-mid;
        //}
    }
    static void makeAnswer(String[] query){
        //query에서 유의미한 문의조건, 점수 딱 2가지로 추출해야한다!
        for(int i=0;i<query.length;i++){
            String[] tmp = query[i].split(" ");//java,and,backend,and,junior,and,...
            String input = "";
            
            for(int j=0;j<tmp.length-1;j++){//마지막 숫자 제외!
                if(tmp[j].equals("and")) continue;
                input += tmp[j];//javabackendjuniorpizza
            }
            answer[i] = counting(input,Integer.parseInt(tmp[tmp.length-1]));
        }
        // for(int i=0;i<answer.length();i++){
        //     answer[i] = counting(,,,);
        // }
    }
    public int[] solution(String[] info, String[] query) {
        //1.info의 원소로 모든 가능한 문자열 순열을 만든다. 
        //2.각각의 경우의 수에 부합하는 사람의 점수를 List에 저장한다!
        answer = new int[query.length];
        makeMap(info);
        makeAnswer(query);//answer 배열을 완성
        return answer;
    }
}