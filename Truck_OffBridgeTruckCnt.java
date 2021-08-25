import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Truck_OffBridgeTruckCnt {
	static int N,W,L,Truck[];
	static class Info{
		int t, w;//idx번째 트럭이 승차하는 시간, 그 트럭의 무게
		Info(int t,int w){
			this.t = t;
			this.w = w;
		}
	}
	static int solve() {
		//초기 셋팅 :0번째 트럭이 다리 위에 올라간다!
		int time = 0;
		int weight = 0;//다리 위의 트럭들 무게 총합 
		int count = 0;
		int idx = 0;
		Queue<Info> q = new LinkedList<>();
		//q.add(new Info(0,0));
		//int idx = 1;//다음으로 탑승할 트럭의 인덱스는 =>0번째 트럭 올라간 후 1부터.
		
		//다리를 건넌 트럭 객수를 카운팅, 트럭의 총 갯수만큼 반복!
		while(count<N) {
			time++;
			//1.트럭 하차
			while(!q.isEmpty() && time == q.peek().t+W) {
				weight -= q.remove().w;
				count++;
			}
			//2.트럭 승차!
			if(idx<N && weight+Truck[idx]<= L) {
				q.add(new Info(time,Truck[idx]));
				weight += Truck[idx];
				idx++;
			}
		}
		
		return time;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);//다리 건너는 트럭 수 
		W = Integer.parseInt(input[1]);//다리 길이 
		L = Integer.parseInt(input[2]);//다리 최대 하중 
		
		Truck = new int[N];
		
		input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			Truck[i] = Integer.parseInt(input[i]);
		}
		System.out.println(solve());
		br.close();
	}

}

//for(int i=0;i<N;i++) {
//	System.out.print(Truck[i]+" ");
//}