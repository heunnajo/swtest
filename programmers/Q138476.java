//Q138476 귤 고르기
import java.util.*;

class Solution {
    class Pair implements Comparable<Pair> {
        int size, cnt;
        
        Pair(int size,int cnt){
            this.size = size;
            this.cnt = cnt;
        }
        public int compareTo(Pair p){
            return p.cnt - this.cnt;//b-a로 함으로써 내림차순 정렬.
        }
    }
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        
        int len = tangerine.length;
        for(int i=0;i<len;i++){
            
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
        }
        int kind = map.size();
        
        ArrayList<Pair> list = new ArrayList<>();
        for(Integer key:map.keySet()){
            list.add(new Pair(key,map.get(key)));
        }
        Collections.sort(list);//이건 오름차순 정렬이 될까? 아님 Comparable 오버라이딩한대로 정렬될까?
        
        for(int i=0;i<kind;i++){
            k -= list.get(i).cnt;
            if(k <= 0){
                answer = i+1;
                break;
            }
        }
        return answer;
    }
}
// if(!map.containsKey(tangerine)){
//                 map.put(tangerine[i],1);
//             }
// for(Integer key:map.keySet()){
//             System.out.print("key(귤  크기) :"+key+" value(귤 갯수) :"+map.get(key));
//             System.out.println();
//         }