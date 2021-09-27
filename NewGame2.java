package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class NewGame2 {
	static int ans,N,K,Map[][];
	static LinkedList<Integer>[][] HorseMap;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,1};
	
	static class Horse{
		int idx,x,y,dir;
		Horse(int idx,int x,int y,int dir){
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	static Horse[] Horses;
	static int HCnt;
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static int findIdx(LinkedList<Integer> list,int k) {
		int idx = -1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i) == k) idx = i;
		}
		return idx;
	}
	static int solve() {
		for(int t=1;t<=1000;t++) {
			
			for(int k=1;k<=K;k++) {
				Horse cur = Horses[k];
				LinkedList<Integer> curPos = HorseMap[cur.x][cur.y];
				int size = curPos.size();
				int nx = cur.x+dx[cur.dir],ny = cur.y+dy[cur.dir];
				
				//이동하려는 말 리스트 
				LinkedList<Integer> moveList = new LinkedList<>();
				int idx;
				if(size>1) {//이분탐색으로 k번 말 인덱스 찾는다.
					idx = findIdx(HorseMap[cur.x][cur.y],k);
					for(int i=idx;i<size;i++) {
						moveList.add(curPos.get(i));
					}
				} else {moveList.add(cur.idx);}//k번 말 하나밖에 없다면
				
				//1.색깔에 따른 추가 처리 
				//1-1.흰 칸:추가처리X
				//1-2.파란 칸  & 범위 초과 :방향 전환
				if(isOut(nx,ny) || Map[nx][ny] == 2) {
					cur.dir = (cur.dir+2)%4;
					int nx2=0,ny2=0;
					//방향전환해서 이동하려는 칸이 파란칸이거나 다시 범위초과이면 이동X:nx,ny 그대로.
					//방향전환해서 이동하려는 칸이 다시 범위초과일 일은 없기 때문에 범위초과 체크삭제.
					if(Map[nx+dx[cur.dir]][ny+dy[cur.dir]]==2) {
						
					}
					//int nx2 = nx+dx[cur.dir],ny2 = ny+dy[cur.dir];
				}
				//1-3.빨간 칸:리스트 뒤집는다 
				if(Map[nx][ny] == 1) {
					Collections.reverse(moveList);
				} 
				
				//2.이동:옮기는 로직은 똑같음.
				if(HorseMap[nx][ny] == null) {
					HorseMap[nx][ny] = moveList;
				} else {
					HorseMap[nx][ny].addAll(moveList);
				}
				//현재 (cur.x,cur.y)위치의 컬렉션 업데이트 
				for(int i=idx;i<size;i++) {
					HorseMap[cur.x][cur.y].remove(i);
				}
				cur.x = nx;cur.y = ny;
				
				//게임 종료 조건
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(HorseMap[i][j].size() >=4) return t;
					}
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		Horses = new Horse[K+1];
		HCnt = 1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				HorseMap[i][j] = new LinkedList<>();
			}
		}
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			if(dir==1) dir = 2;
			else if(dir==2) dir = 1;
			
			Horses[HCnt++] = new Horse(i,x,y,dir);
		}
		
		System.out.println(solve());
	}

}
//for(int i=1;i<=K;i++) {
//	Horse cur = Horses[i];
//	System.out.println(cur.idx+" "+"("+cur.x+","+cur.y+" "+cur.dir+")");
//}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+ " ");
//	}
//	System.out.println();
//}