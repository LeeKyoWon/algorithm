import java.util.*;
import java.io.*;

class Solution {
    
    int n;
    int k;
    LinkedList<int[]> queue = new LinkedList<>();
    
    int[] arr;
    int max = 0;
    int[] ans;
    
    ArrayList<Integer> team1_list;
    
    public int[] solution(int[][] dice) {
        
        n = dice.length;
        k = n / 2;
        
        arr = new int[k];
        arr[0] = 0;
        getTeamCombination(1, 0);
        
        for(int i=0; i< n; i++) {
            Arrays.sort(dice[i]);
        }
        
        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            calculate_WinDrawLost(temp, dice);
        }
        
        for(int i=0; i<k; i++) {
            ans[i]++;
        }
        int[] answer = ans;
        return answer;
    }
    
    // 주어진 케이스에 대해서 승, 무, 패의 수를 각각 구한다.
    public void calculate_WinDrawLost(int[] arr, int[][] dice) {
        
        // Team1 는 A가 고른 것
        // Team2 는 B가 고른 것 
        boolean[] teamCheck = new boolean[n];
        
        for(int i=0; i<k; i++) {
            teamCheck[arr[i]] = true;
        }
        
        int[][] team = new int[2][k];
        
        int team1_cnt = 0;
        int team2_cnt = 0;
        for(int i=0; i<n; i++) {
            if(teamCheck[i]) {
                team[0][team1_cnt++] = i;
            } else {
                team[1][team2_cnt++] = i;
            }
        }
        
        
        ArrayList<Integer> team1_list = new ArrayList<>();
        ArrayList<Integer> team2_list = new ArrayList<>();
        
        
        getDiceCombination(0, 0, dice, team[0], team1_list);
        getDiceCombination(0, 0, dice, team[1], team2_list);
        
        Collections.sort(team1_list);
        Collections.sort(team2_list);
        
        
        int win_cnt = 0;
        int draw_cnt = 0;
        int lose_cnt = 0;
        
        int team2_size = team2_list.size();
        
        for(int i=0; i<team1_list.size(); i++) {
            int idx1 = getLeftIndex(team1_list.get(i), team2_list);
            int idx2 = getRightIndex(team1_list.get(i), team2_list);
            
            if (idx1 == idx2) {
                win_cnt += idx1;
                lose_cnt += team2_size - idx1;
            } else {
                win_cnt += idx1;
                draw_cnt += idx2 - idx1;
                lose_cnt += team2_size - idx2;
            }
        }
        
        if(win_cnt > max) {
            max = win_cnt;
            ans = team[0];
        }
        
        if (lose_cnt > max) {
            max = lose_cnt;
            ans = team[1];
        }
    }
    
    // A가 고른 주사위 조합에 대해서 "모든 합의 경우의 수" 구하기
        public void getDiceCombination(int cur, int sum, int[][] dice, int[] team, ArrayList<Integer> team_list) {
        if(cur == k) {
            team_list.add(sum);
            return;
        }
        
        int temp = sum;
        for(int i=0; i<6; i++) {
            sum = temp;
            sum += dice[team[cur]][i];
            getDiceCombination(cur+1, sum, dice, team, team_list);
        }
    }
    
    // A가 고를 가능한 "모든 주사위 조합" 구하기
    public void getTeamCombination(int cur, int before) {
        if(cur == k) {
            int[] temp = new int[k];
            for(int i=0; i<k; i++) {
                temp[i] = arr[i];
            }
            queue.add(temp);
            return;
        }
        
        for(int i=before+1; i<n; i++) {
            arr[cur] = i;
            getTeamCombination(cur+1, i);
        }
    }

    // 같은 값이 여러 개일 경우 가장 왼쪽 인덱스 구하기
    public int getLeftIndex(int x, ArrayList<Integer> team2_list) {
        int left = 0;
        int right = team2_list.size() - 1;
        
        while(left <= right) {
            int mid = (left+right) / 2;
        
            if(x <= team2_list.get(mid)) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return left;
    }
    
    // 같은 값이 여러 개일 경우 가장 오른쪽 인덱스 구하기
    public int getRightIndex(int x, ArrayList<Integer> team2_list) {
        int left = 0;
        int right = team2_list.size() - 1;
        
        while(left <= right) {
            int mid = (left+right) / 2;
        
            if(x >= team2_list.get(mid)) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        
        return left;
    }
}
