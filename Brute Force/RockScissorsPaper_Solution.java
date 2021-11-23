import java.util.Scanner;

public class RockScissorsPaper_Solution {
	static int N, M; // N : 손가락 가지수, M : 목표 승수
	static int pick[][];  // 각 플레이어의 차례별로 낼 모양을 저장한 배열 
	static int arr[][];  // 각 손가락 모양별 상성을 저장할 배열 
	private static boolean result = false;

	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        
        pick = new int[3][21];  // 3명의 사람의 20경기 정보를 가짐
        arr = new int[12][12];   // 낼 수 있는 손동작의 수를 12개로 본것 같음 
        for(int i = 1; i <= N; i++) {
        		for(int j = 1; j <= N; j++) {
        			arr[i][j] = sc.nextInt();
        		}
        }
        
        for(int i = 0; i < 20; i++) { // 경희
        	 	pick[1][i] = sc.nextInt();
        }
        
        for(int i = 0; i < 20; i++) { // 민호 
	    	 	pick[2][i] = sc.nextInt();
	    }
        	
        int winCount[] = new int[3];
        int turnCount[] = new int[3];
        boolean used[] = new boolean[11];
        
        solution(0,1,winCount,turnCount,used);
        
        if(result ) System.out.println(1);
        else System.out.println(0);
        
        sc.close();
    }

	// dfs 깊이우선탐색 
	private static void solution(int user1, int user2, int[] winCount, int[] turnCount, boolean[] used) {
		// user1과 user2는 각각 유저번호를 의미
		// winCount 는 해당 상태에서 각 플레이어의 승수 
		// winCount[0] : 지우의 승수, winCount[1] : 경희의 승수 , winCount[2] : 민호의 승수 
		// turnCount 는 해당 상황에서 각 플레이어가 몇번째 차례를 맞이했는지
		// turnCount[0] : 지우의 차례순서, turnCount[1] : 경희의 차례순서 , turnCount[2] : 민호의 차례순서
		// userd는 모든 손가락 가지수에 대해 지우가 사용했는지 안했는지를 저장
		
		if(winCount[0] == M) { // 지우가 먼저 목표 승수에 도달 
			result = true;
			return; // wow 
		}
		
		else if(winCount[1] == M | winCount[2] == M) {
			return;
		}
		
		int userNext = 3-user1-user2;
		// 이번 게임이 끝나고 다음 상대로 만날 사람을 저장
		// 지우 : 0  , 경희 : 1 , 민호 : 2
		
		if(user1 == 0) { // user1이 지우라면 
			for(int i = 1; i <= N; i++) {
				if(!used[i]) { // 사용하지 않은 손가락
					int winner = rsp(i, pick[user2][turnCount[user2]], 0, user2);
					winCount[winner]++;
					turnCount[user2]++;
					used[i] = true;
					// 냈다는 가정하에 다음 경기 진행이라고 함 
					solution(winner, userNext, winCount, turnCount, used); 
					winCount[winner]--;
					turnCount[user2]--;	
					used[i] = false;
					// 다시 안낸 상태로 복귀한다는데 
					// 돌아올 경우 다른 손가락 가지수를 찾아서 실행하게 된다는데... 
				}
			}
			
		}
		else if(user2 == 0) { // user2이 지우라면 같은 방식 
			for(int i = 1; i <= N; i++) {
				if(!used[i]) { 
					int winner = rsp(pick[user1][turnCount[user1]], i, user1, 0);
					winCount[winner]++;
					turnCount[user1]++;
					used[i] = true;
					solution(winner, userNext, winCount, turnCount, used); 
					winCount[winner]--;
					turnCount[user1]--;	
					used[i] = false;
					
				}
			}
			
		}
		else { // user1 과 user2 모두 지우가 아니라면 
			int winner = rsp(pick[user1][turnCount[user1]], pick[user2][turnCount[user2]], user1, user2);
			winCount[winner]++;
			turnCount[user1]++;
			turnCount[user2]++;
			solution(winner, userNext, winCount, turnCount, used);
			// 이 함수도 끝에 도달하면 돌아오기 때문에 아래와 같이 원상태로 돌려줘야 한다. 
			winCount[winner]--;
			turnCount[user1]--;
			turnCount[user2]--;
		}
	}

	// 승자 리턴 함수
	private static int rsp(int pick1, int pick2, int user1, int user2) {
		// pick1 : user1의 손가락 번호 
		// pick2 : user2의 손가락 번호 
		// k : user1 의 번호 (지우)
		
		if(pick1 != pick2) {
			if(arr[pick1][pick2] == 2) {
				return user1;
			}else if(arr[pick1][pick2] == 0) {
				return user2;
			}
		}else {
			return Math.max(user1, user2); // 무조건 뒷 사람이 유리보쓰 
		}
		return -1;
	}

}