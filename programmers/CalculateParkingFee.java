import java.util.*;
public class Solution {
	
    public int[] solution(int[] fees, String[] records) {
        
        getTotalTime(records);//totalTime를 완성
        getTotalFee(fees);//feeList를 완성
        int size = feeList.size();
        int[] answer = new int[size];//차량 번호 오름차순으로 요금 저장!
        for(int i=0;i<size;i++){
            answer[i] = feeList.get(i)[1];
        }
        return answer;
    }
    
    Map<Integer,Integer> totalTime;
    List<int[]> feeList;
    
    void getTotalTime(String[] records){//totalTime를 완성
        totalTime = new HashMap<>();
        int len = records.length;
        
        Map<Integer,Integer> inTime = new HashMap<>();
        for(int i=0;i<len;i++){
            String[] record = records[i].split(" ");
            String time = record[0];
            int carNum = Integer.parseInt(record[1]);
            String status = record[2];
            
            //현재 status에 따라 주차 시간 계산
            if(status.equals("IN")){
                inTime.put(carNum,getTime(time));
            } else{//OUT
                totalTime.put(carNum,totalTime.getOrDefault(carNum,0)+(getTime(time)-inTime.get(carNum)));
                inTime.remove(carNum);
            }

        }
      //OUT이 없는 경우! 23:59에서 빼준다
        int maxTime = 23*60+59;
        for(int cN:inTime.keySet()){
             totalTime.put(cN,totalTime.getOrDefault(cN,0)+(maxTime-inTime.get(cN)));
        }
    }
    int getTime(String time){
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return h*60 + m;
    }
    void getTotalFee(int[] fees){//feeList를 완성
        feeList = new ArrayList<>();
        for(int carNum:totalTime.keySet()){
            feeList.add(new int[]{carNum,getFee(totalTime.get(carNum),fees)});
        }
        Collections.sort(feeList,(f1,f2)->{
            return f1[0]-f2[0];//오름차순
        });
    }
    int getFee(int parkingTime,int[] fees){
        int baseTime = fees[0], baseFee = fees[1];
        int unitTime = fees[2], unitFee = fees[3];
        int total = 0;
        if(parkingTime>baseTime){
            total += baseFee;
            parkingTime -= baseTime;
            total += (parkingTime/unitTime)*unitFee;
            if(parkingTime%unitTime != 0){//나눠떨어지지 않는 경우 unitFee를 한번 더 더해준다!
                total += unitFee;
            }
            return total;
        }
        return baseFee;
    }
}