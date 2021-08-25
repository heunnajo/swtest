import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Arrays;
class Menu_Renewal_Solution {
    static HashMap<String,Integer> map;
    static int m;//동일한  갯수  라  도  손  님  들  이  가  장  많  이 선  택  한  단  품  메  뉴  조  합  을  선  택  
    public String[] solution(String[] orders, int[] course) {
       PriorityQueue<String> pq = new PriorityQueue<>();//우선순위 큐의 우선권 갖는 기준은 뭐지??
        for (int i=0;i<course.length;i++){
            map = new HashMap<>();
            m=0;
            for (int j=0;j<orders.length;j++) {
                find(0, "", course[i], 0, orders[j]);
            }
            for (String s : map.keySet()){
                if (map.get(s) == m && m > 1){//m>1 :선택한 단품메뉴가 2개 이상인 조건 같음. map을 순회하는데 s가 m이면
                    pq.offer(s);//우선순위 큐에 삽입!
                }
            }
        }
        String  ans[] = new String[pq.size()];
        int k=0;
        while (!pq.isEmpty()){
            ans[k++] = pq.poll();//우선순위 큐에 들어간 문자열을 하나씩 문자열 배열에 최종 저장하고, 이 문자열 배열을 리턴한다.
        }
        return ans;
    }
    static void find(int cnt,String str,int targetNum,int idx,String word){
        if (cnt==targetNum){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String temps="";
            for (int i=0;i<c.length;i++)temps+=c[i];
            map.put(temps,map.getOrDefault(temps,0)+1);
            m = Math.max(m,map.get(temps));
            return;
        }
        for (int i=idx;i<word.length();i++){
            char now =word.charAt(i);
            find(cnt+1,str+now,targetNum,i+1,word);
        }
    }
}