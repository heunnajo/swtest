package ss;
import java.util.*;
import java.io.*;
public class NTS_2_StringSimulation {
	static String[] DayToNum = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
	static int[] EndDate = {-1,31,28,31,30,31,30,31,31,30,31,30,31};
	static String[] holiMD;
	static int H;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String join_date = br.readLine();
		String resign_date = br.readLine();
		
		H = Integer.parseInt(br.readLine());//H개의 공휴일 입력받는다.
		String[] holidays = new String[H];
		for(int i=0;i<H;i++) {
			holidays[i] = br.readLine();
		}
		holiMD = new String[H];
		for(int i=0;i<H;i++) {
			holiMD[i] = holidays[i].substring(0,2)+holidays[i].substring(3);
			//System.out.println(holiMD[i]);
		}
		
		String today = parseToYYYYMMDDDAY(join_date,1);
		String endDate = parseToYYYYMMDDDAY(resign_date,-1);//-1:DAY가 없음을 의미 : YYYYMMDD 만 리턴
		//문자열 파싱, 윤년 확인 완료
		/*if(isLeapYear(today)) System.out.println("윤년입니다!");
		else System.out.println("윤년이 아닙니다!");
		
		System.out.println("today: "+today);
		System.out.println("endDate: "+endDate);*/
		int answer = 0;
		while(true) {//today = YYYYMMDDDAY 형태!
			if(isWorkingDay(today)) answer++;
			if(today.substring(0,8).equals(endDate)) break;//퇴사날이면 반복문 탈출
			//if(isLeapYear(today)) answer++;여기서 틀림!윤년조건 : 현재 2월 이전이라면 그냥 막 더하면 안됨!
			today = tomorrow(today);
		}
		//tomorrow 모듈 확인 완료
		/*String DOB = "19951231FRI";
		System.out.println(tomorrow(DOB));*/
		System.out.println(answer);
	}
	//각 모듈별로 함수로 구현하고, 각 모듈(함수)마다 제대로 동작하는지 원하는 값을 리턴하는지 확인한다.
	static String parseToYYYYMMDDDAY(String today,int isJoinDate) {//today = YYYYMMDDDAY 형태
		if(isJoinDate == 1) return today.substring(0,4)+today.substring(5,7)+today.substring(8,10)+today.substring(11);//퇴사일에 대한 처리:반복문 종료 조건에 해당하는 처리 하기 위해 구현.
		else return today.substring(0,4)+today.substring(5,7)+today.substring(8);
	}
	static boolean isWorkingDay(String today) {//today = YYYYMMDDDAY
		//1.토/일 판단
		String day = today.substring(8);
		if(day.equals(DayToNum[5]) || day.equals(DayToNum[6])) return false;
		//2. 공휴일 판단
		String todayMD = today.substring(4,8);
		for(int i=0;i<H;i++) {
			if(todayMD.equals(holiMD[i])) return false;
		}
		return true;
	}
	static boolean isLeapYear(String today) {
		String Year = today.substring(0,4);
		int y = Integer.parseInt(Year);
		if(y % 400 == 0) return true;
		if(y % 4 == 0 && (y%100 != 0)) return true;
		else return false;
	}
	static String tomorrow(String today) {
		String tmpY = today.substring(0,4);
		String tmpM = today.substring(4,6);
		String tmpD = today.substring(6,8);
		String tmpDay = today.substring(8);
		//1.정수로 변환
		int Y = Integer.parseInt(tmpY);//0001처리가 가능한가?=>0001인 경우, 1
		int M = Integer.parseInt(tmpM);//01
		int D = Integer.parseInt(tmpD);//01 처리가 가능한가?
		int Day = -1;
		for(int i=0;i<7;i++) {
			if(tmpDay.equals(DayToNum[i])) {
				Day = i;
			}
		}
		//2.다음날짜로 업데이트
		Day = (Day+1) % 7;
		
		int last_day = EndDate[M];//마지막 날짜 조회
		if(isLeapYear(today) && M == 2) {//현재 년도가 윤년이고, 현재 달이 2월일 때 마지막 날짜 1 증가
			last_day += 1;
		}
		
		if(D == last_day){//마지막 날짜일 때
			D = 1; M +=1;
		} else {//마지막 날짜 아닐 때
			D += 1;//조건 깜빡해버림.
		}
		if(M == 13) {
			M = 1; Y+=1;
		}
		
		//3.YYYYMMDDDAY로 만들어서 리턴
		String nextY = String.valueOf(Y);
		while(nextY.length()<4) {
			nextY = "0"+nextY;
		}
		String nextM = String.valueOf(M);
		while(nextM.length()<2) {
			nextM = "0"+nextM;
		}
		String nextD = String.valueOf(D);
		while(nextD.length()<2) {
			nextD = "0"+nextD;
		}
		String nextDay = DayToNum[Day];
		return nextY+nextM+nextD+nextDay;
	}
}
