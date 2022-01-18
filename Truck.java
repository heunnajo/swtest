import java.util.*;
import java.io.*;
public class Truck {
	static class Pair{
		int time,weight;
		Pair(int time,int weight){
			this.time = time;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws Exception {
		//입력값 2개 밖에 없어서 스캐너 써도 될 것 같지만 br 습관화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		//n개의 트럭 입력받는다!
		int[] truck = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		int totalOut = 0;//다리에서 나온 트럭 갯수
		int curTime = 0;//다리에서 나온 트럭 갯수
		int i=0;//다리 위에 올라갈 트럭의 인덱스
		Queue<Pair> q = new LinkedList<>();
		while(totalOut<n) {
			curTime++;
			while(!q.isEmpty() && q.peek().time+w == curTime) {
				q.poll();
				totalOut++;
			}
			int sum = 0;//현재 다리 위 트럭 무게의 합
			for(Pair p:q) sum += p.weight;
			if(i<n && sum + truck[i]<= L) {
				q.add(new Pair(curTime,truck[i]));
				i++;
			}
		}
		
		System.out.println(curTime);
	}

}