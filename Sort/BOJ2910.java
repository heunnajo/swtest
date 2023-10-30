//빈도 정렬
import java.util.*;
import java.io.*;
public class BOJ2910 {
	static class Info{
		int num,order,freq;
		Info(int num,int order,int freq){
			this.num = num;
			this.order = order;
			this.freq = freq;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		HashMap<Integer,int[]> map = new HashMap<>();
		ArrayList<Info> list = new ArrayList<>();
		
		//0.HashMap완성, HashMap의 <K,S>을 리스트에 저장
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int x = Integer.parseInt(st.nextToken());
			if(map.containsKey(x)) {
				int[] val = map.get(x);
				val[1]++;
				map.put(x, val);
			} else {
				int[] arr = new int[2];
				arr[0] = i; arr[1] = 1;
				map.put(x, arr);
			}
		}
		for(Integer key : map.keySet()) {
			int[] val = map.get(key);
			list.add(new Info(key,val[0],val[1]));
//			System.out.print("key: "+key+"순서: "+val[0]+"빈도수: "+val[1]);
//			System.out.println();
		}
		
		//정렬 전 결과 확인
//		for(Info i:list) {
//			System.out.println(i.num+","+i.order+","+i.freq);
//		}
		//정렬 기준 정의
		Comparator comp = new Comparator<Info>() {
			@Override
			public int compare(Info a,Info b) {
				if(a.freq == b.freq) return a.order-b.order;//빈도수 동일한 경우 순서 기준 오름차순 정렬
				return b.freq-a.freq;//빈도수 기준 내림차순 정렬
			}
		};
		//정렬
		Collections.sort(list,comp);
		//정렬 후 결과 확인 : 정답으로 출력!
		StringBuilder sb = new StringBuilder(N);
		for(Info i:list) {
			//System.out.println(i.num+","+i.order+","+i.freq);
			for(int j=0;j<i.freq;j++) {
				sb.append(i.num).append(" ");
			}
		}
		
		sb.append("\n");
		System.out.println(sb.toString());
		
	}

}
