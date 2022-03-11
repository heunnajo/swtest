//3.후보키
import java.util.*;
class Solution {
    public int solution(String[][] relation) {
        //1.자료구조
        LinkedList<Integer> candiKeys = new LinkedList<>();
        int rowLen = relation.length;//튜플 갯수
        int colLen = relation[0].length;//컬럼 갯수
        
        //2.알고리즘 : 비트마스킹을 이용해 조합 구하고, 각 조합에 대해 최소성, 유일성 검사!
        for(int set=0;set<(1<<colLen);set++){//어떤 칼럼을 하나도 선택하지 않을 수는 없으므로 set=1부터 시작
            //1.최소성 검사 2.유일성 검사
            // if(!isMinimum(set,candiKeys)) continue;
            // if(isUnique(set,rowLen,colLen,relation)){
            //     candiKeys.add(set);
            // }
            //1.유일성 검사 2. 최소성 검사
            if(!isUnique(set,rowLen,colLen,relation)) continue;
            if(isMinimum(set,candiKeys)){
                candiKeys.add(set);
            }
        }
        
        return candiKeys.size();
    }
    boolean isMinimum(int set,LinkedList<Integer> candiKeys){
        //& 연산 : 공통으로 1인 비트 추출 = 공통으로 갖는 속성 추출
        for(int key:candiKeys){
            if((key & set) == key) return false;//이미 candiKeys에 유일성, 최소성 만족하는 키가 존재하는 경우 바로 false를 리턴!
        }
        return true;
    }
    
    boolean isUnique(int set,int rowLen,int colLen,String[][] relation){
        //현재의 set에 따라 데이터를 조합하여 map에 저장
        HashMap<String,String> map = new HashMap<>();
        
        for(int i=0;i<rowLen;i++){
            String curData = "";
            for(int j=0;j<colLen;j++){
                if((set & (1<<j)) != 0){
                    curData += relation[i][j];
                }
            }
            if(map.containsKey(curData)) return false;//각 튜플마다 조합 데이터저장할 때, 이미 존재한다면 유일성 만족X이므로 바로 false를 리턴! 
            map.put(curData,curData);
        }
        return true;
    }
}