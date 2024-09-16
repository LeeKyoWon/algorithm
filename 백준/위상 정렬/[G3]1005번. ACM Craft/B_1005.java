import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int k=0; k<T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int W;
            int[] costs = new int[N+1];
            int[] dp = new int[N+1];

            LinkedList<Integer> cur_nodes = new LinkedList<>();
            ArrayList<Integer>[] edges = new ArrayList[N+1];

            st = new StringTokenizer(br.readLine());

            for(int i=1; i<=N; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i <= N; i++) {
                edges[i] = new ArrayList<>();
            }

            int[] cnt = new int[N+1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                edges[x].add(y);
                cnt[y]++;
            }


            W = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
                if(cnt[i] == 0) {
                    cur_nodes.add(i);
                    dp[i] = costs[i];
                }
            }

            while(!cur_nodes.isEmpty()) {

                int cur = cur_nodes.poll();

                if(cur==W) {
                    System.out.println(dp[W]);
                    break;
                }

                for (int next : edges[cur]) {
                    cnt[next]--;

                    if (dp[next] < costs[next] + dp[cur]) {
                        dp[next] = costs[next] + dp[cur];
                    }

                    if(cnt[next] == 0) {
                        cur_nodes.add(next);
                    }
                }
            }
        }
    }
}
