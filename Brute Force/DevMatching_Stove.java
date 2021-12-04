package ss;
import java.util.*;
import java.io.*;
public class DevMatching_Stove {
	static int n;
	static String[] recipes,orders;
	static int[] stove;
	static class Dish{
		String name;
		int cookingTime;
		Dish(String name,int cookingTime){
			this.name = name;
			this.cookingTime = cookingTime;
		}
	}
	static HashMap<String,Integer> Dishes;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		//n = Integer.parseInt(st.nextToken());
		n = 2;
		stove = new int[n];
		Dishes = new HashMap<>();
		recipes = new String[2];
//		recipes = {"A 2","B 2"};
		recipes[0] = "A 2";
		recipes[1] = "B 3";
		orders = new String[3];
		orders[0] = "A 1"; orders[1] = "A 2"; orders[2] = "B 3";
		//요리별로 조리시간 클래스, 배열에 저장?
		for(int i=0;i<recipes.length;i++) {
			String cur = recipes[i];
			int idx = cur.indexOf(' ');
			String name = cur.substring(0,idx);
			int time = Integer.parseInt(cur.substring(idx+1,cur.length()));
			Dishes.put(name, time);
		}
		//주문서도 주문 하나하나 조회, 문자열 파싱
		int ans = 0;
		for(int i=0;i<orders.length;i++) {
			String cur = orders[i];
			int idx = cur.indexOf(' ');
			String name = cur.substring(0,idx);
			int orderTime = Integer.parseInt(cur.substring(idx+1,cur.length()));
			boolean flag = false;
			for(int j=0;j<n;j++) {
				if(stove[i]==orderTime) {
					flag = true;
					stove[i] = stove[i] + Dishes.get(name);
					ans = stove[i];
					break;
				}
			}
			if(!flag) {
				Arrays.sort(stove);
				int minTime = stove[0];
				for(int j=0;j<n;j++) {
					if(stove[i] == minTime) {
						stove[i] = stove[i] + Dishes.get(name);
						ans = stove[i];
						break;
					}
				}
			}
		}
		System.out.println(ans);
	}

}
