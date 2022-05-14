class Solution {
    public int solution(String[] scores) {
        int answer = 0;

        int len = scores[0].length();//심사위원 수
        for(int i=0;i<scores.length;i++){
            //현재 지원자 점수 구하기
            int cntA = 0, cntF = 0,total = 0, avg = 0,max=-1,min=10;
            int[] nums = new int[6];

            for(int j=0;j<len;j++){
                if(scores[i].charAt(j) == 'F') {
                    cntF++;
                    //nums[0]++;
                }
                if(scores[i].charAt(j) == 'A') {
                    cntA++;
                    //nums[5]++;
                }
                if(cntF >= 2) break;//다음 지원자로
                if(cntA >= 2){ answer++; break;}//합격자수 1증가, 다음 지원자로

                //현재 지원자의 최고점, 최저점 구한다.
                if(scores[i].charAt(j) == 'A') nums[5]++;
                else if(scores[i].charAt(j) == 'B') nums[4]++;
                else if(scores[i].charAt(j) == 'C') nums[3]++;
                else if(scores[i].charAt(j) == 'D') nums[2]++;
                else if(scores[i].charAt(j) == 'E') nums[1]++;
                else if(scores[i].charAt(j) == 'F') nums[0]++;

                //System.out.println(i+"번 지원자의 알파벳별 점수 확인");
                for(int k=0;k<6;k++) {
                    //System.out.print(nums[k]+" ");
                    total+=nums[k];//총 점수 total
                }
                //System.out.println();

                //최고점, 최소점 구해서 빼주기
                for(int k=0;k<len;k++){
                    if(nums[k] == 0)continue;
                    if(min == 10) min = k;//최저 점수 = min : 처음 0이 아닌 nums[k]가 나오면 그 값이 바로 min임. 일회성임.
                    if(k>max) max = k;//최고 점수 = max
                }
                //System.out.println("max; "+max);
                //System.out.println("min; "+min);

                //System.out.println("avg: "+avg);

            }
            total = total - max - min;//전체 점수에서 최저점, 최고점 하나씩 제외한 나머지 점수 평균
            //System.out.println("total: "+total);
            len -= 2;
            //System.out.println("len: "+len);
            avg = total/len;//최고점, 최저점 갯수2개 뺀 것으로 나눈다.
            //System.out.println("avg: "+avg);//왜 ArithmeticException 발생함?
            if(avg >= 3) answer++;
        }

        return answer;
    }
}