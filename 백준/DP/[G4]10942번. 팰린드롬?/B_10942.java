import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 길이 1 ~ N 까지에 대해 전부 순회하며 DP를 채워나가는 방식
 *  Ex) 길이가 4인 dp는 길이가 2인 dp를 통해 구한다.
 */
public class B_10942 {

    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 길이가 1인 경우
        for(int i=1; i<=N; i++) {
            dp[i][i] = true;
        }

        // 길이가 2인 경우
        for(int i=1; i<N; i++) {
            if(arr[i] == arr[i+1])
                dp[i][i+1] = true;
        }

        for(int i=3; i<=N; i++) {
            for(int j=1; j<=N+1-i; j++) {
                isPelin(j, j+i-1);
            }
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(dp[S][E])
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    public static void isPelin(int S, int E) {

        if(dp[S][E]) {
            return;
        }

        if(dp[S+1][E-1]) {
            if (arr[S] == arr[E]) {
                dp[S][E] = true;
            }
        }
    }
}
