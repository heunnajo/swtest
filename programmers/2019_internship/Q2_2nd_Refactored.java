import java.util.*;
class Solution {
    public int[] solution(String s) {
        String[] arr = s.replaceAll("[{]"," ").replaceAll("[}]"," ").trim().split(" , ");
        
        Arrays.sort(arr,(a,b)->{return a.length()-b.length();});
        
        Set<String> set = new HashSet<>();
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String str:arr){
            //System.out.print(str+"그리고");
            for(String st:str.split(",")){
                //System.out.print(st[i]+" ");
                if(set.add(st)) answer[idx++] = Integer.parseInt(st);
            }
            //System.out.println();
        }
        return answer;
    }
}
// for(String str:arr){
//             System.out.print(str+"그리고");
//         }