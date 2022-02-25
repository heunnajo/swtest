vector<int> solution(vector < vector <int> > grid, vector < vector <int> > queries){
    vector<int> answer;


    for(int i=0;i<queries.size();++i){
        int r1 = queries[i][0] -1, c1 = queries[i][1] -1;
        int r2 = queries[i][2] -1, c2 = queries[i][3] -1;
        int shorSide = min(r2-r1+1,c2-c1+1);
        if(queries[i][4] == 1){//right down
            for(int r=r1,c=c2,c>=c1;--c){
                int sz = min(c2-c+1,shortSide);
                for(int j=0;j<sz/2;++j){
                    int tmp = grid[r+j][c+j];
                    grid[r+j][c+j] = grid[r+sz-j-1][c+ssz-j-1];
                    grid[r+sz-j-1][c+sz-j-1] = tmp;
                }
            }
            for(int r=r2,c=c1;r>c1;r--){
                int sz = min(r2-r+1,shortSide);
                for(int j=0;j<sz/2;++j){
                    int tmp = grid[r+j][c+j];
                    grid[r+j][c+j] = grid[r+sz-j-1][c+sz-j-1];
                    grid[r+sz-j-1][c+sz-j-1] = tmp;
                }
            }
        }
        else{

        }
    }
}