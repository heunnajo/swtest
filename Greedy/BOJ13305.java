package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//주유소
public class BOJ13305 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dist = new int[N-1];
        int[] cost = new int[N];
        
        for(int i=0;i<N-1;i++) {
        	dist[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
        	cost[i] = Integer.parseInt(st.nextToken());
        }
        
        //N번째 도시를 제외하고, 1->N-1 까지 거리*기름가격 더해주면 된다!
        long sum = 0;
        long min = cost[0];//1번 도시에서는 무조건 주유해야하기 때문에 cost[1-1]로 초기화
        
//        long min = Integer.MAX_VALUE;
        for(int i=0;i<N-1;i++) {
        	if(min > cost[i]) {
        		min = cost[i];
        	}
        	//최소 가격 min으로 값 더함.
        	sum += min * dist[i];
        }
        
        System.out.println(sum);
	}

}
