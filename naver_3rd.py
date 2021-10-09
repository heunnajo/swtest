vector<int> solution(vector < vector <int> > grid, vector < vector <int> > queries){
    vector<int> answer;


    for(int i=0;i<queries.size();++i){
        int r1 = queries[i][0] -1, c1 = queries[i][1] -1;
        int r2 = queries[i][2] -1, c2 = queries[i][3] -1;
        int shorSide = min(r2-r1+1,c2-c1+1);
        if(queries[i][4] == 1){//right down

        }
        else{

        }
    }
}