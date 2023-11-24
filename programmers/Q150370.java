//Q150370 개인정보 수집 유효기간
import java.util.*;
class Solution {
    static int[] solution(String today, String[] terms, String[] privacies) {
      HashMap<String,Integer> map = new HashMap<>();
      ArrayList<Integer> list = new ArrayList<>();
      int intToday = getDate(today);
      // System.out.println("intToday: "+intToday);
      for(int i=0;i<terms.length;i++) {
    	  String[] input = terms[i].split(" ");
    	  String type = input[0];
    	  int validD = Integer.parseInt(input[1]) * 28;
    	  map.put(type, validD);
      }
      for(int i=0;i<privacies.length;i++) {
    	  String[] input = privacies[i].split(" ");
    	  // System.out.println("type: "+input[1]+",date: "+map.get(input[1]));
    	  int validD = getDate(input[0]) + map.get(input[1]);
    	  // System.out.println("validD: "+validD);
    	  if(validD <= intToday) list.add(i+1);
      }
      int[] answer = new int[list.size()];
      for(int i=0;i<list.size();i++) {
    	  answer[i] = list.get(i);
      }
      return answer;
  }
  static private int getDate(String str){
      //YYYY.MM.DD 포맷의 데이터를 int로 변환 후 리턴
      //System.out.println("str: "+str);
	  //if(!str.equals("2021.07.01")) return -1;
      String[] input = str.split("\\.");
      // System.out.println("input.length: "+input.length);
      String Y = input[0];
      String M = input[1];
      String D = input[2];
      // System.out.println("Y: "+Y+"M: "+M+"D: "+D);
      int intY = Integer.parseInt(Y);
      int intM = Integer.parseInt(M);
      int intD = Integer.parseInt(D);
      // System.out.println("intY: "+intY+"intM: "+intM+"intD: "+intD);
      int date = intY * 12 * 28 + intM * 28 + intD;
      // System.out.println("date: "+date);
      return date;
  }
}