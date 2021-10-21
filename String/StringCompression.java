class StringCompression {
    public int solution(String s) {
        int n = s.length();
        for(int x=1;x<=n/2;x++){
            //1.x단위로 문자열을 자르고, isSame에 저장
            String[] isSame = new String[n];
            //int len = isSame.lenth();
            for(int i=0;i<n;i+=x){
                StringBuilder tmp = new StringBuilder();
                for(int j=i;j<x;j++){
                    tmp.append(s.charAt(j));
                }
                isSame[i] = tmp.toString();
            }
            //2.x단위로 자른 문자열의 빈도수를 저장. counting[i] : isSame[i]문자열의 빈도수를 의미
            int[] counting = new int[n];
            for(int i=0;i<n;i++){
                String start = isSame[i]; int cnt = 1;
                for(int j=i+1;j<n;j++){
                    if(start.equals(isSame[j])) cnt++;
                    else{
                        counting[j-x*cnt] = cnt;
                        start = isSame[j];
                        cnt = 1;
                    }
                }
            }
            //3.현재 단위x로 잘랐을 때 최종 압축 문자열 구하기:최솟값으로 갱신 = 정답
            StringBuilder finalS = new StringBuilder();
            for(int i=0;i<n;i++){
                if(counting[i]==1){//빈도수가 1번이라면 그냥 문자열만 추가
                    finalS.append(isSame[i]);
                } else{
                    finalS.append(counting[i]);
                    finalS.append(isSame[i]);
                    i = i+counting[i]-1;//다음 인덱스 점프? : 빈도수
                }
            }
            String ans = finalS.toString();
            int answer = 2000;
            answer = Math.min(answer,ans.length());
            return answer;
    }
}