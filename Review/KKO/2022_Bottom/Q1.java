import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<String,Integer> termInfo = new HashMap<>();
        String y = today.substring(0,4);
        String m = today.substring(5,7);
        String d = today.substring(8);
        String ttmp = y+m+d;
        int todayDate = Integer.parseInt(ttmp);
        //System.out.println("todayDate: "+todayDate);//20220519

        int n = terms.length;

        for(int i=0;i<n;i++){
            String[] input = terms[i].split(" ");
            String name = input[0];
            int period = Integer.parseInt(input[1]);

            termInfo.put(name,period);
        }

        for(int i=0;i<privacies.length;i++){//n개의 개인 정보. 파기 대상 개인 정보 번호 저장!
            String[] input = privacies[i].split(" ");
            String fullDate = input[0];

            String strYear = fullDate.substring(0,4);
            String strMonth = fullDate.substring(5,7);
            String strDate = fullDate.substring(8);

            int year = Integer.parseInt(strYear);
            int month = Integer.parseInt(strMonth);
            int date = Integer.parseInt(strDate);

            String term = input[1];
            int period = termInfo.get(term);//유효기간 달
            //System.out.println(term+":"+period);
            int newYear = 0;
            int newMonth = 0;
            int newDate = 0;

            newMonth = month + period;
            newYear = year + newMonth/12;
            newMonth = newMonth % 12;
            newDate = date;

            StringBuilder sb = new StringBuilder();
            sb.append(newYear);
            if(newMonth<10) sb.append("0");
            sb.append(newMonth);
            if(newDate<10) sb.append("0");
            sb.append(newDate);

            String tmp = sb.toString();
            int expiredDate = Integer.parseInt(tmp);
            //System.out.println("만료 기간: "+expiredDate);

            if(expiredDate > todayDate) ans.add(i+1);
        }

        int size = ans.size();

        int[] answer = new int[size];

        for(int i=0;i<size;i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
//map확인
        // for(String key:termInfo.keySet()){
        //     System.out.println(key+" "+termInfo.get(key));
        // }