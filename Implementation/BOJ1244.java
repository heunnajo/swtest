package Implementation;
import java.util.*;
import java.io.*;
//스위치 켜고 끄기
public class BOJ1244 {
	static class Info{
		int gen,num;
		
		Info(int gen,int num){
			this.gen = gen;
			this.num = num;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int bulbCnt, stuCnt,k;//k는 여학생일 경우 범위늘이는 변량
		int[] bulbs;
		Info[] infos;
		
		bulbCnt = Integer.parseInt(br.readLine());
		bulbs = new int[bulbCnt+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=bulbCnt;i++) {
			bulbs[i] = Integer.parseInt(st.nextToken());
		}
		
		stuCnt = Integer.parseInt(br.readLine());
		infos = new Info[stuCnt+1];
		
		for(int i=1;i<=stuCnt;i++) {
			st = new StringTokenizer(br.readLine());
			
			int gen = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			infos[i] = new Info(gen,num);
		}
		
		//로직 수행!
		for(int i=1;i<=stuCnt;i++) {
			Info cur = infos[i];
			if(cur.gen == 1) {
//				for(int j=1;j<=bulbCnt;j++) {
//					if(j % cur.num == 0) bulbs[j] = 1-bulbs[j];
//				}
				for (int j = cur.num; j <= bulbCnt; j += cur.num) {
					bulbs[j] = 1-bulbs[j];
				}
			} else {
				k = 0;
				while(cur.num-k>=1 && cur.num+k<=bulbCnt) {
					if(bulbs[cur.num-k] == bulbs[cur.num+k]) k++;
				}
				k -= 1;
				//if(k == -1) continue;
				for(int j=cur.num-k;j<=cur.num+k;j++) {
					bulbs[j] = 1-bulbs[j];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=bulbCnt;i++) {
			sb.append(bulbs[i]).append(" ");
			if(i % 20 == 0) sb.append("\n");
		}
		System.out.print(sb);
	}

}
