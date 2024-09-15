import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - DP
 *  -> 경우의 수를 적절히 활용해서 dp 를 적용시켜야만 풀 수 있는 문제
 */
public class B_17404 {

    static int N;
    static int[][] map;
    static final int INF = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<3; i++) {
            int tmp = getMinDist(i);
            if (tmp < ans) {
                ans = tmp;
            }
        }

        System.out.println(ans);
    }

    public static int getMinDist(int idx) {
        int[][] dp = new int[N+1][3];

        for(int i=0; i<3; i++) {
            if(i == idx){
                dp[1][i] = map[1][i];
            } else {
                dp[1][i] = INF;
            }
        }


        for(int i=2; i<=N; i++) {
            dp[i][0] = map[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = map[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = map[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int ret = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            if(i != idx){
                if(ret > dp[N][i]) {
                    ret = dp[N][i];
                }
            }
        }

        return ret;
    }
}
