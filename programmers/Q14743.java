package cdTest;
import java.util.*;
//디스크 컨트롤러 : debugging...
public class Q14743 {
	int Ans;
	class Pair implements Comparable<Pair>{
		int arrivedT, jobT;
		Pair(int arrivedT, int jobT){
			this.arrivedT = arrivedT;
			this.jobT = jobT;
		}
		@Override
		public int compareTo(Pair p) {
			if(this.arrivedT == p.arrivedT) return this.jobT-p.jobT;
			return this.arrivedT - p.arrivedT;
		}
	}
	public static void main(String[] args) {
		
	}
	public int solution(int[][] jobs) {
		Ans = 0;
		//1.받은 2D배열을 요청 시간의 오름차순으로 정렬한다.
		Arrays.sort(jobs,(a,b)->a[0]-b[0]);
		
		//2.우선순위큐를 소요시간의 오름차순으로 정렬되도록 커스터마이징 한다.
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		//3.현재 작업중인 job의 소요시간을 end로 잡고 end안에 요청이 들어오는 job들만 우선순위큐로 넣는다.
		//end 전에 요청을하는 job이 없고 우선순위큐가 비어 있다면 하나를 넣는다.
		//4.매 턴 마다 우선순위 큐에서 작업을 하나 씩 빼서 수행할때마다 end를 업데이트 한다.
		//5.모든 작업이 완료되면 평균을 구한다.
		
        return Ans;
    }
}
