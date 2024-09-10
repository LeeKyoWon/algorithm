import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1167 {

    static final int INF = 1_000_000_001;
    static int n;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

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

        int[] node1 = dijkstra(1);

        int[] ans = dijkstra(node1[1]);

        System.out.println(ans[0]);
    }

    public static int[] dijkstra(int start) {
        int[] d = new int[n+1];
        Arrays.fill(d, INF);
        d[start] = 0;

        PriorityQueue<Edge> min_edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });

        min_edges.add(new Edge(start, 0));

        while(!min_edges.isEmpty()) {
            Edge cur = min_edges.poll();

            if(cur.cost == d[cur.node]) {
                for(Edge e : edges[cur.node]) {
                    if(d[e.node] > d[cur.node] + e.cost) {
                        d[e.node] = d[cur.node] + e.cost;
                        min_edges.add(new Edge(e.node, d[e.node]));
                    }
                }
            }
        }

        int max = -1;
        int max_idx = 0;
        for(int i=1; i<=n; i++) {
            if(max < d[i]) {
                max = d[i];
                max_idx = i;
            }
        }

        return new int[]{max, max_idx};
    }

    public static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
