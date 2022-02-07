//3.순위 검색
import java.util.*;
class SearchRanking {
    String[][] dataSet = {{"-", "cpp", "java", "python"},
                  {"-", "backend", "frontend"},
                  {"-", "junior", "senior"},
                  {"-", "chicken", "pizza"}};
    HashMap<String,ArrayList<Integer>> map;
    public static void main(String[] args) {
        new SearchRanking().test();
    }
    private void test() {
    	String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        int[] answer = solution(info, query);
        for(int i : answer)
        	System.out.println(i);
    }
    void makeMap(int idx,String str){
        //1.종료 조건
        if(idx == 4){
            //System.out.println("str: "+str);
            ArrayList<Integer> list = new ArrayList<Integer>();
            map.put(str,list);
            return;
        }
        //2.현재 선택, 다음 경우 호출
        String[] set = dataSet[idx]; int len = set.length;
        for(int i=0;i<len;i++){
            makeMap(idx+1,str+set[i]);
        }
    }
    public int[] solution(String[] info, String[] query) {
        //1.info[i]]마다 모든 가능한 문조열 조합 만들어서 map에 저장,map을 완성!
        map = new HashMap<>();
        makeMap(0,"");//makeMap(int idx,String str), idx:현재 선택하는 속성 인덱스, str:지금까지 선택한 문자열!
        //2.map 완성 후 info의 점수를 추가, map을 갱신!
        int infoSize = info.length;
        for(int i=0;i<infoSize;i++){
        	String[] input = info[i].split(" ");//0,1,2,3 속성별로 순서대로 저장~
        	String[][] tmpDataSet = {{"-",input[0]},{"-",input[1]},{"-",input[2]},{"-",input[3]},{input[4]}};
        	updateMap(0,"",tmpDataSet);
        	
        }
        //3.keySet()으로 각 value 리스트 오름차순 정렬!!
        for(String s:map.keySet()){
            Collections.sort(map.get(s));
            // System.out.println("key의 점수 리스트 현황(정렬되있어야함)");
            // for(int i=0;i<map.get(s).size();i++){
            //     System.out.print(map.get(s).get(i)+" ");
            // }
            // System.out.println();
        }

        //4.query[i]마다 조건 만족하는 지원자 수 조회!
        //4-1.전처리 : 문자열 파싱으로 조건 추출
        //4-2.map에서 조건 조회
        //4-3.이분 탐색을 X점 이상 만족하는 지원자 수 answer[i]에 저장!
        int querySize = query.length;
        int[] answer = new int[querySize];
        for(int i=0;i<querySize;i++){
            String[] input = query[i].split(" ");
            
            //2-2.map에서 조건 조회 : 0,1,2,3 원소 = map의 key
            String cmd = input[0] + input[2] +input[4] + input[6];
            //System.out.println("query = "+cmd);
            ArrayList<Integer> list = map.get(cmd);
            //2-3.이분 탐색 후 x점 이상 만족하는 지원자 수 answer[i]에 저장!
            answer[i] = binarySearch(list,Integer.parseInt(input[7]));
        }
        return answer;
    }
    void updateMap(int idx,String str,String[][] tmpDataSet) {
    	if(idx == 4) {
    		ArrayList<Integer> list = map.get(str);
    		list.add(Integer.parseInt(tmpDataSet[4][0]));
    		map.put(str, list);
    		return;
    	}
    	String[] set = tmpDataSet[idx]; int len = set.length;
    	for(int i=0;i<len;i++) {
    		updateMap(idx+1,str+set[i],tmpDataSet);
    	}
    }
    int binarySearch(ArrayList<Integer> list, int X){
        //list에서 X가 처음 나오는 인덱스를 찾고, X 이상인 지원자 수를 리턴한다!
        int size = list.size();
        int s = 0,e = size-1, mid = 0;
        while(s<=e){
            mid = (s+e)/2;
            if(list.get(mid)<X) s = mid+1;
            else if(list.get(mid) >= X) e = mid-1;
        }
        return size-s;
    }
    
}
