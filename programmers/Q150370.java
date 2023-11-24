//Q150370 개인정보 수집 유효기간
import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("today: "+today);
        int intToday = getDate(today);
        System.out.println("intToday: "+intToday);
        int[] answer = new int[1];
        return answer;
    }
    private int getDate(String str){
        //YYYY.MM.DD 포맷의 데이터를 int로 변환 후 리턴
        System.out.println("여긴 들어오니?");
        String[] input = str.split(".");
        String Y = input[0];
        String M = input[1];
        String D = input[2];
        System.out.println("Y: "+Y+"M: "+M+"D: "+D);
        int intY = Integer.parseInt(Y);
        int intM = Integer.parseInt(M);
        int intD = Integer.parseInt(D);
        System.out.println("intY: "+intY+"intM: "+intM+"intD: "+intD);
        int date = intY * 12 * 28 + intM * 28 + intD;
        System.out.println("date: "+date);
        return date;
    }
}