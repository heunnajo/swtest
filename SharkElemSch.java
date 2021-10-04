import java.io.*;
import java.util.*;
public class Main
{
    static int ans,N;
    static class Student{
        int x,y,flist[];
        Student(int x,int y,int[] flist){
            this.x = x;
            this.y = y;
            this.flist = flist;
        }
    }
    static int[][] classroom,EmptySeat;
    static Map<Integer,Student> map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
	    
	    N = Integer.parseInt(br.readLine());
	    classroom = new int[N][N];
	    EmptySeat = new int[N][N];
	    map = new HashMap<>();
	    
	    fillEmptySeat();
	    
	    for(int i=0;i<N*N;i++){
	        st = new StringTokenizer(br.readLine());
//	        int num = Integer.parseInt(st.nextToken())-1;
//	        int s1 = Integer.parseInt(st.nextToken())-1;
//	        int s2 = Integer.parseInt(st.nextToken())-1;
//	        int s3 = Integer.parseInt(st.nextToken())-1;
//	        int s4 = Integer.parseInt(st.nextToken())-1;
	        int num = Integer.parseInt(st.nextToken());
	        int s1 = Integer.parseInt(st.nextToken());
	        int s2 = Integer.parseInt(st.nextToken());
	        int s3 = Integer.parseInt(st.nextToken());
	        int s4 = Integer.parseInt(st.nextToken());
	        
	        findSeat(num,new int [] {s1,s2,s3,s4});
	    }
	    //N*N명 좌석 선택한 후 만족도 계산:map을 이용하여 학생 위치정보, flist와의 인접거리 구해서 인접한 친구 명수 구할 수 있다.
	    int cnt;
	    ans = 0;
//	    for(int i=0;i<N*N;i++) {
    	for(int i=1;i<=N*N;i++) {
	    	cnt = 0;
	    	Student s = map.get(i);//일부러 1씩 빼고 0부터 인덱스 시작했는데 답이 틀리게 나오는 이유는 뭐지..
	    	int[] flist = s.flist;
	    	for(int frNum:flist) {//i번째 학생의 N명의 친구들 중 인접거리 만족하는 친구 몇명인지
	    		Student friend = map.get(frNum);
	    		if(Math.abs(s.x-friend.x)+Math.abs(s.y-friend.y) == 1) cnt++;
	    	}
	    	//if(cnt == 0) ans += 0;
	    	if(cnt == 1) ans += 1;
	    	else if(cnt == 2) ans += 10;
	    	else if(cnt == 3) ans += 100;
	    	else if(cnt == 4) ans += 1000;
	    }
	    System.out.println(ans);
	}
	static void findSeat(int num,int[] flist) {//학생 번호 num, 그 학생이 좋아하는 학생 수
		//1.좋아하는 학생 위치 기준으로 상하좌우 인접한 위치 점수 1씩 증가시킨다!
		int[][] nearScore = new int[N][N];
		for(int f:flist) {
			if(map.containsKey(f)) {//자리에 착석해있다면 그 상하좌우 인접 칸 nearScore 배열값 1씩 증가!
				Student s = map.get(f);
				
				for(int d=0;d<4;d++) {
					int nx = s.x+dx[d]; int ny = s.y+dy[d];
					//범위 벗어나거나 이미 착석해있는 경우 continue 처리
					if(isOut(nx,ny) || classroom[nx][ny] != 0) continue;
					//그렇지 않다면 (nx,ny) 점수 증가!
					nearScore[nx][ny]++;
				}
			}
		}
		//2.1번 조건 만족 칸 많다면 빈칸 갯수도 고려해서 좌석 선택
		int maxLike = -1; int maxEmpty = -1;//최댓값인 칸 선택 위해 0보다도 작은 -1로 셋팅
		int choiceX = -1; int choiceY = -1;//최종선택할 x,y 좌표
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(classroom[i][j] != 0) continue;
				if(maxLike<nearScore[i][j]) {
					choiceX = i;choiceY = j;
					maxLike = nearScore[i][j];
					maxEmpty = EmptySeat[i][j];//추후에 좋아하는 학생 수 같으면 빈칸 갯수로 비교해야하기 때문에!
				} else if(maxLike == nearScore[i][j] && maxEmpty < EmptySeat[i][j]) {
					maxEmpty = EmptySeat[i][j];
					choiceX = i;choiceY = j;
				}
			}
		}
		//3.좌석 선택
		classroom[choiceX][choiceY] = num;
		map.put(num, new Student(choiceX,choiceY,flist));
		//4.좌석 선택에 따른 빈칸 갯수 1감소 업데이트!:선택한 위치 상하좌우 인접 칸 빈칸 갯수 1씩 감소.
		for(int d=0;d<4;d++) {
			int nx = choiceX+dx[d];
			int ny = choiceY+dy[d];
			
			if(isOut(nx,ny) || classroom[nx][ny]!= 0) continue;//classsroom[nx][ny]값이 0이면 감소시킨다?!
			EmptySeat[nx][ny]--;
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static void fillEmptySeat() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				EmptySeat[i][j] = 4;
				if(i==0 || i==N-1) EmptySeat[i][j]--;
				if(j==0 || j==N-1) EmptySeat[i][j]--;
			}
		}
	}
}