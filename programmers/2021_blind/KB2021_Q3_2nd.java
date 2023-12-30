import java.util.*;
import java.io.*;
//Q72412 순위 검색
public class KB2021_Q3_2nd {
	static String[] input;
	static boolean[] selected;
	static HashMap<String,ArrayList<Integer>> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		
		solution(info,query);
	}
	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		map = new HashMap<>();
		selected = new boolean[4];
		//info로 map 완성
		for(int i=0;i<info.length;i++) {
			input = info[i].split(" ");
			makeCombi(0);
		}
		for (String key : map.keySet())
            Collections.sort(map.get(key));
		
		//query 파싱
		for(int i=0;i<query.length;i++) {
			String[] tmp = query[i].split(" and ");
			String[] data = new String[4];
			
			for(int j=0;j<3;j++) data[j] = tmp[j];
			
			String[] tmp2 = tmp[3].split(" ");
			data[3] = tmp2[0];
			
			int score = Integer.parseInt(tmp2[1]);
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<4;j++) sb.append(data[j]);
			String condition = sb.toString();
			//System.out.println("condition: "+condition);
			
			if(map.containsKey(condition)) {
				ArrayList<Integer> list = map.get(condition);
				//Collections.sort(list);
				answer[i] = binarySearch(list,score);
			} else {
				answer[i] = 0;
			}
		}
        return answer;
    }
	static int binarySearch(ArrayList<Integer> list,int score) {
		int n = list.size();
		int s = 0; int e = n-1;
		while(s <= e) {
			int mid = (s+e)/2;
			if(list.get(mid) < score) {
				s = mid+1;
			}else {
				e = mid-1;
			}
		}
		return n-s;
	}
	public static void makeCombi(int idx) {
		//1.재귀 종료
		if(idx == 4) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<4;i++) {
				if(selected[i]) {sb.append(input[i]);}
				else {sb.append("-");}
			}
			String cur = sb.toString();
			//System.out.println("cur: "+cur);
			//현재 조합 cur로 map 완성
			
			if(!map.containsKey(cur)) {
				ArrayList<Integer> list = new ArrayList<>();
				map.put(cur, list);
			}
			map.get(cur).add(Integer.parseInt(input[4]));
			return;
		}
		//2.현재 경우 선택, 다음 경우 호출
		selected[idx] = true;
		makeCombi(idx+1);
		selected[idx] = false;
		makeCombi(idx+1);
	}
}
//for(String key:map.keySet()) {
//	System.out.print("조건: "+key+": ");
//	for(int x:map.get(key)) {
//		System.out.print(x+", ");
//	}
//	System.out.println();
//}
