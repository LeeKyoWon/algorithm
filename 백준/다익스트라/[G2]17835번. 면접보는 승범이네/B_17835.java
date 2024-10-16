import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라
 *  1. 그래프를 거꾸로 다룬다 ( 수렴 -> 발산 )
 *  2. 시작점을 여러 개 두고 해당 점을 모두 고려하며
 *     다른 점들로의 가장 가까운 경로를 정하는 방식
 */
public class B_17835 {

    static long INF = 10_000_000_000L;
    static int ans_idx = -1;
    static long ans = 0;

    static long[] d;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        d = new long[N+1];
        Arrays.fill(d, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges[v].add(new Edge(u, dist));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int interviewPlace = Integer.parseInt(st.nextToken());
            queue.add(new Edge(interviewPlace, 0));
            d[interviewPlace] = 0;
        }

        dijkstra();

        for (int i = 1; i <= N; i++) {
            if (ans < d[i]) {
                ans = d[i];
                ans_idx = i;
            }
        }

        System.out.println(ans_idx);
        System.out.println(ans);
    }

    static void dijkstra() {
        while(!queue.isEmpty()) {
            Edge cur = queue.poll();

            if(d[cur.node] == cur.dist) {
                for(Edge next : edges[cur.node]) {
                    if(d[next.node] > d[cur.node] + next.dist) {
                        d[next.node] = d[cur.node] + next.dist;
                        queue.add(new Edge(next.node, d[next.node]));
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        long dist;

        Edge(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}
