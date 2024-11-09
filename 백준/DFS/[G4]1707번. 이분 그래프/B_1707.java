import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * - DFS
 *
 * - 핵심 : 정점의 위상 표현
 *  1. 방문한적 없음   -> visited[] = false
 *  2. 방문했고 그룹 1 -> visited[] = true, group[] = false;
 *  3. 방문했고 그룹 2 -> visited[] = true, group[] = true;
 */
public class B_1707 {

    static int V;
    static int E;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[] group;
    static boolean ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int k = 0; k < T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

             // 정점 방문 여부 체크
            visited = new boolean[V+1];

            // 해당 정점의 그룹 체크
            group = new boolean[V+1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            ans = true;
            for (int i = 1; i <= V; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    group[i] = false;
                    dfs(i);
                }
            }

            if(ans) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int cur) {
        if(!ans)
            return;

        for(int next : graph[cur]) {

            if(!visited[next]) {
                visited[next] = true;
                group[next] = !group[cur];
                dfs(next);
            } else {
                if(group[cur] == group[next]) {
                    ans = false;
                    return;
                }
            }
        }
    }
}
