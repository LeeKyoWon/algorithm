import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] grape_glasses = new int[N];
        for (int i = 0; i < N; i++) {
            grape_glasses[i] = Integer.parseInt(br.readLine());
        }

        if(N == 1) {
            System.out.println(grape_glasses[0]);
            return;
        } else if(N == 2) {
            System.out.println(grape_glasses[0] + grape_glasses[1]);
            return;
        }

        int[][] dp = new int[N][2];
        dp[0][0] = grape_glasses[0];
        dp[1][0] = grape_glasses[1];
        dp[1][1] = grape_glasses[0] + grape_glasses[1];
        dp[2][0] = grape_glasses[0] + grape_glasses[2];
        dp[2][1] = grape_glasses[1] + grape_glasses[2];

        for (int i = 3; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][0] = Math.max(dp[i-2][0], Math.max(dp[i-2][1], Math.max(dp[i-3][0], dp[i-3][1])))+ grape_glasses[i];
                dp[i][1] = dp[i-1][0] + grape_glasses[i];
            }
        }

        int ans = 0;
        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                if(ans < dp[i][j])
                    ans = dp[i][j];
            }
        }
        System.out.println(ans);
    }
}
