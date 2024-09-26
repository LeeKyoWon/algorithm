import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 */
public class B_12851_2 {

    public static final int INF = 200_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        int[] dp = new int[100_001];
        Arrays.fill(dp, INF);
        dp[N] = 0;

        int ans_cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == K) {
                ans_cnt++;
                while(!q.isEmpty() && dp[cur] == dp[K]){
                    cur = q.poll();
                    if (cur == K){
                        ans_cnt++;
                    }
                }
                break;
            }

            if(2*cur <= 100_000) {
                if(dp[cur * 2] == INF) {
                    dp[cur*2] = dp[cur] + 1;
                    q.add(2 * cur);
                } else if (dp[cur*2] == dp[cur] + 1) {
                    q.add(2 * cur);
                }

            }

            if (0 < cur) {
                if(dp[cur-1] == INF) {
                    dp[cur-1] = dp[cur] + 1;
                    q.add(cur-1);
                } else if (dp[cur-1] == dp[cur] + 1) {
                    q.add(cur-1);
                }
            }

            if (cur < 100_000){
                if(dp[cur+1] == INF) {
                    dp[cur+1] = dp[cur] + 1;
                    q.add(cur+1);
                } else if (dp[cur+1] == dp[cur] + 1) {
                    q.add(cur+1);
                }
            }
        }

        System.out.println(dp[K]);
        System.out.println(ans_cnt);
    }

}
