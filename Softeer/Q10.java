//회의실 배정(오답)
import java.util.*;
import java.io.*;
public class MeetingroomReservation {
	static int N,M;//N:회의실 수, M:회의 수
	static ArrayList<String> roomNames;
	static boolean[][] table;
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        roomNames = new ArrayList<>();
        table = new boolean[N][9];
        
        for(int i=0;i<N;i++) {
        	roomNames.add(br.readLine());//뭔가 여기에 문제가 있는 건가?
        }
        
        Collections.sort(roomNames);
//        System.out.println("roomNames 크기: "+roomNames.size());
//        for(String room:roomNames) System.out.println(room);
        
        //회의 기록:table 완성:확인 O
        for(int i=0;i<M;i++) {//M줄의 입력을 처리하는 부분
        	String[] input = br.readLine().split(" ");
        	
        	int start = Integer.parseInt(input[1])-9;
        	int end = Integer.parseInt(input[2])-9;
        	
        	for(int j=start;j<end;j++) {
        		int tableIdx = roomNames.indexOf(input[0]);
        		table[tableIdx][j] = true;
        	}
        }
        //정답 도출 :N개의 회의실 각각에 대해 가능한 시간대를 출력한다:StringBuilder에 이어 붙임!
        int totalCnt;
        StringBuilder sb = new StringBuilder();
        StringBuilder availableTime;
        for(int i=0;i<N;i++) {
        	availableTime = new StringBuilder();
        	int start = 0,cnt = 0;
        	totalCnt = 0;//start:시작시간,cnt:시간대 갯수
        	boolean flag = false;
        	sb.append("Room ").append(roomNames.get(i)).append(":").append("\n");
        	
        	for(int j=0;j<9;j++) {
        		if(!table[i][j]) {
        			flag = true;
        			if(start == 0) {//처음 만나는 false에서 그 때의 j를 시작 시간으로 기록!
        				start = j;//0
        				totalCnt++;
        				System.out.println("start: "+start);
        			}
        			cnt++;
    			}
        		else if(table[i][j]) {
        			if(flag) {//유효한 경우
        			start = start+9; int end = start+cnt;//start = 0+9가 되야하는데
        			if(start == 9) availableTime.append("0");
        			availableTime.append(start).append("-").append(end).append("\n");
        			start = 0; cnt = 0;
        			}
        		}
        	}
        	//가능한 시간 없을 경우:totalCnt로 판단 가능:totalCnt==0
        	if(!flag) sb.append("Not available").append("\n");
        	else {
        		sb.append(totalCnt).append(" ").append("available:").append("\n");
        		sb.append(availableTime);
        	}
        	if(i!=N-1) sb.append("-----").append("\n");
        }
        System.out.print(sb);
	}

}

//        for(String room:roomNames) {
//        	System.out.println("room: "+room);
//        }

//table 확인
//        for(int i=0;i<N;i++) {
//        	for(int j=0;j<9;j++) {
//        		System.out.print(table[i][j]+" ");
//        	}
//        	System.out.println();
//        }
