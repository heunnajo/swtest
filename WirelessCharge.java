import java.io.*;
import java.util.*;
 
public class WirelessCharge_Solution2{
    static info[]user;
    static int M,A,ret;
    static info[]BC;
    static int[][]move;
    static int[]dx={0,-1,0,1,0};
    static int[]dy={0,0,1,0,-1};
    public static void main(String[] args)throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
         
        int T=Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st=new StringTokenizer(br.readLine());
            M=Integer.parseInt(st.nextToken());
            A=Integer.parseInt(st.nextToken());
         
            user=new info[2];
            BC=new info[A];
            move=new int[2][M];//A,B move
             
            for(int i=0;i<2;i++)user[i]=new info(0,0);
            for(int i=0;i<A;i++)BC[i]=new info(0,0,0,0,0);
             
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++)
                move[0][i]=Integer.parseInt(st.nextToken());
             
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++)
                move[1][i]=Integer.parseInt(st.nextToken()); 
 
            for(int i=0;i<A;i++){
                st=new StringTokenizer(br.readLine());
                BC[i].y=Integer.parseInt(st.nextToken());
                BC[i].x=Integer.parseInt(st.nextToken());
                BC[i].c=Integer.parseInt(st.nextToken());
                BC[i].p=Integer.parseInt(st.nextToken());
            }
             
            user[0].x=1;
            user[0].y=1;
            user[1].x=10;
            user[1].y=10;
             
            sb.append("#"+t+" "+solve()+"\n");
        }
        System.out.println(sb);
    }
    static int solve() {
        int ans=0;
        ans+=count();
        for(int m=0;m<M;m++){
            for(int u=0;u<2;u++){
                user[u].x+=dx[move[u][m]];
                user[u].y+=dy[move[u][m]];
            }
            ans+=count();
        }
        return ans;
    }
    static int count() {
        ret=0;
        dfs(0,0);
        return ret;
    }
    static void dfs(int user, int sum) {
        if(user==2){
            ret=Math.max(ret, sum);
            return;
        }
        for(int a=0;a<A;a++){
            if(BC[a].used==0 && distance(user,a)){
                BC[a].used=1;
                dfs(user+1,sum+BC[a].p);
                BC[a].used=0;
            }
        }
        dfs(user+1,sum);
    }
    static boolean distance(int u, int a) {
        int dis=Math.abs(user[u].x-BC[a].x)+Math.abs(user[u].y-BC[a].y);
        if(dis<=BC[a].c)return true;
        else return false;
    }
    static class info{
        int x,y,c,p,used;
        info(int x,int y){
            this.x=x;
            this.y=y;
        }
        info(int x,int y,int c,int p,int used){
            this.x=x;
            this.y=y;
            this.c=c;
            this.p=p;
            this.used=used;
        }
    }
}