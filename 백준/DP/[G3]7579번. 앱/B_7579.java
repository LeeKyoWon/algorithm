import java.io.*;
import java.util.*;

/**
 * DP
 */
public class B_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] m = new int[N];
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][10001];

        for(int i=c[0]; i<=10000; i++) {
            dp[0][i] = m[0];
        }

        for (int i = 1; i < N; i++) {
            for(int j=0; j<=10000; j++) {
                if(j < c[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-c[i]] + m[i], dp[i-1][j]);
                }
            }
        }

        for (int i = 0; i <= 10000; i++) {
            if(dp[N-1][i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
