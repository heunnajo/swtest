//need to be fix error
package ss;
import java.io.*;
import java.util.*;

//원자 소멸 시뮬레이션
public class SWEA_5648 {
//	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedReader input;
	static StringBuilder output = new StringBuilder();
	
	static int TC;
	
	static int N;//원자 갯수
	
	//이동 방향
	static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	//충돌 에너지 합
	static int E;
	
	//전체 원자 목록
	static List<Atom> list = new LinkedList<>();
	
	//이동된 원자들을 지점을 기반으로 목록으로 관리하기 위한 맵 : 충돌하는 원자들 한번에 관리하기 위함!
	//key = (row,col) => len*row + col
	static Map<Integer,List<Atom>> moveMap = new HashMap<>();
	
	//매 텀이 끝나고 지울 원자의 목록 : 지우는 대상은 범위를 벗어나거나, 충돌해서 없어진 것들
	//컬렉션의 변경이 일어날 때 인덱스를 매번 조정하는 것이 까다롭기 때문에 delList라는 별개의 저장소를 만들어서 삭제할 원자를 저장한다!
	static List<Atom> delList = new LinkedList<>();
	
	static class Atom{
		int row,col,dir,power;
		
		public Atom(int row, int col, int dir, int power){
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.power = power;
		}
		public void move() {
			if(dir == UP) row++;
			else if(dir == DOWN) row--;
			else if(dir == RIGHT) col++;
			else col--;
		}
	}
	static boolean isIn(Atom a) {
		return -2000 <= a.row && a.row <= 2000 && -2000 <= a.col && a.col <= 2000;
	}
	public static void main(String[] args) throws IOException{
//		input = new BufferedReader(new StringReader(src));
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		TC = Integer.parseInt(input.readLine());
		
		StringTokenizer tokens = null;
		for(int t = 1;t<=TC; t++) {
			N = Integer.parseInt(input.readLine());
			
			list.clear();//테케 여러개 돌릴 때 초기화 제일 중요! 
			for(int n=0;n<N;n++) {
				tokens = new StringTokenizer(input.readLine());
				//0.5초 단위로 이동시키기 위해 좌표를 2배로 증가!
				int col = (Integer.parseInt(tokens.nextToken()))*2;
				int row = (Integer.parseInt(tokens.nextToken()))*2;
				
				int dir = Integer.parseInt(tokens.nextToken());
				int power = Integer.parseInt(tokens.nextToken());
				
				list.add(new Atom(row,col,dir,power));
			}
		
		
			E = 0;
			while(true) {
				if(list.size() <= 1) break;
				
				moveMap.clear();//각 이동턴마다 원자를 위치 기반으로 저장!(위치값을 key로 갖는다!)
				delList.clear();//각 이동턴마다 삭제할 원자를 저장!
				for(Atom a:list) {//iterator를 사용하는 for-each문에서는 인덱스 조절할 수 없기 때문에 리스트의 원소를 삭제할 수 없다. 그래서 삭제할 원소들을 따로 저장한다!
					a.move();
					if(!isIn(a)) {
						delList.add(a);
						continue;
					} else putToMoveMap(a);
				}//1초가 지남.
				checkCrash();//삭제할 원자를 저장하는 delList를 완성
				list.removeAll(delList);//전체 원자 목록에서 삭제되는 원자 목록을 삭제!헐 대박적. 이건 몰랐는데. 어떻게 동작하는 거지?
			}
			output.append("#").append(t).append(" ").append(E).append("\n");
		}
		System.out.println(output);
	}
	static void checkCrash() {
		Set<Integer> keys = moveMap.keySet();
		for(Integer key:keys) {
			List<Atom> list = moveMap.get(key);
			if(list.size()>1) {
				for(Atom a:list) {
					E += a.power;
					delList.add(a);
				}
			}
		}
	}
	static void putToMoveMap(Atom a) {
		//2차원 배열의 키값을 int로 처리 
		Integer key = a.row * 4001 + a.col;
		if(moveMap.containsKey(key)) {
			moveMap.get(key).add(a);
		} else {
			List<Atom> atoms = new ArrayList<>();
			atoms.add(a);
			moveMap.put(key,atoms);
		}
	}

}
