//#133499 옹알이 (2)

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        int n = babbling.length;
        String[] possible = {"aya","ye","woo","ma"};
        String[] impossible = {"ayaaya","yeye","woowoo","mama"};
        
        for(int i=0;i<n;i++){
            String cur = babbling[i];
            for(String impos : impossible){
                cur = cur.replace(impos,"X");
            }
            for(String pos : possible){
                cur = cur.replace(pos,"O");//여기서 치환이 제대로 안됨!
            }
            boolean flag = true;
            for(int j=0;j<cur.length();j++){
                if(cur.charAt(j) != 'O') {
                    // System.out.println("i: "+i);
                    // System.out.println("cur: "+cur);
                    flag = false;
                    break;
                }
            }
            
            if(flag) answer++;
        }
        return answer;
    }
}