import java.util.*;

class Solution {
    public String solution(String call) {
        String answer = "";
        HashMap<Character,Integer> map = new HashMap();
        String lowerVer = call.toLowerCase();
        //System.out.println("lowerVer: "+lowerVer);

        int n = call.length();

        //map 완성 : 대소문자 상관없이 처리해야함. 따라서 소문자로 변환한 뒤에 처리.
        for(int i=0;i<n;i++){
            if(map.containsKey(lowerVer.charAt(i))){
                map.put(lowerVer.charAt(i),map.get(lowerVer.charAt(i))+1);
            } else{
                map.put(lowerVer.charAt(i),1);
            }
        }

        //최댓값 도출 : 확인 완료
        int max = 0;
        for(char c:map.keySet()){
            if(map.get(c)>max) {
                max = map.get(c);
            }
            //System.out.println(c+" : "+ map.get(c));
        }
        //System.out.println("최댓값 : "+max);

        for(int i=0;i<n;i++){
            if(map.get(lowerVer.charAt(i)) != max) answer += call.charAt(i);
        }
        return answer;
    }
}