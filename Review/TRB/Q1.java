//1.문자열 
import java.util.*;
class Solution{
    public String solution(String s){
        int[] arr = new int[26];//각 알파벳 횟수를 기록. 인덱스 = 알파벳의 인덱스, 배열값 = 알파벳의 횟수
        s = s.toLowerCase();//s를 굳이 사전순으로 정렬할 필요는 없는 것 같음.
        int len = s.length();
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            int idx = c-'a';
            arr[idx]++;
        }
        //가장많은 알파벳 도출
        int Max = 0; List<Integer> maxAlpha = new LinkedList<>();
        for(int i=0;i<26;i++){
            if(Max<arr[i]) {
                //최댓값 갱신
                Max = arr[i]; maxAlpha.clear(); maxAlpha.add(i);
            } else if(Max==arr[i]){
                maxAlpha.add(i);//기존 리스트에 추가
            }
        }
        //가장 많은 알파벳의 인덱스가 잘 기록?:O
        /*System.out.println("maxAlpha의 크기: "+maxAlpha.size());
        for(int i=0;i<maxAlpha.size();i++){
            System.out.print(maxAlpha.get(i)+" ");
        }
        System.out.println();*/
        //map을 완성해야함.
        Map<Integer,String> map = new HashMap<>();
        //String tmp = "a"+0;
        //System.out.println("tmp: "+tmp);
        // for(int i=0;i<26;i++){
        //     map.put(i,'a'+0);
        // }
        map.put(0,"a");map.put(1,"b");map.put(2,"c");map.put(3,"d");map.put(4,"e");
        map.put(5,"f");map.put(6,"g");map.put(7,"h");map.put(8,"i");map.put(9,"j");
        map.put(10,"k");map.put(11,"l");map.put(12,"m");map.put(13,"n");map.put(14,"o");
        map.put(15,"p");map.put(16,"q");map.put(17,"r");map.put(18,"s");map.put(19,"t");
        map.put(20,"u");map.put(21,"v");map.put(22,"w");map.put(23,"x");map.put(24,"y");map.put(25,"z");
        List<String> ans = new LinkedList<>();
        for(int i:maxAlpha){
            ans.add(map.get(i));//maxAlpha에 기록된 가장 많은 알파벳의 인덱스로 해당 알파벳을 조회
        }
        StringBuilder answer = new StringBuilder();
        for(String st:ans){
            answer.append(st);
        }
        //System.out.println();
        return answer.toString();
    }
}