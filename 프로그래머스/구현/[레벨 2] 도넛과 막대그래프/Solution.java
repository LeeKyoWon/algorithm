import java.io.*;
import java.util.*;

class Solution {
    
    static LinkedList<Integer>[] edge_queues;
    static int[] out_degree;
    static int[] in_degree;
    
    public int[] solution(int[][] edges) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int node_cnt = 0;
        
        for(int i=0; i<edges.length; i++) {
            int from = edges[i][0];        
            int to = edges[i][1];
            map.put(from, map.getOrDefault(from, 0)+1);
            
            if(from > node_cnt)
                node_cnt = from;
            
            if(to > node_cnt)
                node_cnt = to;
        }
        
        out_degree = new int[node_cnt+1];
        in_degree = new int[node_cnt+1];
        
        edge_queues = new LinkedList[node_cnt+1];
        for(int i=0; i<=node_cnt; i++) {
            edge_queues[i] = new LinkedList<>();
        }
        
        for(int i=0; i<edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            out_degree[from]++;
            in_degree[to]++;
            
            edge_queues[from].add(to);
        }
        
        int central_node = 0;
        for(int i=1; i<=node_cnt; i++) {
            if(out_degree[i] >= 2 && in_degree[i] == 0) {
                central_node = i;
                break;
            }
        }
        
        int[] ans = new int[3];
        for(int i=0; i<out_degree[central_node]; i++) {
            int graph_type = getGraphType(edge_queues[central_node].poll(), node_cnt);
            ans[graph_type]++;
        }
        
        int[] answer = {central_node, ans[0], ans[1], ans[2]};
        return answer;
    }
    
    public static int getGraphType(int start, int node_num) {
        
        int cur = start;
        boolean line_flag = false;
        boolean eight_flag = false;
        
        
        while(true) {
            
            if(out_degree[cur] == 2 && in_degree[cur] >= 2) {
                eight_flag = true;
                break;
            }
            
            if (out_degree[cur] == 0 && in_degree[cur] > 0) {
                line_flag = true;
                break;
            }
            
            if(!edge_queues[cur].isEmpty()) {
                cur = edge_queues[cur].poll();
            } else {
                break;
            }

        }
        
        if(eight_flag) {
            return 2;
        } else if(line_flag) {
            return 1;
        } else 
            return 0;
    }
}
