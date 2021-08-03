import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
public class TreeInvestment_LinkedList {
	static int N,M,K,A[][],F[][];//A:로봇 S2D2가 뿌리는 양분 양, F:현재 양분상태.
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static class Tree{
		int x,y,age;
		boolean alive;
		Tree(int x,int y,int age,boolean alive){
			this.x = x;
			this.y = y;
			this.age = age;
			this.alive = alive;
		}
	}
	static LinkedList<Tree> Trees;
	static int solve() {
		for(int k=0;k<K;k++) {//전체 반복 조건 :k번 반복.
			//봄.
			for(Tree t:Trees) {
				if(F[t.x][t.y]>= t.age) {
					F[t.x][t.y] -= t.age;
					t.age++;
				} else {
					t.alive = false;
				}
			}
			//여름:여름에 죽은 나무들은 양분이 되고, 트리 리스트에서 삭제한다.
			//for-each문에서는 node를 삭제할 수 없기 때문에 Iterator를 사용한다!
			for(Iterator<Tree> it = Trees.iterator();it.hasNext();) {
				Tree t = it.next();
				if(t.alive == false) {
					F[t.x][t.y] += t.age/2;
					it.remove();//트리 list에서 삭제. Iterator 제대로 사용된 것 맞나..
				}
			}
			
			//가을 :읹접 8칸에 나이1인 나무 번식.
			//각 나무의 현재 위치 x,y라고 하면
			LinkedList<Tree> newTrees = new LinkedList<Tree>();
			for(Tree t:Trees) {
				if(t.age % 5 == 0) {
					for(int d=0;d<8;d++) {
						int nx = t.x+dx[d];
						int ny = t.y+dy[d];
						if(nx<0 || nx>N-1 || ny<0 || ny>N-1) continue;
						newTrees.add(new Tree(nx,ny,1,true));//(nx,ny)에 나무가 생긴다.=>나무가 생긴다는 것은 어떻게 구현하지?
					}
				}
			}
			Trees.addAll(0,newTrees);//정렬필요없이 나이 작은 나무들 앞에 위치.
			//겨울 :로봇이 양분 추가.
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					F[i][j] += A[i][j];
				}
			}
			
		}
		return Trees.size();//k년 후 존재하는 나무 총 갯수.
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		//객체 초기화.
		A = new int[N][N];
		F = new int[N][N];
		Trees = new LinkedList<Tree>();
		
		//로봇이 뿌리는 양분의 양 정보 저장!
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(input[j]);
				F[i][j] = 5;
			}
		}
		//나무 정보 저장!(x,y,z)
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			int age = Integer.parseInt(input[2]);
			Trees.add( new Tree(x,y,age,true));
		}
		
		System.out.println(solve());
		
		br.close();
	}

}