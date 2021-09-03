import java.util.*;
class RankingSearch_2nd {
    static Map<String,List<Integer>> map = new HashMap<>();
    static void go(int index,String str,String[] infoArr){
        //정답 찾은 경우.재귀 종료&리턴 시작!
        if(index == 4){//총 4개의 문자열로 순열 다 만든 경우!
            if(map.containsKey(str)){
                map.get(str).add(Integer.parseInt(infoArr[4]));
            } else{
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(infoArr[4]));
                map.put(str,list);
            }
            return;
        }
        //다음 경우 호출
        //1.선택O
        go(index+1,str+infoArr[index],infoArr);
        //2.선택X
        go(index+1,str+"-",infoArr);
    }
    //static void mainLogic(String[] info, String[] query){
    static void mainLogic(String[] info){
        //for(int i=0;i<query.length;i++){
            //map = new HashMap<>();
            for(int j=0;j<info.length;j++){
                //재귀함수 실행:문자열 순열 생성
                //1.문자열 파싱
                String[] infoArr = info[j].split(" ");
                go(0,"",infoArr);
                
            }
            sorting();//sorting(map);//전역변수라서 굳이 매개변수로 전달하지 않아도 됨!
        //}
    }
    //static void sorting(HashMap<String,List<Integer> map){
    static void sorting(){
        //map의 모든 key에 대한 value를 정렬한다!
        Iterator<String> it = map.keySet().iterator();//keySet을 반복한다.String의 집합.
        while(it.hasNext()){
            String s = it.next();
            List<Integer> list = map.get(s);
            Collections.sort(list);
        }
    }
    static int getAnswer(String cmd,int score){
        //만약 해당하는 조건이 Map에 없다면(조건에 부합하는 사람이 없음)
        if(!map.containsKey(cmd)) return 0;
        
        //리스트 이분 탐색
        List<Integer> list = map.get(cmd);
        int size = list.size();
        int s = 0, e = size-1;
        // int mid = (s+e)/2;

        while(s<=e){
            
            //if(map.get(mid) <= score){
            int mid = (s+e)/2;//반복문 돌때마다 s또는 e가 갱신되기 때문에 이에 따라 mid도 갱신되야함!
            if(list.get(mid) < score){
                s = mid+1;
            } else{//mid>score
                e = mid-1;
            }
        }
        return size-s;
    }
    public int[] solution(String[] info, String[] query) {
        //int[] answer = {};
        int[] answer = new int[query.length];
        mainLogic(info);//map을 완성.
        for(int i=0;i<query.length;i++){
            //mainLogic(info);//map을 완성.
            String[] arr = query[i].split(" ");
            
            String str = ""; int score = Integer.parseInt(arr[arr.length-1]);//유의미한 데이터만 추출!
            for(int j=0;j<arr.length-1;j++){
                if(arr[j].equals("and"))continue;
                else{str += arr[j];}//굳이 문자열 배열을 하나의 문자열로 합쳐야하나? 아닌 것 같은데..하나의 문자열보단 하나의 문자열 배열이 더 적합할 것 같은..ㄴㄴ아님. javabackendjuniorpizza 자체가 key가 되기 때문!!!!
            }
            answer[i] = getAnswer(str,score);
        }
        //정답 구하는 메서드=>메서드 어딘가 내부에 존재할 것 같음. 메인 메서드는 아니고. 장황해지기 때문에.
        
        return answer;
    }
}