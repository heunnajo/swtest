package ss;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkMiddleSchool_2nd {
	static int N,M,map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean[][] isStarted;
	
	private static class Point {
		int x;
        int y;

        public Point(int x, int y) {
        	this.x = x;
            this.y = y;
        }
    }
	static boolean outRange(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //1. BFS로 블록그룹 탐색!
        int sumScore = 0;
        while(true) {
        	isStarted = new boolean[N][N];
        	Queue<Point> q = new LinkedList<>();
        	//최대 크기 블록그룹 탐색 위해 우선순위 비교 위한 준비물!
        	int maxGroupCnt = 0,maxRainbowCnt = 0;
        	int targetX = -1, targetY = -1;
        	
        	//2차원 배열 완전탐색.
        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			if(0<map[i][j] <6 && !isStarted[i][j]) {
        				isStarted[i][j] = true;
        				int GroupCnt = 0,RainbowCnt = 0;
        				int GroupX = i,GroupY = j;
        				int color = map[i][j];
        				
        				q.add(new Point(i,j));
        				boolean[][] visited = new boolean[N][N];
        				while(!q.isEmpty()) {
        					Point p = q.poll();
        					GroupCnt++;
        					visited[p.x][p.y] = true;
        					
        					for(int d=0;d<4;d++) {
        						int nx = p.x+dx[d],ny = p.y+dy[d];
        						
        						if(outRange(nx,ny)||visited[nx][ny])continue;
        						if(map[nx][ny] == 0 || map[nx][ny]==color) {
        							visited[nx][ny] = true;
        							q.add(new Point(nx,ny));
        							
        							if(map[nx][ny] == 0) {
        								RainbowCnt++;//현재 탐색중인 블록그룹의 무지개 블록갯수 증가!
        							}
        							if(map[nx][ny] == color) {
        								isStarted[nx][ny] = true;
        								if(GroupX>nx) {
        									GroupX = nx;
        									GroupY = ny;
        								} else if(GroupX == nx && GroupY > ny) {
        									GroupY = ny;
        								}
        							}
        						}
        					}
        				}
        				
        				if(maxGroupCnt < GroupCnt) {
        					maxGroupCnt = GroupCnt;
        					maxRainbowCnt = RainbowCnt;
        					targetX = GroupX;
        					targetY = GroupY;
        				} else if(maxGroupCnt == GroupCnt) {
        					if(maxRainbowCnt < RainbowCnt) {
        						maxRainbowCnt = RainbowCnt;
        						targetX = GroupX;
            					targetY = GroupY;
        					} else if(maxRainbowCnt == RainbowCnt) {
        						if(targetX < GroupX) {
        							targetX = GroupX;
                					targetY = GroupY;
        						} else if(targetX == GroupX && targetY < GroupY) {
        							targetY = GroupY;
        						}
        					}
        				}
        				
        			}
        		}
        	}//2차원 배열 완탐 종료
        	
        	//2.조건 체크 후 점수 합산.
        	if(maxGroupCnt<2) break;
        	sumScore += Math.pow(maxGroupCnt, 2);
        	
        	//3.블록 그룹 삭제 처리.
        	q = new LinkedList<>();
        	q.add(new Point(targetX,targetY));
        	int color = map[targetX][targetY];
        	while(!q.isEmpty()){
        		Point p = q.poll();
        		for(int d=0;d<4;d++) {
        			int nx = p.x+dx[d],ny = p.y+dy[d];
        			if(outRange(nx,ny)) continue;
        			if(map[nx][ny] == 0 || map[nx][ny] == color) {
        				map[nx][ny] = 6;
        				q.add(new Point(nx,ny));
        			}
        		}
        	}
        	gravity();
        	leftRotate();
        	gravity();
        }//while문 종료.
        System.out.println(sumScore);
	}
	private static void gravity() {
		
	}
	private static void leftRotate() {
		
	}
}
