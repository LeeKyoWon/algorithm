import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP
 */
public class B_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];

        for (int i = 1; i <= n; i++) {
            dp[0] = 1;
            int cur = coins[i-1];
            for(int j = 1; j <= k; j++) {
                if(j >= cur) {
                    dp[j] = dp[j - cur] + dp[j];
                }
            }
        }

        System.out.println(dp[k]);
    }
}
