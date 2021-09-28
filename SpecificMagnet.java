package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SpecificMagnet {
	static int ans,T,K;
	static int[][] Magnet = new int[4][8];
	static class rotateInfo{
		int idx,dir;
		
		rotateInfo(int idx,int dir){
			this.idx = idx;
			this.dir = dir;
		}
	}
	static rotateInfo[] Info;
	static int infoCnt;
	static void rotate(int idx,int rotDir) {
		if(rotDir == 1) {//시계방향 회전!
			int tmp = Magnet[idx][7];
			for(int i=7;i>=1;i--) {
				Magnet[idx][i] = Magnet[idx][i-1];
			}
			Magnet[idx][0] = tmp;
		}
		else if(rotDir == 0) {//시계방향 회전!
			int tmp = Magnet[idx][0];
			for(int i=0;i<7;i++) {
				Magnet[idx][i] = Magnet[idx][i+1];
			}
			Magnet[idx][7] = tmp;
		}
		return;
	}
	static void go(int idx,int dir,int rotDir) {
		if(idx<0 || idx>3) return;
		//초기 실행:어디서 왔는지 방향dir이 없으므로, 현재 idx 톱니 회전하고, idx-1 또는 idx+1 회전.
		if(dir==2) {
			if(idx-1>=0) {go(idx-1,0,-rotDir);}
			if(idx+1<4) {go(idx+1,1,-rotDir);}
			//현재 idx톱니 회전
			rotate(idx,rotDir);
		}
		//idx-1,idx+1 조건 판단 후 먼저 회전
		if(dir == 0){//현재톱니=왼쪽톱니 .오른쪽 재귀X 왼쪽으로만진행!
			if(idx-1>=0 && Magnet[idx][6] != Magnet[idx-1][2]) {//바로 idx-1에 접근하면 안됨.초기실행과 재귀실행 구분?
				go(idx-1,0,-rotDir);//현재 회전방향과 반대
			}
		} 
		else if(dir==1){//현재톱니=오른쪽톱니.왼쪽재귀X 오른쪽으로만진행!
			if(idx+1<4 && Magnet[idx][2] != Magnet[idx+1][6]) {//바로 idx+1에 접근하면 안됨.초기실행과 재귀실행 구분?
				go(idx+1,1,-rotDir);//현재 회전방향과 반대
			}
		}
		//idx 회전
		rotate(idx,rotDir);
	}
	static void cal() {
		//순서대로 S극이면 1,2,4,8
		if(Magnet[0][0]==1) {ans+=1;}
		if(Magnet[1][0]==1) {ans+=2;}
		if(Magnet[2][0]==1) {ans+=4;}
		if(Magnet[3][0]==1) {ans+=8;}
		return;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			K = Integer.parseInt(br.readLine());
			Info = new rotateInfo[K];
			infoCnt = 0;
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					Magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken())-1;
				rotateInfo ri = new rotateInfo(idx,dir);
				Info[infoCnt++] = ri;
			}
			ans = 0;
			for(int i=0;i<K;i++) {
				rotateInfo ri = Info[i];
				System.out.println(i+"번 회전하기 전:");
				printMagnet();
				
				go(ri.idx,2,ri.dir);
				
				System.out.println(i+"번 회전하기 후:");
				printMagnet();
			}
			cal();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void printMagnet() {
		for(int i=0;i<4;i++) {
			System.out.print("톱니바퀴"+i+" ");
			for(int j=0;j<8;j++) {
				System.out.print(Magnet[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
//for(int i=0;i<4;i++) {
//	for(int j=0;j<8;j++) {
//		System.out.print(Magnet[i][j]+" ");
//	}
//	System.out.println();
//}
//for(int i=0;i<K;i++) {
//	rotateInfo ri = Info[i];
//	System.out.println("idx: "+ ri.idx+" "+"dir: "+ri.dir+" ");
//}
