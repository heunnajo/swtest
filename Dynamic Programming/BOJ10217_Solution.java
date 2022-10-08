import java.util.*;
import java.io.*;
//KCM Travel
//DP + Dijkstra
class BOJ10217_Solution {
    static class Ticket {
        private int to;
        private int cost;
        private int time;
        Ticket(int to , int cost , int time){
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    } 
    static class Airport implements Comparable<Airport>{
        private int airport_num;
        private int min_time;
        Airport(int airport_num , int min_time){
            this.airport_num = airport_num;
            this.min_time = min_time;
        }
        @Override
        public int compareTo(Airport A) {
            return this.min_time - A.min_time;
        }
    } 
    static int[][] DP;
    static int num_of_airport,M;
    static int INF = 0x7777777;
    static ArrayList<Ticket>[] airplain;
	public static void main (String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    int Test_Case = Integer.parseInt(br.readLine());
	    for(int i = 0; i < Test_Case ; i++){
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        num_of_airport = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        int num_of_Ticket = Integer.parseInt(st.nextToken());
	        DP = new int[num_of_airport+1][M+1];//least time take for airport 1 to airport N under cost M
	        for(int j = 2 ; j <= num_of_airport ; j++){
	            Arrays.fill(DP[j],INF);
	        }
	        airplain = new ArrayList[num_of_airport+1];
	        for(int j = 0 ; j <= num_of_airport ; j++){
	            airplain[j] = new ArrayList<Ticket>();
	        }
	        for(int j = 0 ; j < num_of_Ticket ; j++){
	            st = new StringTokenizer(br.readLine()," ");
	            int from = Integer.parseInt(st.nextToken());
	            int to = Integer.parseInt(st.nextToken());
	            int cost = Integer.parseInt(st.nextToken());
	            int time = Integer.parseInt(st.nextToken());
	            airplain[from].add(new Ticket(to,cost,time));
	        }
	        KCM_DP();
	        int result = INF;
	        for(int k = 0 ; k <= M ; k++){
	            if(result > DP[num_of_airport][k]){
	                result = DP[num_of_airport][k];
	            }
	            if(k==M && DP[num_of_airport][k] == INF){
	                bw.write("Poor KCM\n");
	            } else if(k==M && DP[num_of_airport][k] != INF){
	                bw.write(String.valueOf(result) + "\n");    
	            }
	        }
	    }
	    bw.flush();
	    bw.close();
	}
	static void KCM_DP(){
	    PriorityQueue<Airport> queue = new PriorityQueue<>();
	    int[] visit = new int[num_of_airport+1];
	    queue.add(new Airport(1 , 0));
	    while(!queue.isEmpty()){
	        Airport airport = queue.poll();
	        if(airport.airport_num==num_of_airport) return;
	        if(visit[airport.airport_num]==1) continue;
	        visit[airport.airport_num] = 1;
	        for(Ticket temp : airplain[airport.airport_num]){
	            if(temp.cost > M) continue; 
	            for(int k = 0 ; k <= M - temp.cost ; k++){
	                if(DP[airport.airport_num][k]!=INF){
	                    if(DP[temp.to][k+temp.cost] > DP[airport.airport_num][k] + temp.time){ 
    	                    DP[temp.to][k+temp.cost] = DP[airport.airport_num][k] + temp.time;
	                        queue.add(new Airport(temp.to , DP[temp.to][k+temp.cost]));
                        }
	                }
	            }
                
	        }
	    }
	}
}