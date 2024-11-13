import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 백트래킹으로 풀려하다가 시간초과 발생
 *
 * - DP 로 풀어야만 시간 복잡도 통과
 *   -> 2~N 개 연속하는 것에 대하여 차례로 최소값을 구해 나간다
 *      Ex) dp[1][3] = dp[1][1] + dp[2][3] 과 dp[1][2] + dp[3][3] 중 작은 값을 선택하는 구조
 *
 * - 문제를 푸는 과정에 "누적 합" 개념도 적용된다.
 *   -> 부분 최적 해   :  과거의 비용                                    + 현재의 비용(부분 누적 합)
 *      Ex) dp[1][3] = Math.min(dp[1][1]+dp[2][3], dp[1][2] + dp[3][3]) + sum[3]-sum[0];
 *
 *
 */
public class B_11066 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int x = 0; x < T; x++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] sum = new int[N+1];
            for (int i = 1; i <= N; i++) {
                int cur = Integer.parseInt(st.nextToken());
                sum[i] = cur + sum[i-1];
            }

            int[][] dp = new int[N+1][N+1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j+i <= N; j++) {
                    int min = Integer.MAX_VALUE;
                    for(int k= j; k < j+i; k++) {
                        min = Math.min(min, dp[j][k] + dp[k+1][j+i] + sum[j+i] - sum[j-1]);
                    }
                    dp[j][j+i] = min;
                }
            }

            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb);
    }
}
