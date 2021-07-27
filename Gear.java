package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Gear {
	//톱니바퀴 배열 저장.
	static int[][] gears = new int[4][8];
	static void rotate_check(int index,int dir,int flag) {
		//if(index <0 || index>3) return;
		//초기에 flag = 0. 일단 무조건 실행.
		if(flag == 0) {
			if(index-1 >=0 && gears[index-1][2] != gears[index][6]) {
				rotate_check(index-1,-dir,-1);//왼쪽 톱니바퀴에서 왔다.왼쪽만 생각한다.
			}
			
			if(index+1 < 4 && gears[index][2] != gears[index+1][6]) {
				rotate_check(index+1,-dir,1);
			}
		}
		//왼쪽방향 톱니들 회전.
		else if(flag==-1) {
			//톱니 회전.
			if(index-1 >=0 && gears[index-1][2] != gears[index][6]) {
				rotate_check(index-1,-dir,-1);//왼쪽 톱니바퀴에서 왔다.왼쪽만 생각한다.
			}
		}
		//오른쪽방향 톱니들 회전.
		else if(flag==1) {
			//톱니 회전.
			if(index+1 < 4 && gears[index][2] != gears[index+1][6]) {
				rotate_check(index+1,-dir,1);
			}
		}
		rotate(index,dir);
	}
	static void rotate(int index,int dir) {
		//1.시계방향 회전.
		if(dir == 1) {
			int tmp = gears[index][7];
			for(int i=1;i<7;i++) {
				gears[index][i] = gears[index][i-1];
			}
			gears[index][0] = tmp;
			/*정답.
			int tmp = gears[index][7];
			for(int i=7;i>0;i--) {
				gears[index][i] = gears[index][i-1];
			}
			gears[index][0] = tmp;
			*/
		}
		//2.반시계방향 회전.
		if(dir == -1) {
			int tmp = gears[index][0];
			for(int i=0;i<7;i++) {//a[n] = a[n+1]
				gears[index][i] = gears[index][i+1];//[i-1]해서 틀림. = [i+1]
			}
			gears[index][7] = tmp;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<4;i++) {//톱니바퀴 톱니 정보 입력받는다.
			String[] input = br.readLine().split("");
			for(int j=0;j<8;j++) {
				gears[i][j] = Integer.parseInt(input[j]);
			}
		}
		int time = Integer.parseInt(br.readLine());
		while(time-- >0) {
			String[] input2 = br.readLine().split(" ");
			int index = Integer.parseInt(input2[0])-1;//zerobaseindex.3->2, 1->0
			int dir = Integer.parseInt(input2[1]);
			rotate_check(index,dir,0);
			
			System.out.println("===== 톱니바퀴 정보 출력 =====");
			for(int i=0;i<4;i++) {
				for(int j=0;j<8;j++) {
					System.out.print(gears[i][j]+" ");
				}
				System.out.println();
			}
		}
		//정답 계산
		int ans = 0;
		if(gears[0][0]==1) {ans += 1;}
		if(gears[1][0]==1) {ans += 2;}
		if(gears[2][0]==1) {ans += 4;}
		if(gears[3][0]==1) {ans += 8;}
		System.out.println(ans);
	}

}
