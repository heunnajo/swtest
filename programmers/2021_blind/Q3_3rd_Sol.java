//Q3. 순위 검색
import java.util.*;
class Solution {    
    String[][] infoArr;
    HashMap<String,ArrayList<Integer>> map;
    int iLen, qLen;
    int[] answer;
    
    public int[] solution(String[] info, String[] query) {
        qLen = query.length; iLen = info.length;
        answer = new int[qLen];
        infoArr = new String[info.length][5];
        map = new HashMap<>();
        
        for(int i=0;i<iLen;i++){
            infoArr[i] = preprocess(0,info[i]);
        }
        for(int i=0;i<iLen;i++){
            permu(i,0,"");
        }
        sort();
        for(int i=0;i<qLen;i++){
            String[] tmp = preprocess(1,query[i]);
            String cmd = tmp[0]; int x = Integer.parseInt(tmp[1]);
            answer[i] = process(cmd,x);
        }
        return answer;
    }
    void permu(int infoIdx,int idx,String selected){
        if(idx == 4){
            if(map.containsKey(selected)){
                map.get(selected).add(Integer.parseInt(infoArr[infoIdx][4]));
            } else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(infoArr[infoIdx][4]));//
                map.put(selected,list);
            }
            return;
        }
        permu(infoIdx,idx+1,selected+infoArr[infoIdx][idx]);
        permu(infoIdx,idx+1,selected+"-");
    }
    String[] preprocess(int flag,String str) {
        String[] ret = new String[2];
        if(flag == 0) return str.split(" ");
        else{
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
        return ret;
    }
    void sort(){
        for(String s:map.keySet()){
            ArrayList<Integer> list = map.get(s);
            Collections.sort(list);
        }
    }
    int process(String cmd,int x){
        if(!map.containsKey(cmd)) return 0;
        
        ArrayList<Integer> list = map.get(cmd);
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