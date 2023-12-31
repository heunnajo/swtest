import java.util.*;
import java.io.*;
//합승 택시 요금
public class KB2021_Q5 {
	static int[] ad;
	public static void main(String[] args) throws IOException {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		solution(play_time,adv_time,logs);
	}
	public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        ad = new int[360000];
        int playTime = stringToInt(play_time);
        int advTime = stringToInt(adv_time);
        
        for(int i=0;i<logs.length;i++) {
        	String[] tmp = logs[i].split("-");
        	int s = stringToInt(tmp[0]);
        	int e = stringToInt(tmp[1]);
        	for(int j=s;j<e;j++) ad[j]++;
        }
        
        int max_idx = 0;
        long max_sum = 0;
        long sum = 0;
        for(int i=0;i<advTime;i++) {
        	sum += ad[i];
        }
        max_sum = sum;
        
        //슬라이딩 윈도우, 투포인터로 최댓값 갖는 구간의 첫 인덱스 도출
        for(int i=advTime;i<playTime;i++) {
        	sum += ad[i] - ad[i-advTime];
        	if(max_sum < sum) {
        		max_sum = sum;
        		max_idx = i-advTime+1;
        	}
        }
        answer = intToString(max_idx);
        //System.out.println("answer: "+answer);
        return answer;
    }

	static int stringToInt(String time) {
		int sum = 0;
		String[] input = time.split(":");
		int h = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int s = Integer.parseInt(input[2]);
		sum = h*3600 + m*60 + s;
		//System.out.println("stringToInt "+time+": "+sum);
		return sum;
	}
	static String intToString(int time) {
		String res = "";
		int h = time / 3600;
		time = time % 3600;
		int m = time / 60;
		int s = time % 60;
		
		String hh = h+"";
		if(h<10) hh = "0"+h;
		
		String mm = m+"";
		if(m<10) mm = "0"+m;
		
		String ss = s+"";
		if(s<10) ss = "0"+s;
		
		res = hh+":"+mm+":"+ss;
		//System.out.println("intToString "+time+": "+res);
		return res;
	}
}
