import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DFS 풀이
 */
public class B_1167_2 {

    static int n;
    static ArrayList<Edge>[] edges;
    static boolean[] visited;
    static int max;
    static int max_idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];

        edges = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());

            int node = Integer.parseInt(st.nextToken());
            while(node != -1) {
                int cost = Integer.parseInt(st.nextToken());
                edges[cur].add(new Edge(node, cost));

                node = Integer.parseInt(st.nextToken());
            }
        }

        dfs(2,0);

        max = 0;
        Arrays.fill(visited, false);

        dfs(max_idx, 0);

        System.out.println(max);
    }

    public static void dfs(int cur, int dist) {
        visited[cur] = true;

        if(max < dist) {
            max = dist;
            max_idx = cur;
        }

        for(Edge e : edges[cur]) {
            if (!visited[e.node]) {
                dfs(e.node, dist + e.dist);
            }
        }
    }

    public static class Edge {
        int node;
        int dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
