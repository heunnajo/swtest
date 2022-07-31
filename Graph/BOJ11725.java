//트리의 부모 찾기 Memory : 39960KB, Time : 388ms 더 빠른 이유는 SuppressWarnings?
import	java.io.*;
import	java.util.*;

public class	Main
{
	public static void	main(String[] args) throws Exception
	{  
		StreamTokenizer	st	= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in))); 		
		StringBuilder	sb	= new StringBuilder();
		st.nextToken(); int	n = (int)st.nval;
		@SuppressWarnings("unchecked")
		ArrayList<Integer> node[] = new ArrayList[n+1];
		int	parent[]	= new int[n+1];
		int				i,x,y;
		LinkedList<Integer>	q	= new LinkedList<>();

		for(i=1; i<=n; ++i)
			node[i]	= new ArrayList<>();	

		for(i=1; i<n; ++i){
			st.nextToken(); x = (int)st.nval;
			st.nextToken(); y = (int)st.nval;
			node[x].add(y);
			node[y].add(x);
		}	

		parent[1]	= 1;
		q.add(1);
		
		while(!q.isEmpty()){
			i	= q.poll();
			for(int j : node[i]){
				if(parent[j] == 0){
					parent[j]	= i;
					q.add(j); 
				}
			}
		}

		for(i=2; i<=n; ++i)
			sb.append(parent[i]).append("\n");

		System.out.print(sb.toString());
	}
}
