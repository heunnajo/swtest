//Q3. 순위 검색
import java.util.*;
class Q3_3rd {
    static String[][] infoArr;
    static HashMap<String,LinkedList<Integer>> map;
    static int iLen, qLen;
    static int[] answer;
    public static void main(String[] args) throws Exception {
    	String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
    	String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
    	solution(info,query);
    	for(int i=0;i<qLen;i++) {
    		System.out.print(answer[i]+" ");
    		
    	}
    	System.out.println();
	}
    public static int[] solution(String[] info, String[] query) {
        //변수 초기화
        qLen = query.length; iLen = info.length;
        answer = new int[qLen];
        infoArr = new String[info.length][5];
        map = new HashMap<>();
        
        //0.info 전처리(flag=0:info전처리) : infoArr을 완성
        for(int i=0;i<iLen;i++){
            //System.out.println("여기 제대로 들어와서 처리하니?!!");
            infoArr[i] = preprocess(0,info[i]);//java, backend,pizza,150 을 저장하는 문자열 배열을 리턴
        }
        //1.완전 탐색 : infoArr로 각 infoArr[i]마다 4가지 항목에 대해 완전 탐색!
        for(int i=0;i<iLen;i++){
            permu(i,0,"");//info인덱스,항목인덱스,현재까지선택한정보
        }
        
        for(int i=0;i<qLen;i++){
            //0.전처리(flag=1:query전처리)
            String[] tmp = preprocess(1,query[i]);
            //1.query[i]마다 조건에 만족하는 x점 이상인 사람 수를 anaswer[i]에 저장
            String cmd = tmp[0]; int x = Integer.parseInt(tmp[1]);
            answer[i] = process(cmd,x);//이분탐색
        }
        return answer;
    }
    static void permu(int infoIdx,int idx,String selected){
        //1.종료 조건
        if(idx == 4){
            if(map.containsKey(selected)){
                map.get(selected).add(Integer.parseInt(infoArr[infoIdx][4]));
            } else{
                LinkedList<Integer> list = new LinkedList<>();
                //System.out.println("infoArr[infoIdx][4] : "+infoArr[infoIdx][4]);//왜 null이냐?
                list.add(Integer.parseInt(infoArr[infoIdx][4]));//
                map.put(selected,list);
            }
            return;
        }
        //2.현재 경우 선택
        //2-1.선택 O
        permu(infoIdx,idx+1,selected+infoArr[infoIdx][idx]);
        //2-1.선택 X
        permu(infoIdx,idx+1,selected+"-");
    }
    static String[] preprocess(int flag,String str) {
        String[] ret = new String[2];
        //1.flag = 0 : info[i] : 공백문자만 제거해서 리턴
        if(flag == 0) return str.split(" ");
//        if(flag == 0){
//            String[] tmp = str.split(" ");
//            for(int i=0;i<tmp.length;i++){
//                System.out.print(tmp[i]+" ");
//            }
//            System.out.println();
//        }
        
        //2.flag = 1 : query[i] : 조건, 점수 2개만 문자열 형태로 담아서 리턴!
        else{//and를 제거하고 저장
            int idx = 0;
            String[] tmp = str.split(" "); String cmd  = "";
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].equals("and")) continue;
                if(i != tmp.length-1) {
                	cmd += tmp[i];
                } else if(i == tmp.length-1) {
                	ret[1] = tmp[i];
                }
            }
            ret[0] =  cmd;
        }
//        System.out.println("조건 : "+ret[0]);
//        System.out.println("점수 : "+ret[1]);
        return ret;
    }
    static void sort(){
        for(String s:map.keySet()){
            LinkedList<Integer> list = map.get(s);
            Collections.sort(list);
        }
    }
    static int process(String cmd,int x){
        if(!map.containsKey(cmd)) return 0;
        
        //이분 탐색
        sort();
        LinkedList<Integer> list = map.get(cmd);
        int s = 0, e = list.size()-1, mid = -1;
        while(s<=e){
            mid = (s+e)/2;
            if(list.get(mid)<x){
                s = mid+1;
            } else if(list.get(mid) >= x){
                e = mid-1;
            }
        }
        return list.size()-s;
    }
}
