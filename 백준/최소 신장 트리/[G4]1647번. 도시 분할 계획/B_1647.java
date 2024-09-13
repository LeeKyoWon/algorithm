import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소 신장 트리 응용
 *  : N-1개가 아닌 N-2 개를 구하면 됨
 *
 * 주의사항 !!!
 *  : 집이 딱 2개 주어진다면 답이 0이 나와야 하는 예외처리
 */
public class B_1647 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N==2) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Edge> min_heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            min_heap.add(new Edge(u, v, w));
        }

        int cnt = 0;
        int ans = 0;

        int[] parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        while(!min_heap.isEmpty()) {
            Edge cur = min_heap.poll();

            if(!isSameGroup(parents, cur.u, cur.v)) {
                union(parents, cur.u, cur.v);
                ans += cur.w;
                cnt++;
            }

            if(cnt == N-2)
                break;
        }

        System.out.println(ans);
    }

    public static void union(int[] parents, int x, int y) {
        x = find(parents, x);
        y = find(parents, y);

        if(x<y)
            parents[y] = x;
        else
            parents[x] = y;
    }

    public static int find(int[] parents, int x) {
        if(x == parents[x]) return x;
        return parents[x] = find(parents, parents[x]);
    }

    public static boolean isSameGroup(int[] parents, int x, int y) {
        x = find(parents, x);
        y = find(parents, y);

        return x == y;
    }

    public static class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
