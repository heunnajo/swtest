import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
class Solution {
    static Map<String,List<Integer>> map = new HashMap<>();
    static int[] answer;
    
    static void dfs(int index,String str,String[] info){
        if(index == 4){
            if(map.containsKey(str) == false){
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                map.put(str,list);
            }
            else{
                map.get(str).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(index+1,str+info[index],info);
        dfs(index+1,str+"-",info);
    }
    static void makeMap(String[] info){
        for(int i=0;i<info.length;i++){
            dfs(0,"",info[i].split(" "));
        }
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            List<Integer> li = map.get(key);
            Collections.sort(li);
        }
    }
    static int counting(String str,int score){
        if(map.containsKey(str) == false){return 0;}
        
        
            List<Integer> list = map.get(str);
            int start = 0, end = list.size()-1;
            while(start<=end){
            int mid=(start+end)/2;
            if(list.get(mid)<score){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
            return list.size()-start;
    }
    static void makeAnswer(String[] query){
        for(int i=0;i<query.length;i++){
            String input = "";
            String[] tmp = query[i].split(" ");
            
            for(int j=0;j<tmp.length-1;j++){
                if(tmp[j].equals("and")) continue;
                input += tmp[j];
            }
            answer[i] = counting(input,Integer.parseInt(tmp[tmp.length-1]));
        }
    }
    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        makeMap(info);
        makeAnswer(query);//answer 배열을 완성
        return answer;
    }
}