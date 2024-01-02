//현대모비스 알고리즘 대회
//Q1.상담원 인원 : debugging...
import java.util.*;
public class Q214288 {
	static int N,K;
	static int[][] Reqs;
	static List<List<Integer>> Orders;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		K = 3;
		N = 5;
		Reqs = new int[][]{{10,60,1},{15,100,3},{20,30,1},{30,50,3},{50,40,1},{60,30,2},{65,30,1},{70,100,2}};
		solution(K,N,Reqs);
	}
	public static int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;
        //1.완전 탐색
        Orders = new ArrayList<>(); // 분배가능한 모든 경우의 수
        Integer[] area = new Integer[k+1];
        Arrays.fill(area,1);
        go(n-k,1,new ArrayList<Integer>(Arrays.asList(area)));
        
        for(List<Integer> order : Orders){
            answer = Math.min(answer,simulation(k,order,reqs));
        }
        //2.각 케이스마다 시뮬레이션
        int cur = simulation();
        if(answer > cur) answer = cur;
        
        System.out.println("answer: "+answer);
        return answer;
    }
	static void go(int remain,int idx,List<Integer> area) {
		// 모든 인원을 담았으면 orders에 추가
        if(remain <= 0){
            Orders.add(area);
            return;
        }
        
        for(int i = idx; i < area.size(); i++){
            area.set(i,area.get(i)+1);
            go(remain-1,i,new ArrayList<Integer>(area));
            area.set(i,area.get(i)-1);
        }
	}
	static int simulation(int k, List<Integer> order, int[][] reqs) {
		int res = 0;
		PriorityQueue<Integer> pq[] = new PriorityQueue[K+1];
		for(int i=1;i<=K;i++) {
			pq[i] = new PriorityQueue<>();
			//이 부분 빼먹어서 틀림 : 우선순위큐는 한번만 생성하면 된다고 생각했는데 아님.
			for(int j=0;j<order.get(i);i++) pq[i].add(0);
		}
		
		for(int[] req:Reqs) {
			int arrive = req[0];
			int time = req[1];
			int type = req[2];
			
			int mentor = pq[type].poll();
			
			if(mentor < arrive) {
				pq[type].add(arrive+time);
				
			} else if(mentor > arrive) {
				res += (mentor-arrive);
				pq[type].add(mentor+time);
			} else {
				pq[type].add(mentor+time);
			}
		}
		
		return res;
	}

}
