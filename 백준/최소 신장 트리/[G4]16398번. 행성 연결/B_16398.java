import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소 신장 트리 (크루스칼)
 */
public class B_16398 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> min_cost_q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i < j) {
                    min_cost_q.add(new Edge(i, j, map[i][j]));
                }
            }
        }

        long ans = 0;
        int cnt = 0;
        while(!min_cost_q.isEmpty()) {
            Edge cur = min_cost_q.poll();

            if(union(cur.u, cur.v)) {
                cnt++;
                ans = ans + cur.cost;
            }

            if(cnt==N-1)
                break;
        }

        System.out.println(ans);
    }

    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y){
            return false;
        } else if (x > y){
            parent[x] = y;
        } else {
            parent[y] = x;
        }

        return true;
    }

    public static int find(int x) {
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
