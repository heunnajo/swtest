package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class TreeInvestment_ArrayList {
	static int N,M,K,A[][],F[][];//A:로봇 S2D2가 뿌리는 양분 양, F:현재 양분상태.
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static List<Integer>[][] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		//객체 초기화.
		A = new int[N][N];
		F = new int[N][N];
		tree = new ArrayList[N][N];
		//로봇이 뿌리는 양분의 양 정보 저장!
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(input[j]);
				F[i][j] = 5;
				tree[i][j] = new ArrayList<Integer>();
			}
		}
		//나무 정보 저장!(x,y,z)
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			int z = Integer.parseInt(input[2]);
			tree[x][y].add(z);
		}
		
		//봄, 여름.
		for(int k=0;k<K;k++) {
			SpringSummer();
			FallWinter();
		}
		System.out.println(CountTrees());
		
		br.close();
	}
	
	static void SpringSummer() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//봄:나이만큼 양분 감소, 나이+1 증가.
				int sz = tree[i][j].size();//(i,j)칸에 있는 총 나무 갯수.
				boolean flag = false;
				for(int k=sz-1;k>=0;k--) {//(i,j)칸의 리스트를 돈다!
					int x = tree[i][j].get(k);
					if(flag || F[i][j]-x<0) {//양분-나이<0:나무 사망.
						flag = true;
						F[i][j] += x/2;
						tree[i][j].remove(k);
					}else {//양분-나이>=0
						F[i][j] -= x;
						tree[i][j].set(k,x+1);
					}
				}
				
			}
		}
	}
	static void FallWinter() {
		//가을 :인접 8칸에 나이1인 나무 번식.//각 나무의 현재 위치 x,y라고 하면
		//(nx,ny)에 나무가 생긴다.=>나무가 생긴다는 것은 어떻게 구현하지?
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//가을:나무 나이가 5의 배수이면 인접 8칸에 나이 1인 나무 생성.
				int sz = tree[i][j].size();//(i,j)칸에 있는 총 나무 갯수.
				for(int k=0;k<sz;k++) {
					if(tree[i][j].get(k) % 5==0) {
						for(int d=0;d<8;d++) {
							int nx = i+dx[d];
							int ny = j+dy[d];
							if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
							tree[nx][ny].add(1);//(nx,ny)에 나이 1인 노드 추가.
						}
					}
				}
				//겨울 :로봇이 양분 추가.
				F[i][j] += A[i][j];
			}
		}
				
	}
	static int CountTrees() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int sz = tree[i][j].size();//(i,j)에 있는 트리 갯수를 sz. N*N 모든칸ㅇ의 트리 갯수(각 칸의 리스트 크기) 더하면 된다.
				sum += sz;
			}
		}
		
		return sum;
	}
	
}

//나무 정보 디버깅.
//for(int i=0;i<TList.size();i++) {
//	System.out.print(TList.get(i).x+" ");
//	System.out.print(TList.get(i).y+" ");
//	System.out.println(TList.get(i).z+" ");
//}
//NxN 입력 디버깅.
//System.out.println();
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(F[i][j]+" ");
//	}
//	System.out.println();
//}
//System.out.println();