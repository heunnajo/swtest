class AdsInsertion_2nd {
    static int convert(String times){//HH:MM:SS를 초로 변환!
        String[] time = times.split(":");//:로 문자 구분!
        return Integer.parseInt(time[0])* 3600 + Integer.parseInt(time[1])* 60 + Integer.parseInt(time[2]);
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        //1.영상 재생 시간과 광고 시간을 초 단위(정수)로 변환!
        int playSec = convert(play_time);
        int advSec = convert(adv_time);
        //1.전체 영상을 의미하는 배열 생성
        int[] totalSec = new int[360000];//영상 길이는 최대 100시간이기 때문에
        //2.각각의 로그 또한 초 단위로 변환한다.
        //2.HH:MM:SS-HH:MM:SS 형태이기 때문에 substring을 이용하여 시작, 종료로 먼저 구분한 후에
        //2.각각의 시각을 convert한다.
        for(String log : logs){//substring 리턴타입은 String이기 때문에 바로 convert처리한다!
            int start = convert(log.substring(0,8));//시작 시각 = 0부터 7까지.
            int end = convert(log.substring(9,17));//9부터 끝까지.(길이가 17이니까 17넣어주면 endIdx=17)
            //해당 로그 구간 배열에 1씩 증가시킨다!
            //start=1, end=5
            for(int i=start;i<end;i++){
                totalSec[i] += 1;
            }
        }
        //3.슬라이딩 윈도우
        //3-1.0번째부터 시작.
        long curSum=0;//광고 구간만큼의 누적 횟수 합산값 저장.
        //for(int i=0;i+advSec<playSec;i++){
        for(int i=0;i<advSec;i++){
            curSum += totalSec[i];
        }
        //3-2.본격적으로 오른쪽으로 1칸씩 이동! 구간이 끝나는 인덱스를 기준으로 반복문 구성!
        long maxIdx = 0;
        long maxSum = curSum;
        for(int i=advSec;i<playSec;i++){
            curSum = curSum + totalSec[i] - totalSec[i-advSec];
            if(curSum > maxSum){
                maxSum = curSum;
                maxIdx = i-advSec + 1;//5-5+1
            }
        }
        
        return String.format("%02d:%02d:%02d",maxIdx/3600, (maxIdx/60)%60, maxIdx%60 );
    }
}
