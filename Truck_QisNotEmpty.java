import java.util.*;
import java.io.*;
public class Truck_QisNotEmpty {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[] t = new int [n];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<n;i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{t[0],0});
		
		int weight = t[0];//현재 다리 위의 트럭 무게 합.
		int idx = 1,time = 1;//다음에 넣을 트럭 인덱스!
		while(!q.isEmpty()) {
			if(time-q.peek()[1] == w) {
				weight -= q.remove()[0];
			}
			if(idx<n && weight+t[idx]<=l) {//현재 트럭무게합 weight+ 다음트럭무게 <=l이면 넣는다! 
				q.add(new int[] {t[idx],time});
				weight += t[idx];
				idx++;
			}
			time++;//7은 1칸 이동한다.
		}
		System.out.println(time);
	}

}