//컵라면
import java.util.PriorityQueue;

public class BOJ1781_Solution1 {
	static int n;
	static PriorityQueue<Problem> pq = new PriorityQueue<>();
	static PriorityQueue<Integer> solved = new PriorityQueue<>();
	static int time = 1;
	static int max = 0;
	
	static public class Problem implements Comparable<Problem> {
		int deadline, ramen;
		public Problem(int deadline, int ramen) {
			this.deadline = deadline;
			this.ramen = ramen;
		}
		
		@Override
		public int compareTo(Problem o) {
			if(this.deadline == o.deadline)
				return this.ramen - o.ramen;//이건 라면갯수 오름차순 정렬하는 건데... 오름차순이 아니라 내림차순인데 왜....!!!!!!!!!!!!!!?????
			else
				return this.deadline - o.deadline;
		}
	}
	
	public static void main(String[] args) throws Exception {
		n = read();
		
		for(int i=0; i<n; i++)
			pq.add(new Problem(read(), read()));
		
		while(!pq.isEmpty()) {
			Problem temp = pq.poll();//현재 문제 temp
			
			if(time <= temp.deadline) {//현재 문제 temp 풀 수 있는 경우
				solved.add(temp.ramen);
				max += temp.ramen;
				time++;
			} else if(!solved.isEmpty() && solved.peek() <= temp.ramen) {//현재 문제 temp 풀지 못하는 경우 : 이미 푼 문제더라도 현재 문제의 라면 갯수가 더 많으면 기존 푼 문제 제거하고 현재 문제 풀도록 한다!
				max -= solved.poll();
				max += temp.ramen;
				solved.add(temp.ramen);
			}
		}
		
		System.out.print(max);
	}
    private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	    	n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
}