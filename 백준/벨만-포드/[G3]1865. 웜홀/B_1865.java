import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 벨만-포드
 *
 * <중요 포인트>
 * 1. 모든 지점이 연결되었다는 보장이 없다는 중요한 아이디어 !!
 *    -> 시작점을 1로 잡고 진행하더라도
 *        1과 연결되어 있지 않은 그래프에 대해서는 알 수 없으니
 *        아직 방문하지 않은 점에 대해서 벨만-포드 알고리즘 반복해서 수행
 *
 * 2. 도로는 양방향, 웜홀은 단방향 연결 !!
 */
public class B_1865 {

    static final int INF = 30_000_000;
    static boolean[] visited;
    static int N;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int case_num = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < case_num; k++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());


            boolean time_flag = false;
            visited = new boolean[N+1];
            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i=0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }

            int cur_start = 1;
            while(true) {
                boolean loop_flag = false;

                time_flag = bellman_ford(cur_start);

                for (int i = 1; i <= N; i++) {
                    if (!visited[i]) {
                        loop_flag = true;
                        cur_start = i;
                    }
                }
                if(time_flag || !loop_flag) {
                    break;
                }
            }

            if(time_flag)
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");

        }

        System.out.println(sb);

    }

    public static boolean bellman_ford(int start) {
        int[] min_dist = new int[N+1];
        Arrays.fill(min_dist, INF);
        min_dist[start] = 0;
        visited[start] = true;

        for(int i=1; i<=N; i++) {
            for(int j=0; j<edges.size(); j++) {
                Edge cur = edges.get(j);

                int u = cur.u;
                int v = cur.v;

                if(min_dist[u] == INF) {
                    continue;
                }

                if(min_dist[v] > min_dist[u] + cur.time) {
                    min_dist[v] = min_dist[u] + cur.time;
                    visited[v] = true;
                    if(i == N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Edge {
        int u;
        int v;
        int time;

        public Edge(int u, int v, int time) {
            this.u = u;
            this.v = v;
            this.time = time;
        }
    }
}
