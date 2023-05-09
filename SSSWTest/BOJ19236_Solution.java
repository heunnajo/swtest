import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//청소년 상어
public class BOJ19236_Solution {
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        Pair[] fish = new Pair[17];

        for(int i=0; i<4; i++) {
            String[] input = br.readLine().split(" ");

            for (int j = 0; j < 7; j = j + 2) {
                fish[Integer.parseInt(input[j])] = new Pair(i, j / 2, Integer.parseInt(input[j + 1]) - 1);
                map[i][j / 2] = Integer.parseInt(input[j]);
            }
        }

        int eat = map[0][0];
        Pair shark = new Pair(0, 0, fish[map[0][0]].d);
        fish[0] = new Pair(0, 0, -1);
        fish[map[0][0]] = new Pair(0, 0, -1);
        map[0][0] = -1;

        dfs(map, fish, eat, shark);
        System.out.println(max);
    }

    public static void dfs(int[][] map, Pair[] fish, int eat, Pair shark) {
        int[][] temp_map = new int[4][4];
        for(int k=0; k<4; k++) {
            System.arraycopy(map[k], 0, temp_map[k], 0, 4);
        }

        Pair[] temp_fish = new Pair[17];
        for(int i=1; i<=16; i++)
            temp_fish[i] = new Pair(fish[i].x, fish[i].y, fish[i].d);

        for(int i=1; i<=16; i++) {

            if(temp_fish[i].d != -1) {
                Pair f = new Pair(temp_fish[i].x, temp_fish[i].y, temp_fish[i].d);
                int nd = f.d;

                for(int j=0; j<8; j++) {
                    nd %= 8;

                    int nx = f.x + dx[nd];
                    int ny = f.y + dy[nd];

                    if(nx<0 || nx>=4 || ny<0 || ny>=4 || map[nx][ny]==-1) {
                        nd++;
                        continue;
                    }

                    if(temp_map[nx][ny]==0) {
                        temp_map[f.x][f.y] = 0;
                        temp_map[nx][ny] = i;
                        temp_fish[i] = new Pair(nx, ny, nd);
                        break;
                    }

                    else if(temp_map[nx][ny]>0){
                        //System.out.println(map[f.x][f.y]+" "+i);
                        temp_map[f.x][f.y] = temp_map[nx][ny];
                        temp_fish[temp_map[nx][ny]] = new Pair(f.x, f.y, temp_fish[temp_map[nx][ny]].d);
                        temp_map[nx][ny] = i;
                        temp_fish[i] = new Pair(nx, ny, nd);
                        break;
                    }
                }
            }
        }

        /*for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++)
                System.out.print(map[i][j]+" ");
            System.out.println();
        }
        System.out.println();*/

        boolean flag = false;

        for(int i=1; i<=4; i++) {
            int nx = shark.x + i*dx[shark.d];
            int ny = shark.y + i*dy[shark.d];

            if(nx<0 || nx>=4 || ny<0 || ny>=4)
                break;

            int t = temp_map[nx][ny];

            if(t > 0) {
                flag = true;

                temp_map[shark.x][shark.y] = 0;
                Pair s = new Pair(nx, ny, temp_fish[t].d);
                temp_fish[t] = new Pair(0, 0, -1);
                temp_map[nx][ny] = -1;

                dfs(temp_map, temp_fish, eat+t, s);

                temp_map[nx][ny] = t;
                temp_fish[t] = new Pair(nx, ny, s.d);
                temp_map[shark.x][shark.y] = -1;
            }
        }

        if(!flag) {

            /*for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++)
                    System.out.print(map[i][j]+" ");
                System.out.println();
            }
            System.out.println();*/

            max = Math.max(eat, max);
        }
    }

    static class Pair {
        int x;
        int y;
        int d;

        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}