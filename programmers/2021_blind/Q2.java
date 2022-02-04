//2. 메뉴 리뉴얼
import java.util.*;
class Solution {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
        //초기화
        for(int i=0;i<=10;i++){
            HashMap<String,Integer> tmp = new HashMap<String,Integer>();
            list.add(tmp);
        }
        //1.메뉴 조합 만들기
        for(int k=0;k<orders.length;k++){
            char[] arr = orders[k].toCharArray();
            int len = arr.length;
            Arrays.sort(arr);
                        
            for(int i=1;i<(1<<len);i++){
                String menu = "";//각 부분집합에 대해 문자열 조합 만들어야하기 때문에 반복문안에 들어가야한다!
                for(int j=0;j<len;j++){
                    if((i&(1<<j)) != 0) menu += arr[j];
                }
                //메뉴를 완성했으면 list의 메뉴의 갯수(문자열길이) 인덱스에 저장!
                int menuLen = menu.length();
                int cnt = list.get(menuLen).getOrDefault(menu,0);
                list.get(menuLen).put(menu,cnt+1);
            }
        }
        
        //2.1에서 만든 메뉴 조합 갯수 계산
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<course.length;i++){
            int maxFreq = 0;//가장 많이 주문한 것을 카운팅하기 위해 각 문자열 길이 마다 최빈값 갖는 문자열 찾는다!
            for(Map.Entry m : list.get(course[i]).entrySet()){
                maxFreq = Math.max(maxFreq,(int)m.getValue());
            }
            if(maxFreq < 2) continue;//메뉴는 2가지 이상이여야함! 그렇지 않으면 이 메뉴는 넣지 않음, 컨티뉴 처리
            //가장 많이 주문한 것 여러개이면 다 저장한다!
            for(Map.Entry m : list.get(course[i]).entrySet()){
                if((int)m.getValue() == maxFreq) ans.add((String)m.getKey());
            }
        }
        
        Collections.sort(ans);
        //컬렉션 ans를 배열 타입으로 변환!
        return ans.toArray(new String[ans.size()]);
    }
}