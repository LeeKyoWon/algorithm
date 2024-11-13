import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 유니온 파인드
 */
public class B_1922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(u, v, cost));
        }

        int ans = 0;
        int cnt = 0;

        while(cnt < N-1) {
            Edge cur = edges.poll();

            if(union(parent, cur.u, cur.v)) {
                cnt++;
                ans += cur.cost;
            }
        }

        System.out.println(ans);
    }

    static boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x==y) {
            return false;
        } else if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        return true;
    }

    static int find(int[] parent, int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static class Edge implements Comparable<Edge>{
        int u;
        int v;
        int cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

    }
}
