#include <iostream>
#include <vector>
#include <string>
using namespace std;
void cal_time(int idx, vector<int> &time){
    switch(idx){
        case 1:
            if(time[0]>1){
                time[0]=time[0]-1;
            }
            else if(time[0]==1){
                time[0]=24;
            }
            else{
                time[0]=23;
            }
            time[1]=time[1]+60;
            break;
        case 2:
            if(time[1]>0){
                time[1]=time[1]-1;
            }
            else{
                cal_time(1,time);
                time[1]=time[1]-1;
            }
            time[2]=time[2]+60;
            break;
    }    
}

vector<int> calculate(vector <int> & s_time, vector<int> &e_time){
    vector <int> answer;
    if(s_time[2]>e_time[2]){
        cal_time(2,e_time);
        answer.push_back(e_time[2]-s_time[2]);
    }
    else{
        answer.push_back(e_time[2]-s_time[2]);
    }
    if(s_time[1]>e_time[1]){
        cal_time(1,e_time);
        answer.push_back(e_time[1]-s_time[1]);
    }
    else{
        answer.push_back(e_time[1]-s_time[1]);
    }
     if(s_time[0]>e_time[0]){

        answer.push_back(24+e_time[0]-s_time[0]);
    }
    else{
        answer.push_back(e_time[0]-s_time[0]);
    }   
    return answer;
}

int main(){
    string start,end,t_answer;
    vector <int> s_time;
    vector <int> e_time;
    vector <int> answer;
    cin >> start >>end;
    for(int i=0;i<9;i=i+3){
        s_time.push_back(stoi(start.substr(i,2)));
        e_time.push_back(stoi(end.substr(i,2)));

    }
    answer=calculate(s_time,e_time);
    for(int i=2;i>-1;i--){
        if(answer[i]<10){
            t_answer+="0";
            t_answer+=to_string(answer[i]);
        }
        else{
            t_answer+=to_string(answer[i]);
        }
        if(i !=0){
            t_answer+=":";
        }
    }
    cout <<t_answer;
}