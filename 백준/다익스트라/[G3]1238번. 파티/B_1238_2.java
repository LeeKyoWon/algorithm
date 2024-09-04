import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1238_2 {

    final static int INF = 1_000_001;
    static int N;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, time));
        }

        int[] round_trip_time = dijkstra(X);

        int max = 0;
        for(int i=1; i<=N; i++) {
            if(i==X)
                continue;

            int[] tmp = dijkstra(i);
            round_trip_time[i] += tmp[X];
            if (max < round_trip_time[i]){
                max = round_trip_time[i];
            }
        }

        System.out.println(max);
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Edge> min_heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.time - o2.time;
            }
        });

        int[] d = new int[N+1];
        Arrays.fill(d, INF);
        d[start] = 0;

        min_heap.add(new Edge(start, 0));

        while(!min_heap.isEmpty()) {
            Edge cur = min_heap.poll();
            if(cur.time == d[cur.node]) {
                for(Edge e : edges[cur.node]) {
                    if(d[e.node] > d[cur.node] + e.time) {
                        d[e.node] = d[cur.node] + e.time;
                        min_heap.add(new Edge(e.node, d[e.node]));
                    }
                }
            }
        }

        return d;
    }

    public static class Edge {
        int node;
        int time;

        public Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
