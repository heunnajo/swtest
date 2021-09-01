class AdsInsertion_Solution2 {
    int convert(String time){//time = HH:MM:SS
        String[] nums = time.split(":");//:로 문자를 나눈다!
        return Integer.parseInt(nums[0])* 60 * 60 + Integer.parseInt(nums[1])* 60 + Integer.parseInt(nums[2]);
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = convert(play_time);//영상러닝타임을 초로 변환해서 playSec에 저장!
        int advSec = convert(adv_time);
        
        int[] totalSec = new int[100 * 3600];//영상 최대 시간은 100시간이기 때문에 크기를 미리 잡고 간다. 크기를 미리 먼저 잡는 것이 ezsw코드 구현 특징. play_time만큼?하는 게 아니라.
        for(String log:logs){
            int start = convert(log.substring(0,8));//시작 시간 = 0번부터 7번까지
            int end = convert(log.substring(9,17));//중간에 부호를 제외하고, 9번부터 16번까지
            
            //각 시청 시각 log에 대해 시작시각 ~ 종료시각까지 반복! ex) start = 1, end = 12
            for(int i=start;i<end;i++){
                //totalSec[i]++;
                totalSec[i] += 1;
            }
        }
        
        long currSum = 0;
        for(int i=0;i<advSec;i++){//광고 길이만큼 반복.
            currSum += totalSec[i];
        }
        
        long maxSum = currSum;//window에서 합산한 값(누적 재생횟수)를 maxSum에 저장!
        int maxIdx = 0;
        for(int i=advSec;i<playSec;i++){//영상 재생 시간 이내에서 1칸씩 오른쪽으로 이동한다.값 갱신할 땐, 기존의 값에서 새로운 i번째 칸 더해주고, i-(광고길이) 만큼 뺀 값은 빼주는 형식으로 갱신한다!
            currSum = currSum + totalSec[i] - totalSec[i-advSec];
            if(currSum>maxSum){
                maxSum = currSum;
                maxIdx = i-advSec+1;
            }
        }
        return String.format("%02d:%02d:%02d", maxIdx/3600, maxIdx / 60 % 60, maxIdx % 60);        
    }
}