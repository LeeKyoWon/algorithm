import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * DFS (백트래킹 ) + DP ( 자식의 합 -> 부모 )
 */
public class B_15681 {

    static LinkedList<Integer>[] edges;
    static int[] ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        edges = new LinkedList[N+1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new LinkedList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(v);
            edges[v].add(u);
        }

        ans = new int[N+1];
        Arrays.fill(ans, 1);
        visited = new boolean[N+1];
        // 루트에서 리프 노드 방향으로의 엣지만 남기기
        visited[R] = true;

        backtracking(R);

        for (int i = 0; i < Q; i++) {
            int cur = Integer.parseInt(br.readLine());
            sb.append(ans[cur]).append("\n");
        }

        System.out.println(sb);
    }

    public static int backtracking(int cur) {
        for(int node : edges[cur]) {
            if(!visited[node]) {
                visited[node] = true;
                ans[cur] += backtracking(node);
            }
        }

        return ans[cur];
    }
}
